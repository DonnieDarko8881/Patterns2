package com.kodilla.patterns2.facade.api;

import com.kodilla.patterns2.facade.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@EnableAspectJAutoProxy
public class OrderFacade {
    private ShopService shopService;
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderFacade.class);

    @Autowired
    public OrderFacade(ShopService shopService) {
        this.shopService = shopService;
    }

    public void processOrder(OrderDto order, long userId)
            throws OrderProcessingException {
        boolean submittedOrder = false;
        long orderId = registerNewOrder(userId);
        validateOrderId(orderId);
        try {
            calculateValueOfItems(order, orderId);
            ifPaid(orderId);
            verifyOrder(orderId);
            submittedOrder = ifOrderSubmitted(orderId);
        } finally {
            validateCancelOrder(!submittedOrder, orderId);
        }
    }

    public long registerNewOrder(long userId) {
        long orderId = shopService.openOrder(userId);
        LOGGER.info("Registering new Order, ID: " + orderId);
        return orderId;
    }

    public void validateOrderId(long orderId) throws OrderProcessingException {
        if (orderId < 0) {
            LOGGER.error(OrderProcessingException.ERR_NOT_AUTHORIZED);
            throw new OrderProcessingException(OrderProcessingException.ERR_NOT_AUTHORIZED);
        }
    }

    private void calculateValueOfItems(OrderDto order, long orderId) {
        addItems(order, orderId);
        BigDecimal value = shopService.calculateValue(orderId);
        LOGGER.info("Order value is: " + value + " USD");
    }

    private void addItems(OrderDto order, long orderId) {
        for (ItemDto orderItem : order.getItems()) {
            LOGGER.info("Adding item " + orderItem.getProductId() + ", "
                    + orderItem.getQuantity() + " pcs");
            shopService.addItem(orderId, orderItem.getProductId(), orderItem.getQuantity());
        }
    }

    private void ifPaid(long orderId) throws OrderProcessingException {
        if (!shopService.doPayment(orderId)) {
            LOGGER.error(OrderProcessingException.ERR_PAYMENT_REJECTED);
            throw new OrderProcessingException(OrderProcessingException.ERR_PAYMENT_REJECTED);
        }
        LOGGER.info("Payment for order was done");
    }

    private void verifyOrder(long orderId) throws OrderProcessingException {
        boolean readyToSubmit = shopService.verifyOrder(orderId);
        if (!readyToSubmit) {
            LOGGER.error(OrderProcessingException.ERR_VERIFICATION_ERROR);
            throw new OrderProcessingException(OrderProcessingException.ERR_VERIFICATION_ERROR);
        }
        LOGGER.info("Order is ready to submit");
    }

    private boolean ifOrderSubmitted(long orderId) throws OrderProcessingException {
        boolean ifOrderSubmitted = shopService.submitOrder(orderId);
        if (!ifOrderSubmitted) {
            LOGGER.error(OrderProcessingException.ERR_SUBMITTING_ERROR);
            throw new OrderProcessingException(OrderProcessingException.ERR_SUBMITTING_ERROR);
        }
        LOGGER.info("Order " + orderId + "submitted");
        return true;
    }


    private void validateCancelOrder(boolean wasError, long orderId) {
        if (wasError) {
            LOGGER.info("Cancelling order " + orderId);
            shopService.cancelOrder(orderId);
        }
    }
}

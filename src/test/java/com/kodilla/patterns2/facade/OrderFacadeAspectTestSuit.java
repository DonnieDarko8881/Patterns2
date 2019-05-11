package com.kodilla.patterns2.facade;

import com.kodilla.patterns2.facade.api.ItemDto;
import com.kodilla.patterns2.facade.api.OrderDto;
import com.kodilla.patterns2.facade.api.OrderFacade;
import com.kodilla.patterns2.facade.api.OrderProcessingException;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderFacadeAspectTestSuit{
    @Autowired
    private ShopService shopService;

    @Autowired
    private OrderFacade orderFacade;


    @Test
    public void testShopFacade() {
        OrderDto order = new OrderDto();
        order.addItem(new ItemDto(10L, 2));
        order.addItem(new ItemDto(216L, 1));
        order.addItem(new ItemDto(25L, 1));
        order.addItem(new ItemDto(11L, 3));
        try {
            orderFacade.proccessOrder(order, 1L);
        } catch (OrderProcessingException e) {
            //business exception  - shpuld be handled in real application
        }
    }
}


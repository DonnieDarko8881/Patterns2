package com.kodilla.patterns2.observer.homework;

public interface Observable {
    void putToQue(Task task);
    void registerObserver(Observer observer);
    void notifyObservers();
}

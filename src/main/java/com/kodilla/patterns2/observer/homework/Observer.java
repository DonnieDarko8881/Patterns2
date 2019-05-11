package com.kodilla.patterns2.observer.homework;

public interface Observer {
    void update(Student student);
    void pollTask(Student student);
    void showTask(Student student);
}

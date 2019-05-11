package com.kodilla.patterns2.observer.homework;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Student implements Observable {
    private Queue<Task> queOfTask;
    private List<Observer> observers;
    private String firstName;
    private String lastName;

    public Student( String firstName, String lastName) {
        this.queOfTask = new ArrayDeque<>();
        this.observers = new ArrayList<>();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void putToQue(Task task) {
         queOfTask.offer(task);
         notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer mentor : observers) {
             mentor.update(this);
        }
    }

    public Queue<Task> getQueOfTask() {
        return queOfTask;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

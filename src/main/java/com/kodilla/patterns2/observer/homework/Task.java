package com.kodilla.patterns2.observer.homework;

public class Task {
    private final String name;
    private  Status status;

    public Task(String name) {
        this.name = name;
        this.status = Status.TO_ACCEPT;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

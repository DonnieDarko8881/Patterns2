package com.kodilla.patterns2.observer.homework;

public class Mentor implements Observer{
    private final String username;
    private int updateCount;

    public Mentor(String username) {
        this.username = username;
    }

    @Override
    public void update(Student student) {
        System.out.println(username + ": New Task in Que Of "+ student.getFirstName() + " "
        +student.getLastName()+ "\n"+
                " (total: " + student.getQueOfTask().size() + " tasks)");
        updateCount++;
    }

    @Override
    public void pollTask(Student student) {
        student.getQueOfTask().poll();
    }

    @Override
    public void showTask(Student student) {
        student.getQueOfTask().peek();
    }

    public int getUpdateCount() {
        return updateCount;
    }
}

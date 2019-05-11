package com.kodilla.patterns2.observer.homework;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentMentorTestSuite {

    @Test
    public void testUpdate(){
        //Given
        Student szymonMedykowski = new Student("Szymon", "Medykowski");
        Student noName = new Student("No","Name");
        Student lordVoldemort = new Student("Lord", "Voldemort");

        Mentor mentorMentorowski = new Mentor("Mentor Mentorowski");
        Mentor mentorProkopiuk = new Mentor("Mentor Prokopiuk");

        Task task = new Task("task");
        Task task1 = new Task("task1");
        Task task2 = new Task  ("task2");
        //when
        szymonMedykowski.registerObserver(mentorProkopiuk);
        noName.registerObserver(mentorMentorowski);
        noName.registerObserver(mentorProkopiuk);
        lordVoldemort.registerObserver(mentorProkopiuk);
        noName.putToQue(task1);
        noName.putToQue(task2);
        szymonMedykowski.putToQue(task);
        szymonMedykowski.putToQue(task1);
        lordVoldemort.putToQue(task);
        //then
        assertEquals(5,mentorProkopiuk.getUpdateCount());
        assertEquals(2, mentorMentorowski.getUpdateCount());
    }
}

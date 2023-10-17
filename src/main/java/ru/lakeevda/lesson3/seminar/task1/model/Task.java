package ru.lakeevda.lesson3.seminar.task1.model;

import java.time.LocalDate;

public class Task {
    private String name;

    private LocalDate createDate;
//    private LocalDate beginDate;
//    private LocalDate factEndDate;
    private LocalDate dueDate;
    private Priority priority;
    private Skill skill;

    private int length;

    public Task(String name, int rank, LocalDate dueDate, Skill skill, int length) {
        this.name = name;
        this.createDate = LocalDate.now();
        this.dueDate = dueDate;
        this.skill = skill;
        this.priority = Priority.P2;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", createDate=" + createDate +
//                ", beginDate=" + beginDate +
//                ", factEndDate=" + factEndDate +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", skill=" + skill +
                '}';
    }
}

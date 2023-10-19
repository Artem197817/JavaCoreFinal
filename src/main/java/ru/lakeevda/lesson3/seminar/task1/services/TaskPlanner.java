package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;


public class TaskPlanner {

    private final SelectionEmployee selectionEmployee;


    public TaskPlanner(SelectionEmployee selectionEmployee) {
        this.selectionEmployee = selectionEmployee;
    }

    public Assigment planTask(Task task) {
        Employee employee = selectionEmployee.selectionEmployee(task.getSkill());
        Assigment assigment = new Assigment(employee, task);
        AssigmentRepository.addAssigment(assigment);
        return assigment;
    }


}

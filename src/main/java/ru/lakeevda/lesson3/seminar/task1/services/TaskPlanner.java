package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.view.View;

import java.util.ArrayList;
import java.util.List;


public class TaskPlanner {

    private final SelectionEmployee selectionEmployee;
    public static List<Task> freeTask = new ArrayList<>();


    public TaskPlanner(SelectionEmployee selectionEmployee) {
        this.selectionEmployee = selectionEmployee;
    }

    public void planTask(Task task) {
        Employee employee = selectionEmployee.selectionEmployee(task.getSkill(), task.getPriority());
        if (employee.getSkill() == Skill.MANAGER) {
            freeTask.add(task);
            return;
        }
        Assigment assigment = new Assigment(employee, task);
        AssigmentRepository.addAssigment(assigment);
        View.informingEmployee(employee, task.getPriority());
    }

    public static List<Task> getFreeTask() {
        return freeTask;
    }


}

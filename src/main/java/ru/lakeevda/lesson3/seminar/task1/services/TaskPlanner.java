package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.Assigment;
import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Task;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;

import java.util.List;

abstract public class TaskPlanner {

    static private List<Employee> employees;

    public static void setEmployees(List<Employee> employees) {
        TaskPlanner.employees = employees;
    }

    public static Assigment planTask(Task task) {
        List<Employee> sortEmployees = employees.stream()
                .filter(x -> x.getSkill() == task.getSkill())
                .filter(x -> !x.isWorking())
                .toList();
        Assigment assigment = null;
        if (!sortEmployees.isEmpty()) {
            assigment = new Assigment(sortEmployees.get(0), task);
            AssigmentRepository.addAssigment(assigment);
        }
        return assigment;
    }
}

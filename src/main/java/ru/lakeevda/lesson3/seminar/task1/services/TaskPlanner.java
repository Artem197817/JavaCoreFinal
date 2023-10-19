package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;

import java.util.List;

public class TaskPlanner {

    private final EmployeeService employeeService;

    private final SelectionEmployee selectionEmployee;

    private List<Employee> employees;

    public TaskPlanner(EmployeeService employeeService, SelectionEmployee selectionEmployee) {
        this.employeeService = employeeService;
        this.selectionEmployee = selectionEmployee;
    }


    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Assigment planTask(Task task) {
        Employee employee = selectionEmployee.selectionEmployee(task.getSkill());
        Assigment assigment = new Assigment(employee, task);
        AssigmentRepository.addAssigment(assigment);
        return assigment;
    }




}

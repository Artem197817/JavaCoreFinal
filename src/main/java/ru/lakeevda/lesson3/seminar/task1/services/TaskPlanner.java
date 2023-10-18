package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;

import java.util.List;

public class TaskPlanner {

    private final EmployeeService employeeService;

    private List<Employee> employees;

    public TaskPlanner(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Assigment planTask(Task task) {
        Employee employee = selectionEmployee(task.getSkill());

        Assigment assigment = new Assigment(employee, task);
        AssigmentRepository.addAssigment(assigment);
        return assigment;
    }


    public Employee selectionEmployee(Skill skill) {
        // При большом количестве заданий необходимо считать общее время заданий и раздавать задания с учетом общей занятости работника
        // Так же при малом количестве заданий все задания будут сыпаться на первого работника. Что тоже не комильфо
        List<Employee> sortEmployees = employees.stream()
                .filter(x -> x.getSkill() == skill)
                .toList();
        for (Employee employee : sortEmployees) {
            if (!employee.isWorking())
                return employee;
        }
        for (Employee employee : sortEmployees) {
            if (employeeService.checkEmployeeTaskPriorityP1(employee)) {
                return employee;
            }
        }
        ManagerService.informingManager("ВНИМАНИЕ МЕНЕДЖЕРА! В департаменте " + skill
                + " нет свободныч работников для задания с высшим пиоритетом");
        return sortEmployees.get(0);
    }

}

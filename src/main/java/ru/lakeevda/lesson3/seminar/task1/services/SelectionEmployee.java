package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Skill;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;

import java.util.List;

public class SelectionEmployee {

    ManagerService managerService;
    DepartmentHRService departmentHRService;

    EmployeeService employeeService;

    public SelectionEmployee(ManagerService managerService, DepartmentHRService departmentHRService, EmployeeService employeeService) {
        this.managerService = managerService;
        this.departmentHRService = departmentHRService;
        this.employeeService = employeeService;
    }

    public Employee selectionEmployee(Skill skill) {
        // При большом количестве заданий необходимо считать общее время заданий и раздавать задания с учетом общей занятости работника
        // Так же при малом количестве заданий все задания будут сыпаться на первого работника. Что тоже не комильфо
        List<Employee> sortEmployees = EmployeeRepository.getEmployees().stream()
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
        managerService.informingManager("ВНИМАНИЕ МЕНЕДЖЕРА! В департаменте " + skill
                + " нет свободных работников для задания с высшим пиоритетом");
        return departmentHRService.getDepartmentManager(skill);

    }
}

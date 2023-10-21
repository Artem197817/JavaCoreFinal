package ru.lakeevda.lesson3.seminar.task1;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.services.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Department departmentEngineer = new Department(Skill.ENGINEER);
        Department departmentHR = new Department(Skill.HR);
        Employee employeeHR = new Employee("Маркова"
                , "Анна"
                , LocalDate.of(1982, 2, 26)
                , 200
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employeeHR);
        DepartmentHRService departmentHRService = new DepartmentHRService();
        departmentHRService.appointManager(departmentHR, employeeHR);
        departmentHRService.addDepartment(departmentHR);
        Employee employee1 = new Employee("Смирнов"
                , "Иван"
                , LocalDate.of(1965, 2, 26)
                , 200
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee1);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee1);
        Employee employee2 = new Employee("Иванов"
                , "Дмитрий"
                , LocalDate.of(1978, 8, 14)
                , 200
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee2);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee2);
        Employee employee3 = new Employee("Соболев"
                , "Сергей"
                , LocalDate.of(1989, 5, 10)
                , 300
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employee3);
        departmentHRService.appointManager(departmentEngineer, employee3);
        Employee employee4 = new Employee("Смирнов"
                , "Федор"
                , LocalDate.of(1985, 12, 10)
                , 100
                , Skill.HR);
        EmployeeRepository.addEmployee(employee4);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee4);
        Director director = new Director("Петров"
                , "Петр"
                , "Петрович"
                , LocalDate.of(1973, 8, 17)
                , 1000
                , Skill.DIRECTOR);
        EmployeeRepository.addEmployee(director);
        departmentHRService.addDepartment(departmentEngineer);

        Task task1 = new Task("Задача1"
                , 1,
                LocalDate.now()
                , Skill.ENGINEER
                , 10);

        Task task2 = new Task("Задача2"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);
        Task task3 = new Task("Задача3"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 20);
        task3.setPriority(Priority.P1);
        Task task4 = new Task("Задача4"
                , 2,
                LocalDate.now()
                , Skill.TECHNOLOGIST
                , 20);
        task3.setPriority(Priority.P1);
        Task task5 = new Task("Задача5"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);
        Task task6 = new Task("Задача6"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 120);

        ManagerService managerService = ManagerService.factoryManagerService(employee3, departmentEngineer);
        EmployeeService employeeService = new EmployeeService();
        SelectionEmployee selectionEmployee = new SelectionEmployee(managerService, departmentHRService, employeeService);
        TaskPlanner taskPlanner = new TaskPlanner(selectionEmployee);

        taskPlanner.planTask(task1);
        employeeService.startTaskByEmployee(employee1);
        taskPlanner.planTask(task2);
        employeeService.startTaskByEmployee(employee2);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task4);
        taskPlanner.planTask(task5);
        taskPlanner.planTask(task6);

        employeeService.startTaskByEmployee(employee3);
        employeeService.startTaskByEmployee(employee4);

        System.out.println(taskPlanner.getFreeTask());
        System.out.println(employeeService.getAssigmentsByEmployee(employee1));
        System.out.println(employeeService.getAssigmentsByEmployee(employee2));
        System.out.println(employeeService.getAssigmentsByEmployee(employee3));
    }
}

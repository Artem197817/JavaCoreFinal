package ru.lakeevda.lesson3.seminar.task1;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
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
                , 20);
        Task task3 = new Task("Задача3"
                , 2,
                LocalDate.now()
                , Skill.ENGINEER
                , 20);
        task3.setPriority(Priority.P1);

        ManagerService managerService = ManagerService.factoryManagerService(employee3,departmentEngineer);
        EmployeeService employeeService = new EmployeeService();
        SelectionEmployee selectionEmployee = new SelectionEmployee(managerService, departmentHRService, employeeService);
        TaskPlanner taskPlanner = new TaskPlanner(employeeService,selectionEmployee);

        taskPlanner.setEmployees(EmployeeRepository.getEmployees());

        Assigment assigment1 = taskPlanner.planTask(task1);
        employeeService.startTaskByEmployee(assigment1.getEmployee(), assigment1);
        Assigment assigment2 = taskPlanner.planTask(task2);
        employeeService.startTaskByEmployee(assigment2.getEmployee(), assigment2);
        Assigment assigment3 = taskPlanner.planTask(task3);
        employeeService.startTaskByEmployee(assigment3.getEmployee(), assigment3);
        Assigment assigment4 = taskPlanner.planTask(task3);
        employeeService.startTaskByEmployee(assigment4.getEmployee(), assigment4);
        Assigment assigment5 = taskPlanner.planTask(task3);
        employeeService.startTaskByEmployee(assigment5.getEmployee(), assigment5);

        System.out.println(employeeService.getAssigmentsByEmployee(employee1));
        System.out.println(employeeService.getAssigmentsByEmployee(employee2));
        System.out.println(employeeService.getAssigmentsByEmployee(employee3));
    }
}

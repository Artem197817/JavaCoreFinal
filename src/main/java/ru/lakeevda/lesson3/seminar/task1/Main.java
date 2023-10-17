package ru.lakeevda.lesson3.seminar.task1;

import ru.lakeevda.lesson3.seminar.task1.model.Skill;
import ru.lakeevda.lesson3.seminar.task1.model.Assigment;
import ru.lakeevda.lesson3.seminar.task1.model.Department;
import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Task;
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
        departmentHRService.appointManager(departmentHR,employeeHR);

        Employee employee1 = new Employee("Смирнов"
                , "Иван"
                , LocalDate.of(1965, 2, 26)
                , 200
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee1);
        departmentHRService.addEmployeeDepartment(departmentEngineer,employee1);
        Employee employee2 = new Employee("Иванов"
                , "Дмитрий"
                , LocalDate.of(1978, 8, 14)
                , 200
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee2);
        departmentHRService.addEmployeeDepartment(departmentEngineer,employee2);
        Employee employee3 = new Employee("Соболев"
                , "Сергей"
                , LocalDate.of(1989, 5, 10)
                , 300
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employee3);
        departmentHRService.appointManager(departmentEngineer,employee3);
        Employee employee4 = new Employee("Смирнов"
                , "Федор"
                , LocalDate.of(1985, 12, 10)
                , 100
                , Skill.HR);
        EmployeeRepository.addEmployee(employee4);
        departmentHRService.addEmployeeDepartment(departmentEngineer,employee4);

        Task task1 = new Task("Задача1"
                , 1,
                LocalDate.of(2023, 10, 16)
                , Skill.ENGINEER
                , 10);

        Task task2 = new Task("Задача2"
                , 2,
                LocalDate.of(2023, 10, 15)
                , Skill.ENGINEER
                , 20);


        TaskPlanner.setEmployees(EmployeeRepository.getEmployees());
        Assigment assigment1 = TaskPlanner.planTask(task1);
//        System.out.println(EmployeeRepository.getEmployees());
//        System.out.println(AssigmentRepository.getAssigmentList());
        EmployeeService employeeService = new EmployeeService();
        employeeService.startTaskByEmployee(assigment1.getEmployee(), assigment1);

        Assigment assigment2 = TaskPlanner.planTask(task2);
        employeeService.startTaskByEmployee(assigment2.getEmployee(), assigment2);

        System.out.println(employeeService.getAssigmentsByEmployee(employee1));
        System.out.println(employeeService.getAssigmentsByEmployee(employee2));
        System.out.println(employeeService.getAssigmentsByEmployee(employee3));
    }
}

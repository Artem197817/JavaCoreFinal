package ru.lakeevda.lesson3.seminar.task1;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.DepartmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.services.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Department departmentEngineer = new Department(Skill.ENGINEER);
        DepartmentRepository.addDepartment(departmentEngineer);
        Department departmentHR = new Department(Skill.HR);
        DepartmentRepository.addDepartment(departmentHR);
        Employee employeeHR = new Employee("Маркова"
                , "Анна"
                , LocalDate.of(1982, 2, 26)
                , 200
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employeeHR);
        employeeHR.setDepartment(departmentHR);
        DepartmentHRService departmentHRService = new DepartmentHRService();
        departmentHRService.appointManager(departmentHR, employeeHR);
        departmentHRService.addDepartment(departmentHR);
        System.out.println(employeeHR.getDepartment());
        Employee employee1 = new Employee("Смирнов"
                , "Иван"
                , LocalDate.of(1965, 2, 26)
                , 200
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee1);
        employee1.setDepartment(departmentEngineer);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee1);
        Employee employee2 = new Employee("Иванов"
                , "Дмитрий"
                , LocalDate.of(1978, 8, 14)
                , 200
                , Skill.ENGINEER);
        EmployeeRepository.addEmployee(employee2);
        departmentHRService.addEmployeeDepartment(departmentEngineer, employee2);
        employee2.setDepartment(departmentEngineer);
        Employee employee3 = new Employee("Соболев"
                , "Сергей"
                , LocalDate.of(1989, 5, 10)
                , 300
                , Skill.MANAGER);
        EmployeeRepository.addEmployee(employee3);
        departmentHRService.appointManager(departmentEngineer, employee3);
        employee3.setDepartment(departmentEngineer);
        Employee employee4 = new Employee("Смирнов"
                , "Федор"
                , LocalDate.of(1985, 12, 10)
                , 100
                , Skill.HR);
        EmployeeRepository.addEmployee(employee4);
        employee4.setDepartment(departmentHR);
        departmentHRService.addEmployeeDepartment(departmentHR, employee4);
        Department departmentDirector = new Department(Skill.DIRECTOR);
        DepartmentRepository.addDepartment(departmentDirector);
        Director director = new Director("Петров"
                , "Петр"
                , "Петрович"
                , LocalDate.of(1973, 8, 17)
                , 1000
                , Skill.DIRECTOR);
        EmployeeRepository.addEmployee(director);
        director.setDepartment(departmentDirector);
        departmentHRService.addDepartment(departmentEngineer);

        Task task1 = new Task("Задача1"
                , Skill.ENGINEER
                , 10);
        Task task2 = new Task("Задача2"
                , Skill.ENGINEER
                , 120);
        Task task3 = new Task("Задача3"
                , Skill.ENGINEER
                , 20);
        task3.setPriority(Priority.P1);
        Task task7 = new Task("Задача7"
                , Skill.ENGINEER
                , 20);
        task7.setPriority(Priority.P1);
        Task task4 = new Task("Задача4"
                , Skill.TECHNOLOGIST
                , 20);
        task3.setPriority(Priority.P1);
        Task task5 = new Task("Задача5"
                , Skill.ENGINEER
                , 120);
        Task task6 = new Task("Задача6"
                , Skill.ENGINEER
                , 120);
        Task task8 = new Task("Задача8"
                , Skill.ENGINEER
                , 20);
        task8.setPriority(Priority.P1);
        Task task9 = new Task("Задача9"
                , Skill.ENGINEER
                , 20);
        task9.setPriority(Priority.P1);

        ManagerService managerService = ManagerService.factoryManagerService(employee3);
        EmployeeService employeeService = new EmployeeService();
        SelectionEmployee selectionEmployee = new SelectionEmployee (departmentHRService, employeeService);
        TaskPlanner taskPlanner = new TaskPlanner(selectionEmployee);

        taskPlanner.planTask(task1);
        employeeService.startTaskByEmployee(employee1);
        taskPlanner.planTask(task2);
        employeeService.startTaskByEmployee(employee2);
        taskPlanner.planTask(task3);
        taskPlanner.planTask(task7);
        taskPlanner.planTask(task8);
        taskPlanner.planTask(task4);
        taskPlanner.planTask(task5);
        taskPlanner.planTask(task6);
        employeeService.finishTaskByEmployee(employee1);
        taskPlanner.planTask(task9);
        employeeService.startTaskByEmployee(employee1);

        employeeService.startTaskByEmployee(employee3);
        employeeService.startTaskByEmployee(employee4);

     //   assert managerService != null;
      //  managerService.manualAssignmentTask();

       // System.out.println(TaskPlanner.getFreeTask());
       System.out.println(employeeService.getAssigmentsByEmployee(employee1));
       System.out.println(employeeService.getAssigmentsByEmployee(employee2));
       // System.out.println(employeeService.getAssigmentsByEmployee(employee3));

        FileService fileService = new FileService();
        fileService.fileWriterEmployee(EmployeeRepository.getEmployees());
        fileService.fileWriterAssigmentAndTask(AssigmentRepository.getAssigmentList(),TaskPlanner.getFreeTask());
       // fileService.fileWriterAssigment(AssigmentRepository.getAssigmentList());
        System.out.println(fileService.fileReaderEmployee());
        fileService.fileWriterAssigmentAndTask(AssigmentRepository.getAssigmentList(),TaskPlanner.getFreeTask());
        fileService.fileReaderTask().forEach(System.out::println);
    }
}

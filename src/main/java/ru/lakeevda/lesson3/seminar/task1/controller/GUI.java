package ru.lakeevda.lesson3.seminar.task1.controller;

import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.services.*;
import ru.lakeevda.lesson3.seminar.task1.view.View;


public class GUI {

    private final ScannerService scannerService;
    private final EmployeeService employeeService;
    private final DepartmentHRService departmentHRService;
    private final TaskPlanner taskPlanner;
    private final FileService fileService;


    public GUI() {
        this.scannerService = new ScannerService();
        this.employeeService = new EmployeeService();
        this.departmentHRService = new DepartmentHRService();
        this.taskPlanner = new TaskPlanner(new SelectionEmployee(departmentHRService, employeeService));
        this.fileService = new FileService();
    }

    public void run() {
        boolean isExit = true;
        while (isExit) {
            String command = scannerService.stringScanner("Введите команду. Либо воспользуйтесь командой HELP ").toUpperCase();
            switch (command) {
                case ("EXIT") -> {
                    isExit = false;
                    scannerService.close();
                }
                case ("HELP") -> View.help();
                case ("GET A") -> AssigmentRepository.getAssigmentList().forEach(System.out::println);
                case ("GET FT") -> TaskPlanner.getFreeTask().forEach(System.out::println);
                case ("GET E") -> EmployeeRepository.getEmployees().forEach(System.out::println);
                case ("GET AE") -> employeeService.getAssigmentsByIdEmployee().forEach(System.out::println);
                case ("GET ADE") -> departmentHRService.getEmployeesByDepartment().forEach(System.out::println);
                case ("CREATE E") -> departmentHRService.createEmployee();
                case ("CREATE T") -> taskPlanner.createTask();
                case ("GET CT") -> fileService.fileReaderTaskComplete();
                case ("TASK M") -> ManagerService.assigmentTaskManual();
                default -> {
                    View.printConsole("Команда не распознана");
                    View.help();
                }
            }
        }

    }
}

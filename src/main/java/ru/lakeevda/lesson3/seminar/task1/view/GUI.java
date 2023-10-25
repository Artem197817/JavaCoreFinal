package ru.lakeevda.lesson3.seminar.task1.view;

import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.services.EmployeeService;
import ru.lakeevda.lesson3.seminar.task1.services.ScannerService;
import ru.lakeevda.lesson3.seminar.task1.services.TaskPlanner;

public class GUI {

    ScannerService scannerService;
    EmployeeService employeeService;

    public GUI() {
        this.scannerService = new ScannerService();
        this.employeeService = new EmployeeService();
    }

    public void run() {
        boolean isExit = true;
        while (isExit) {
            String command = scannerService.stringScanner("Введите команду. Либо воспользуйтесь командой HELP ");
            switch (command) {
                case ("EXIT"):
                    isExit = false;
                    scannerService.close();
                    break;
                case ("HELP"):
                    View.help();
                    break;
                case ("GET A"):
                    AssigmentRepository.getAssigmentList().forEach(System.out::println);
                    break;
                case ("GET FT"):
                    TaskPlanner.getFreeTask().forEach(System.out::println);
                    break;
                case ("GET E"):
                    EmployeeRepository.getEmployees().forEach(System.out::println);
                    break;
                case ("GET AE"):
                    employeeService.getAssigmentsByIdEmployee().forEach(System.out::println);
                    break;
                case ("GET ADE"):
                    break;
                case ("CREATE E"):
                    break;
                case ("CREATE T"):
                    break;
                case ("TASK M"):
                    break;
                default:
                    View.printConsole("Команда не распознана");
                    View.help();
            }
        }

    }
}

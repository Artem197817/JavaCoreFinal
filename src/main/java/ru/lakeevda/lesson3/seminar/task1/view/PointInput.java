package ru.lakeevda.lesson3.seminar.task1.view;

import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.DepartmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.services.StartService;
import ru.lakeevda.lesson3.seminar.task1.services.TaskPlanner;

public class PointInput {

    public static void main(String[] args) {
        StartService startService = new StartService();
        startService.init();

        AssigmentRepository.getAssigmentList().forEach(System.out::println);
        EmployeeRepository.getEmployees().forEach(System.out::println);
        DepartmentRepository.getDepartmentList().forEach(System.out::println);
        TaskPlanner.freeTask.forEach(System.out::println);


    }
}

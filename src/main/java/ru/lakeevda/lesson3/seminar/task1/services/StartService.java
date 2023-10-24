package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

public class StartService {

    public void init(){

        FileService fileService = new FileService();
        EmployeeRepository.setEmployees(fileService.fileReaderEmployee());

        for (Employee employee:EmployeeRepository.getEmployees()){
            Department department = employee.getDepartment();
            department.addEmployee(employee);
        }
        List<Task> tasks = fileService.fileReaderTask();
        for (Task task : tasks) {
            int idEmployee = task.getIdEmployee();
            if (idEmployee == 0) {
                TaskPlanner.freeTask.add(task);
            } else {
                Employee employee = EmployeeRepository.getEmployeeById(idEmployee);
                Assigment assigment = new Assigment(employee,task);
                AssigmentRepository.addAssigment(assigment);
            }
        }
    }
}


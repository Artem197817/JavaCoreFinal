package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;

import java.time.LocalDate;
import java.util.List;

public class EmployeeService {
    public List<Assigment> getAssigmentsByEmployee(Employee employee) {
        return AssigmentRepository.getAssigmentList().stream()
                .filter(x -> x.getEmployee() == employee)
                .toList();
    }

    ;

    public void startTaskByEmployee(Employee employee, Assigment assigment) {
        if (assigment.getEmployee() == employee
                || employee.getSkill() == Skill.MANAGER
                || employee.getSkill() == Skill.DIRECTOR) {
            employee.setWorking(true);
            assigment.setFactStartDate(LocalDate.now());
            if (assigment.getTask().getPriority() == Priority.P1) {
                for (Assigment ass : getAssigmentsByEmployee(employee))
                    ass.setStatus(Status.OnHOLD);
            }
            assigment.setStatus(Status.IN_PROGRESS);
        }
    }

    public void finishTaskByEmployee(Employee employee, Assigment assigment) {
        if (assigment.getEmployee() == employee
                || employee.getSkill() == Skill.MANAGER
                || employee.getSkill() == Skill.DIRECTOR) {
            employee.setWorking(false);
            assigment.setFactEndDate(LocalDate.now());
            assigment.setStatus(Status.COMPLETE);
        }
    }

    public boolean checkEmployeeTaskPriorityP1(Employee employee) {

        List<Assigment> assigmentFilterPriority = getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getPriority() == Priority.P1)
                .toList();
        return assigmentFilterPriority.isEmpty();

    }
}

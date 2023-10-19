package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.Department;
import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Skill;
import ru.lakeevda.lesson3.seminar.task1.services.exeption.CheckingAccessRights;
import ru.lakeevda.lesson3.seminar.task1.view.View;

public class ManagerService {

    private Employee employee;
    private Department department;

    private ManagerService(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
    }

    public static ManagerService factoryManagerService(Employee employee, Department department) {
        try {
            if (department.getManager() == employee || employee.getSkill() == Skill.DIRECTOR) {
                return new ManagerService(employee, department);
            } else throw new CheckingAccessRights();
        } catch (CheckingAccessRights e) {
            View.printConsole(e.getMessage());
        }
        return null;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void raisingSalaries(double percentageIncrease) {
        department.getDepartmentEmployee().stream()
                .filter(x -> x.getSkill() != Skill.MANAGER)
                .forEach(x -> x.setSalary(x.getSalary() * (1 + percentageIncrease)));
    }

    public void informingManager(String message) {
        View.printConsole(message);
    }
    
}

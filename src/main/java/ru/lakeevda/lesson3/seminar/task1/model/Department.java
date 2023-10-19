package ru.lakeevda.lesson3.seminar.task1.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private final Skill skill;
    private List<Employee> departmentEmployee;

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    private Employee manager;

    public Department(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    public Employee getManager() {
        return manager;
    }

    public List<Employee> getDepartmentEmployee() {
        return departmentEmployee;
    }

    public void setDepartmentEmployee(List<Employee> departmentEmployee) {
        this.departmentEmployee = departmentEmployee;
    }

    public List<Employee> addEmployee(Employee employee) {
        if (departmentEmployee == null) departmentEmployee = new ArrayList<>();
        departmentEmployee.add(employee);
        return departmentEmployee;
    }

    @Override
    public String toString() {
        return "Department " + "\n" + "{" +
                "skill='" + skill + '\'' +
                ", manager=" + manager +
                '}';
    }

}

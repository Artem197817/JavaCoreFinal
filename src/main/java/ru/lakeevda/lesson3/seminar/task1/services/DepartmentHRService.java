package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.Department;
import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Skill;
import ru.lakeevda.lesson3.seminar.task1.services.exeption.SkillException;
import ru.lakeevda.lesson3.seminar.task1.view.View;

import java.util.ArrayList;
import java.util.List;

public class DepartmentHRService {

    public List<Department> departments;

    public void addDepartment (Department department) {
        if (departments == null) departments = new ArrayList<>();
        departments.add(department);
    }

    public void addEmployeeDepartment(Department department, Employee employee) {
        department.addEmployee(employee);
        employee.setDepartment(department);
    }

    public void appointManager(Department department, Employee manager) {
        try {
            if (manager.getSkill() == Skill.MANAGER || manager.getSkill() == Skill.DIRECTOR) {
                department.setManager(manager);
                manager.setDepartment(department);
            } else throw new SkillException();
        } catch (SkillException e) {
            View.printConsole(e.getMessage());
        }
    }
    public Employee getDepartmentManager (Skill skill){
       List<Department>  departmentSortSkill = departments.stream()
               .filter(x-> x.getSkill() == skill)
               .toList();
       return departmentSortSkill.get(0).getManager();
    }
}

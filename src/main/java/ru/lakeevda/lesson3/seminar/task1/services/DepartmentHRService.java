package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.Department;
import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Skill;
import ru.lakeevda.lesson3.seminar.task1.repository.DepartmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.services.exeption.SkillException;
import ru.lakeevda.lesson3.seminar.task1.view.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentHRService {

    public List<Department> departments;

    public ScannerService scannerService = new ScannerService();

    public void addDepartment(Department department) {
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

    public Employee getDepartmentManager(Skill skill) {
        List<Department> departmentSortSkill = departments.stream()
                .filter(x -> x.getSkill() == skill)
                .toList();
        try {
            return departmentSortSkill.get(0).getManager();
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Employee("", "", LocalDate.of(1950, 1, 1), 0, Skill.MANAGER);
        }
    }

    public List<Employee> getEmployeesByDepartment() {
        Department department = DepartmentRepository.getDepartmentBySkill(scannerService.skillScanner("Skill department"));
        return department.getDepartmentEmployee();
    }

    public void createEmployee() {

        String lastName = scannerService.stringScanner("Фамилия");
        String firstName = scannerService.stringScanner("Имя");
        LocalDate birthDay = scannerService.dateScanner("День рождения");
        double salary = scannerService.intScanner("Заработная плата");
        Skill skill = scannerService.skillScanner("Skill");
        Employee employee = new Employee(lastName, firstName, birthDay, salary, skill);
        EmployeeRepository.addEmployee(employee);
        Department department = DepartmentRepository.getDepartmentBySkill(skill);
        if (department.getSkill() == Skill.NoSKILL)
            department = new Department(skill);
        addEmployeeDepartment(department, employee);
    }
}
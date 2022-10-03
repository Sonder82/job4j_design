package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeBonus {

    /**
     * Нарушает принцип OCP, т.к. поле класса не является абстракцией, а имеет конкретную реализацию.
     * Нарушение возникнет, когда потребуется значение бонуса для каждой категории работников.
     */
    private List<Employee> employees = new ArrayList<>();

    public EmployeeBonus(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Нарушает принцип OCP, т.к.возвращаем реализацию, а не абстракцию
     */
    public List<Employee> minSalary() {
        return employees.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .stream().toList();
    }
}

package ru.job4j.ood.ocp;

import ru.job4j.ood.ocp.third.right.Employee;

public class PermanentEmployeeBonus {
    private Employee employee;

    public double calculateBonus() {
        return employee.getSalary() * 0.1;
    }
}

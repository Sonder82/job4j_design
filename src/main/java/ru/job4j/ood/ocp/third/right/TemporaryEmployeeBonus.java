package ru.job4j.ood.ocp.third.right;

public class TemporaryEmployeeBonus implements Bonus {


    @Override
    public double calculateBonus(Employee employee) {
        return employee.getSalary() * 0.05;
    }
}

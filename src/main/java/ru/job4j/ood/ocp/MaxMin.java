package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.List;

public class MaxMin {

    /**
     * Нарушает принцип OCP, т.к. поле класса не является абстракцией, а имеет конкретную реализацию.
     */
    private final List<Employee> list = new ArrayList<>();

    /**
     * Нарушает принцип OCP, т.к. параметры и возвращаемые значения не являются абстрактными.
     */
    public List<Employee> addEmployees(Employee employee) {
        list.add(employee);
        return list;
    }
}

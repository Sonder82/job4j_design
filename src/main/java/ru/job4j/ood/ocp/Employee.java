package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Employee {

    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return age == employee.age && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    /**
     * Нарушает принцип OCP, т.к. в случае необходимости использования поиска по другим критериям,
     * потребуется изменить метод, добавить Predicate.
     */

    private static class SearchEmployee {
        List<Employee> searchName(List<Employee> employees, String name) {
            return employees.stream()
                    .filter(n -> n.getName().contains(name))
                    .collect(Collectors.toList());
        }
    }

    /**
     * Нарушает принцип OCP, т.к. реализация в параметрах. В параметрах используем абстракцию.
     */
    public List<Employee> minAge(ArrayList<Employee> value) {
        return value.stream()
                .min(Comparator.comparing(Employee::getAge))
                .stream().toList();
    }
}

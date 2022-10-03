package ru.job4j.ood.ocp;

import java.util.Objects;

public class Employee {

    private String name;
    private double salary;
    private String category;

    public Employee(String name, double salary, String category) {
        this.name = name;
        this.salary = salary;
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int age) {
        this.salary = salary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        return salary == employee.salary && Objects.equals(name, employee.name)
                && Objects.equals(category, employee.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, category);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", age=" + salary
                + ", category='" + category + '\''
                + '}';
    }
}

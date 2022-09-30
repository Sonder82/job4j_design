package ru.job4j.ood.ocp.third.right;

import java.util.Objects;

public  class Employee {
    private int id;
    private String category;
    private double salary;

    public Employee(int id, String category, double salary) {
        this.id = id;
        this.category = category;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
        return id == employee.id && Double.compare(employee.salary, salary) == 0
                && Objects.equals(category, employee.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, salary);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "id=" + id
                + ", category='" + category + '\''
                + ", salary=" + salary
                + '}';
    }
}

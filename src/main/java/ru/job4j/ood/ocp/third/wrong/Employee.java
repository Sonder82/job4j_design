package ru.job4j.ood.ocp.third.wrong;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private String employeeType;

    public Employee(int id, String name, String employeeType) {
        this.id = id;
        this.name = name;
        this.employeeType = employeeType;
    }

    public double calculateBonus(double salary) {
        return salary * 0.1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
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
        return id == employee.id && Objects.equals(name, employee.name) && Objects.equals(employeeType, employee.employeeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeeType);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", employeeType='" + employeeType + '\''
                + '}';
    }
}

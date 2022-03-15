package ru.job4j.generic;

public class Role extends Base {

    private final String departmentName;

    public Role(String id, String departmentName) {
        super(id);
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}

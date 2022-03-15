package ru.job4j.generic;

public class User extends Base {

    private final String userName;

    public User(String id, String name) {
        super(id);
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }
}

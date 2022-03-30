package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;

    private int children;

    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User firstUser = new User("Aleksey", 2, new GregorianCalendar(1982, 2, 28));
        User secondUser = new User("Aleksey", 2, new GregorianCalendar(1982, 2, 28));

        Map<User, Object> container = new HashMap<>();
        container.put(firstUser, new Object());
        container.put(secondUser, new Object());

        System.out.println(container);

    }

}
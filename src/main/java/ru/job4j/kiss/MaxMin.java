package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public User max(List<User> value) {
        value.sort(Comparator.comparing(User::getName));
        return value.get(value.size() - 1);
    }

    public User min(List<User> value) {
        value.sort(Comparator.comparingInt(User::getAge));
        return value.get(0);
    }
}

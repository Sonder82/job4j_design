package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void checkMax() {
        List<User> jobs = Arrays.asList(
                new User("Aleksey", 40),
                new User("Anton", 29),
                new User("Ivan", 31)
        );
        MaxMin maxMin = new MaxMin();
        User user = maxMin.max(jobs, Comparator.comparing(User::getName));
        String result = user.getName();
        assertThat(result).isEqualTo("Ivan");
    }

    @Test
    void checkMin() {
        List<User> jobs = Arrays.asList(
                new User("Aleksey", 40),
                new User("Anton", 29),
                new User("Ivan", 31)
        );
        MaxMin maxMin = new MaxMin();
        User user = maxMin.min(jobs, Comparator.comparingInt(User::getAge));
        assertThat(user.getAge()).isEqualTo(29);
    }

    @Test
    void checkNull() {
        List<User> jobs = List.of();
        MaxMin maxMin = new MaxMin();
        assertThatThrownBy(() -> maxMin.max(jobs, Comparator.comparingInt(User::getAge)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value не должен быть пустым");
    }
}
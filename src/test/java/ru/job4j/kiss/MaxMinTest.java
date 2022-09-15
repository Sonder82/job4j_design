package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        User user = maxMin.max(jobs);
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
        User user = maxMin.min(jobs);
        int result = user.getAge();
        assertThat(result).isEqualTo(29);
    }
}
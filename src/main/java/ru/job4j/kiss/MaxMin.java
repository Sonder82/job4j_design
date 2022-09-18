package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    private <T> T findMaxMin(List<T> value, Comparator<T> comparator) {

        if (value.isEmpty()) {
            throw new IllegalArgumentException("value не должен быть пустым");
        }
        T result;
            result = value.get(0);
            for (T val : value) {
                if (comparator.compare(val, result) > 0) {
                    result = val;
                }
            }
        return result;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findMaxMin(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findMaxMin(value, comparator.reversed());
    }


}

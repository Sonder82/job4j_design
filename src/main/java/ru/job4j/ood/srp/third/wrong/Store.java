package ru.job4j.ood.srp.third.wrong;

import java.util.List;

public interface Store {
    List<Integer> product(Item item);
    void print(List<Integer> numbers);
}

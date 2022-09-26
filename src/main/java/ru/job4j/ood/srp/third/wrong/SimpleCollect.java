package ru.job4j.ood.srp.third.wrong;

import java.util.ArrayList;
import java.util.List;

public class SimpleCollect implements Store {
    @Override
    public List<Integer> product(Item item) {
        List<Integer> list = new ArrayList<>();
        list.add(item.getId());
        return list;
    }

    @Override
    public void print(List<Integer> items) {
        items.forEach(System.out::println);
    }
}

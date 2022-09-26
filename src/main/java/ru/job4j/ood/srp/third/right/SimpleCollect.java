package ru.job4j.ood.srp.third.right;

import ru.job4j.ood.srp.third.wrong.Item;

import java.util.ArrayList;
import java.util.List;

public class SimpleCollect implements Store {

    @Override
    public List<Integer> product(Item item) {
        List<Integer> list = new ArrayList<>();
        list.add(item.getId());
        return list;
    }
}

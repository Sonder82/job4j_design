package ru.job4j.set;


import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Класс описывает реализацию коллекции Set на массиве.
 *
 * @param <T>
 * @author Aleksey Novoselov
 * @version 1.0
 */

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean isAdd = !this.contains(value);
        if (isAdd) {
            set.add(value);
        }
        return isAdd;
    }

    @Override
    public boolean contains(T value) {
        boolean isContains = false;
        for (T element : set) {
            if (Objects.equals(element, value)) {
                isContains = true;
            }
        }
        return isContains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

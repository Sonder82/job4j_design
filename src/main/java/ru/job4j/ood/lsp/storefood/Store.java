package ru.job4j.ood.lsp.storefood;


import java.util.Calendar;
import java.util.List;

/**
 * Чтобы не переписывать во всех классах код,
 * создадим дефолтный метод.
 * Метод percent будет считать проценты на сколько
 * израсходован срок годности продукта.
 */
public interface Store {
    boolean addFood(Food food);
    List<Food> getFoodList();
}









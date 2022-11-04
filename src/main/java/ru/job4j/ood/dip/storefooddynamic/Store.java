package ru.job4j.ood.dip.storefooddynamic;


import java.util.List;

public interface Store {
    boolean addFood(Food food);
    List<Food> getFoodList();
    void clean();
}









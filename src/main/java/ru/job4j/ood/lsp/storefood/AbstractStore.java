package ru.job4j.ood.lsp.storefood;

import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс это каркасная реализация хранилища.
 * Все хранилища имеют одинаковую логику получения всех продуктов (findAll()).
 * Логика add() тоже шаблонна.
 * Если продукт проходит какую-то проверку, то добавляем его в хранилище.
 */
public abstract class AbstractStore implements Store {

    protected final List<Food> foodList = new ArrayList<>();

    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (isNotExpired(food)) {
            result = true;
            foodList.add(food);
        }
        return result;
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }

    protected abstract boolean isNotExpired(Food food);
}

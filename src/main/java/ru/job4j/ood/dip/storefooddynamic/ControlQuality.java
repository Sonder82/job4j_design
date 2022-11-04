package ru.job4j.ood.dip.storefooddynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует контроль качества продуктов
 */
public class ControlQuality {

    private List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    /**
     * Метод выполняет перемещение продукта в определенное хранилище.
     * С помощью цикла проходим по списку хранилищ и,
     * в зависимости от условий, добавим продукт в нужное хранилище.
     * @param food продукт для добавления в хранилище
     */
    public void addFoodToStore(Food food) {
            for (Store store : storeList) {
                store.addFood(food);
            }
    }

    public void resort() {
        List<Food> storeNewListFood = new ArrayList<>();
        storeList.forEach(store -> storeNewListFood.addAll(store.getFoodList()));
        storeList.forEach(store -> store.clean());
        storeNewListFood.forEach((Food food) -> addFoodToStore(food));
    }
}

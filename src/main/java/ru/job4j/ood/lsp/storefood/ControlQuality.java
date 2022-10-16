package ru.job4j.ood.lsp.storefood;


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
     * @param food
     */
    public void addFoodToStore(Food food) {
            for (Store store : storeList) {
                store.addFood(food);
            }
    }
}

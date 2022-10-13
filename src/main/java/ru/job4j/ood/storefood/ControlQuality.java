package ru.job4j.ood.storefood;


public class ControlQuality {
    private Store store;

    public ControlQuality(Store store) {
        this.store = store;
    }

    public void algorithm(Food food) {
            store.addFood(food);
    }
}

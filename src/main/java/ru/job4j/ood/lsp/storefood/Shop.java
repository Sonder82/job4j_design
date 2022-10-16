package ru.job4j.ood.lsp.storefood;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore implements Store {

    List<Food> shopFoodList = new ArrayList<>();

    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (isNotExpired(food)) {
            result = true;
            discount(food);
            shopFoodList.add(food);
        }
        return result;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(shopFoodList);
    }

    @Override
    protected boolean isNotExpired(Food food) {
        return (CountPercent.percent(food) > Constants.DATE_EXPIRY_25
                && CountPercent.percent(food) < Constants.DATE_EXPIRY_100);
    }

    protected void discount(Food food) {
        if (CountPercent.percent(food) > Constants.DATE_EXPIRY_75) {
            food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount() / 100));
        }
    }
}

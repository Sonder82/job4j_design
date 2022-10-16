package ru.job4j.ood.lsp.storefood;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore implements Store {

    List<Food> wareHouseList = new ArrayList<>();

    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (isNotExpired(food)) {
            result = true;
            wareHouseList.add(food);
        }
        return result;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(wareHouseList);
    }

    @Override
    protected boolean isNotExpired(Food food) {
        return CountPercent.percent(food) < Constants.DATE_EXPIRY_25;
    }
}

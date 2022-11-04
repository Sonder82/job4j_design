package ru.job4j.ood.dip.storefooddynamic;

public class Shop extends AbstractStore {

    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (isNotExpired(food)) {
            result = true;
            discount(food);
            foodList.add(food);
        }
        return result;
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

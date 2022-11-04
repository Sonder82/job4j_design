package ru.job4j.ood.dip.storefooddynamic;

public class Warehouse extends AbstractStore {

    @Override
    protected boolean isNotExpired(Food food) {
        return CountPercent.percent(food) < Constants.DATE_EXPIRY_25;
    }
}

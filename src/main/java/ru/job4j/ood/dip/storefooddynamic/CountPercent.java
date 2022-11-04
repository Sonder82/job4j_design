package ru.job4j.ood.dip.storefooddynamic;

import java.util.Calendar;

public class CountPercent {

    /**
     * Метод percent будет считать проценты на сколько
     * израсходован срок годности продукта.
     */
    public static int percent(Food food) {
        long total = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        long current = Calendar.getInstance().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        return (int) (current * 100 / total);
    }
}

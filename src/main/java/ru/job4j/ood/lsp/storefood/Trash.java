package ru.job4j.ood.lsp.storefood;

public class Trash extends AbstractStore {

    @Override
    protected boolean isNotExpired(Food food) {
        return CountPercent.percent(food) <= Constants.DATE_EXPIRY_0;
    }
}

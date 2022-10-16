package ru.job4j.ood.lsp.storefood;

public abstract class AbstractStore {

    protected abstract boolean isNotExpired(Food food);
}

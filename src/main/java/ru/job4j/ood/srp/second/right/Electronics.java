package ru.job4j.ood.srp.second.right;

public class Electronics extends Product {
    @Override
    public double calculateTaxIncludedPrice() {
        return getBasePrice() * 1.18;
    }
}

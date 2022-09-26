package ru.job4j.ood.srp.second.right;

import java.util.List;

public class ShoppingCartService {
    public double calculateTotalOrder(List<Product> products) {

        double orderTotal = 0;

        for (Product product : products) {
            orderTotal += product.calculateTaxIncludedPrice();
        }
        return orderTotal;
    }
}

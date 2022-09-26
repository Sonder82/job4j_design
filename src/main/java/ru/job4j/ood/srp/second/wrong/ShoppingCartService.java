package ru.job4j.ood.srp.second.wrong;

import ru.job4j.ood.srp.second.wrong.Product;

import java.util.List;

public class ShoppingCartService {
    public double calculateTotalOrder(List<Product> products) {

        double orderTotal = 0;

        for (Product product : products) {
            if ("FOOD".equals(product.getType())) {
                orderTotal += product.getPrice() * 1.10;
            } else if ("Electronics".equals(product.getType())) {
                orderTotal += product.getPrice() * 1.18;
            }
        }
        return orderTotal;
    }
}

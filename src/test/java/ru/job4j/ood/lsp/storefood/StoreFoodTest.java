package ru.job4j.ood.lsp.storefood;

import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class StoreFoodTest {

    @Test
    void addFoodToWarehouse() {
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.set(expiredDate.get(Calendar.YEAR) + 1, expiredDate.get(Calendar.MONTH),
                expiredDate.get(Calendar.DATE));
        Calendar createDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DATE));
        Food bread = new Bread("Borodinskiy", expiredDate, createDate, 100, 10);
        Store wareHouse = new Warehouse();
        assertThat(wareHouse.addFood(bread)).isTrue();
    }

    @Test
    void getWarehouseFoodList() {
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.set(expiredDate.get(Calendar.YEAR) + 1, expiredDate.get(Calendar.MONTH),
                expiredDate.get(Calendar.DATE));
        Calendar createDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DATE));
        Food bread = new Bread("Borodinskiy", expiredDate, createDate, 100, 10);
        Store wareHouse = new Warehouse();
        wareHouse.addFood(bread);
        assertThat(wareHouse.getFoodList()).hasSize(1);
    }

    @Test
    void addFoodToShop() {
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.set(expiredDate.get(Calendar.YEAR) + 1, expiredDate.get(Calendar.MONTH),
                expiredDate.get(Calendar.DATE));
        Calendar createDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR) - 1, createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DATE));
        Food milk = new Milk("Prostokvashino", expiredDate, createDate, 200, 10);
        Store shop = new Shop();
        assertThat(shop.addFood(milk)).isTrue();
    }

    @Test
    void getShopFoodList() {
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.set(expiredDate.get(Calendar.YEAR) + 1, expiredDate.get(Calendar.MONTH),
                expiredDate.get(Calendar.DATE));
        Calendar createDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR) - 1, createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DATE));
        Food milk = new Milk("Prostokvashino", expiredDate, createDate, 100, 10);
        Store shop = new Shop();
        shop.addFood(milk);
        assertThat(shop.getFoodList()).hasSize(1);
    }

    @Test
    void addFoodToShopWithDiscount() {
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.set(expiredDate.get(Calendar.YEAR), expiredDate.get(Calendar.MONTH),
                expiredDate.get(Calendar.DATE) + 1);
        Calendar createDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR) - 1, createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DATE));
        Food milk = new Milk("Prostokvashino", expiredDate, createDate, 200, 10);
        Store shop = new Shop();
        shop.addFood(milk);
        assertThat(milk.getPrice()).isEqualTo(180);
    }

    @Test
    void addFoodToTrash() {
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.set(expiredDate.get(Calendar.YEAR), expiredDate.get(Calendar.MONTH) - 1,
                expiredDate.get(Calendar.DATE) - 2);
        Calendar createDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DATE) - 1);
        Food milk = new Milk("Prostokvashino", expiredDate, createDate, 200, 10);
        Store trash = new Trash();
        assertThat(trash.addFood(milk)).isTrue();
    }

    @Test
    void getTrashFoodList() {
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.set(expiredDate.get(Calendar.YEAR), expiredDate.get(Calendar.MONTH),
                expiredDate.get(Calendar.DATE) - 1);
        Calendar createDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DATE));
        Food milk = new Milk("Prostokvashino", expiredDate, createDate, 100, 10);
        Store trash = new Trash();
        trash.addFood(milk);
        assertThat(trash.getFoodList()).hasSize(1);
    }

    @Test
    void checkControlQuality() {
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.set(expiredDate.get(Calendar.YEAR), expiredDate.get(Calendar.MONTH),
                expiredDate.get(Calendar.DATE) - 1);
        Calendar createDate = Calendar.getInstance();
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DATE));
        Food milk = new Milk("Prostokvashino", expiredDate, createDate, 100, 10);
        Store wareHouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality control = new ControlQuality(List.of(wareHouse, shop, trash));
        control.addFoodToStore(milk);
        assertThat(trash.getFoodList()).hasSize(1);
    }
}
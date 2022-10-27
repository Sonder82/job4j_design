package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    public static final ActionDelegate STUB_ACTION = System.out::println;


    @Override
    public void print(Menu menu) {
        long count;
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            count = menuItemInfo.getNumber().chars().filter(ch -> ch == '.').count();
            if (count == 1) {
                System.out.println(menuItemInfo.getNumber().concat(menuItemInfo.getName()));
            }
            if (count == 2) {
                System.out.println("---" + menuItemInfo.getNumber().concat(menuItemInfo.getName()));
            }
            if (count == 3) {
                System.out.println("------" + menuItemInfo.getNumber().concat(menuItemInfo.getName()));
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        SimpleMenuPrinter simpleMenuPrinter = new SimpleMenuPrinter();
        simpleMenuPrinter.print(menu);
    }
}

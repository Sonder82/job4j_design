package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    public static final String CHAR = "---";

    @Override
    public String print(Menu menu) {
       StringBuilder sb = new StringBuilder();
        for (Menu.MenuItemInfo menuItemInfo : menu) {
           int count = menuItemInfo.getNumber().split("\\.").length - 1;
           sb.append(CHAR.repeat(count)).append(menuItemInfo.getNumber())
                   .append(menuItemInfo.getName()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}

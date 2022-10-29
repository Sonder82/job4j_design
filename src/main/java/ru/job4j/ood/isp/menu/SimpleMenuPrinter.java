package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    public static final String CHAR = "---";

    @Override
    public String print(Menu menu) {
       StringBuilder sb = new StringBuilder();
        for (Menu.MenuItemInfo menuItemInfo : menu) {
           int count = menuItemInfo.getNumber().split("\\.").length - 1;
           StringBuilder text = new StringBuilder();
           text.append(CHAR.repeat(count));
            System.out.println(text.append(menuItemInfo.getNumber())
                    .append(menuItemInfo.getName()));
           sb.append(text).append(System.lineSeparator());
        }
        return sb.toString();
    }
}

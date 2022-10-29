package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static final Integer ADD_ROOT = 1;
    public static final Integer ADD_CHILD = 2;
    public static final Integer SHOW_MENU = 3;

    public static final String SELECT = "Выберите меню";
    public static final String PARENT = "Создайте название элемента меню";
    public static final String CHILD = "Создайте название подпункта меню";
    public static final String ROOT = "Укажите название меню, в которое Вы хотите добавить подпункт";
    public static final String ALL_MENU = "Вывод на экран всего меню";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
            Введите 1 для создания названия элемента в меню.
            Введите 2 для создания названия подпункта меню.
            Введите 3 для показа всего меню.
            Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        buildMenu(menu, scanner, menuPrinter);
    }

    private static void checkRootName(Menu menu, String text) {
        if (menu.select(text).isEmpty()) {
            menu.add(Menu.ROOT, text, STUB_ACTION);
            System.out.println("Название пункта меню: " + text);
        } else {
            System.out.println("Вы ввели название пункта меню, которое уже существует. Введите другое имя.");
        }
    }

    private static void checkParentName(Menu menu, String textParent, String textChild) {
        if (menu.select(textParent).isPresent()) {
            menu.add(textParent, textChild, STUB_ACTION);
            System.out.println("Название подпункта меню: " + textChild);
        } else {
            System.out.println("Вы ввели название пункта меню, которое не существует. Введите другое имя.");
        }
    }

    private static void buildMenu(Menu menu, Scanner scanner, MenuPrinter menuPrinter) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_ROOT == userChoice) {
                System.out.println(PARENT);
                String text = scanner.nextLine();
                checkRootName(menu, text);
            } else if (ADD_CHILD == userChoice) {
                System.out.println(CHILD);
                String textChild = scanner.nextLine();
                System.out.println(ROOT);
                String textParent = scanner.nextLine();
                checkParentName(menu, textParent, textChild);
            } else if (SHOW_MENU == userChoice) {
                System.out.println(ALL_MENU);
                System.out.println(menuPrinter.print(menu));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}

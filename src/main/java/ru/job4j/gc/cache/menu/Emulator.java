package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    private static DirFileCache dirFileCache;

    public static final Integer SELECT_CACHE_DIRECTORY = 1;
    public static final Integer LOAD_CACHE_IN_FILE = 2;
    public static final Integer GET_CACHE_FROM_FILE = 3;

    public static final String OPTION = "Выберите меню";
    public static final String VIEW = "Задать кэшируемую директорию";
    public static final String LOAD = "Загрузить содержимое файла в кэш";
    public static final String GET = "Получить содержимое файла из кэша";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1, укажите кэшируемую директорию.
                Введите 2, чтобы загрузить содержимое файла в кэш.
                Введите 3, чтобы получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(OPTION);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (SELECT_CACHE_DIRECTORY == userChoice) {
                System.out.println(VIEW);
                String dir = scanner.nextLine();
                dirFileCache = new DirFileCache(dir);
            } else if (LOAD_CACHE_IN_FILE == userChoice) {
                System.out.println(LOAD);
                String file = scanner.nextLine();
                dirFileCache.put(file, dirFileCache.get(file));
            } else if (GET_CACHE_FROM_FILE == userChoice) {
                System.out.println(GET);
                String file = scanner.nextLine();
                dirFileCache.get(file);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

}

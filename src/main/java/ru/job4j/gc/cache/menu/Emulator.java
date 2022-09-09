package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.DirFileCache;

import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {
    private static DirFileCache dirFileCache;

    public static final Integer SELECT_CACHE_DIRECTORY = 1;
    public static final Integer LOAD_CACHE_IN_FILE = 2;
    public static final Integer GET_CACHE_FROM_FILE = 3;

    public static final String OPTION = "Выберите меню";
    public static final String VIEW = "Здесь будет отображаться кэшируемая директория";
    public static final String LOAD = "Загрузить содержимое файла в кэш. Укажите требуемый файл.";
    public static final String GET = "Получить содержимое файла из кэша. Укажите требуемый файл";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1, чтобы отобразить  кэшируемую директорию.
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
                Path directory = Path.of("C:\\projects\\job4j_design\\src\\"
                       + "main\\java\\ru\\job4j\\gc\\cache\\files");
                dirFileCache = new DirFileCache(String.valueOf(directory));
                System.out.println("Задана кэшируемая директория по пути :" + directory);
            } else if (LOAD_CACHE_IN_FILE == userChoice) {
                System.out.println(LOAD);
                String file = scanner.nextLine();
                if (check(dirFileCache)) {
                    dirFileCache.put(file, dirFileCache.get(file));
                }
            } else if (GET_CACHE_FROM_FILE == userChoice) {
                System.out.println(GET);
                String file = scanner.nextLine();
                if (check(dirFileCache)) {
                    System.out.println(dirFileCache.get(file));
                }
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
    private static boolean check(DirFileCache path) {
        boolean result = true;
        if (path == null) {
            System.out.println("Не задана кэшируемая директория, выполните пункт 1 меню");
            result = false;
        }
        return result;
    }
}

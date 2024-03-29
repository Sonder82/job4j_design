package ru.job4j.gc.prof;

/**
 * 1. Эксперименты с различными GC.
 *
 * Этот интерфейс будет определять
 * работу класса по получению данных
 * от пользователя в консоли.
 *
 * То есть в интерфейсе должен
 * быть объявлен метод, который
 * возвращает введенную строку
 * от пользователя.
 *
 * И, например, метод, который
 * будет возвращать от пользователя
 * число, а не строку.
 *
 */

public interface Input {
    String askStr(String question);

    int askInt(String question);
}

package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * В этом классе будут прочитаны данные из файла.
 * В файле содержится числа.Нужно вывести в консоль четные числа.
 * Чтобы создать пустую изменяемую строку воспользуемся StringBuilder.
 * С помощью метода .append преобразовываем переданный объект в строку и добавляем к текущей строке
 * Получившийся текст можно разбить на строчки через метод String.split.
 * С помощью parseInt преобразуем строку в int.
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                    text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println("Четное число  " + line);
                } else {
                    System.out.println("Нечетное число  " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

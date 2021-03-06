package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Метод filter должен прочитать файл и вернуть строки, где предпоследнее значение - это 404.
 * BufferedReader оборачивает внутри него объект Reader, который автоматически считывает данные
 * из источника (log.txt) и сохраняет их в buffer (буфер) BufferedReader.
 * У буферизированного символьного потока применяем метод чтения целой строки(in.lines).
 * Разделяем строку через split. Разделенные элементы хранятся в массиве,по индексу найдем предпоследний элемент.
 * С помощью Objects.equals сравним предпоследний элемент с "404".
 */
public class LogFilter {
    public List<String> filter(String filter) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filter))) {
            list = in.lines()
                    .filter(x -> {
                        String[] words = x.split(" ");
                        return ("404").equals(words[words.length - 2]);
                    })
                    .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод записывает результат фильтрации(из метода filter) в файл.
     * @param log список из метода filter
     * @param file файл для записи
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
        ))) {
            for (String string : log) {
                out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out::println);
        save(log, "404.txt");
    }
}

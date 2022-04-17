package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
                    .filter(x -> Objects.equals(x.split(" ")[x.split(" ").length - 2], "404"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}

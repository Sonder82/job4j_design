package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * В классе рассматривается модель, которая выполняет считывание данных настроек(файл конфигурации).
 * Данные проверяются по шаблону.
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод загружает пару ключ-значение в Map values.
     * При чтении из файла могут быть пустые строки и комментарии, их нужно пропускать.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !(s.startsWith("#") || s.equals("")))
                    .forEach(s -> {
                        String[] line = s.split("=");
                        if (line.length == 2) {
                            values.put(line[0], line[1]);
                        } else {
                            check();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проверяет на различные нарушения записи шаблона ключ=значение.
     */
    private void check() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (line.startsWith("=") || line.endsWith("=")) {
                    throw new IllegalArgumentException();
                }
                if (!line.contains("=")) {
                    throw new IllegalArgumentException();
                }
                String[] stringMap = line.split("=");
                if (stringMap[0].equals("")) {
                    throw new IllegalArgumentException();
                }
                if (stringMap.length > 2) {
                    StringJoiner joiner = new StringJoiner("=");
                    for (int index = 1; index < stringMap.length; index++) {
                        joiner.add(stringMap[index]);
                        stringMap[1] = joiner.toString();
                        values.put(stringMap[0], stringMap[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}

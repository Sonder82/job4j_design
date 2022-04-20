package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !(s.startsWith("#") || s.equals("")))
                    .forEach(s -> {
                        if (s.startsWith("=") || s.endsWith("=")) {
                            throw new IllegalArgumentException();
                        }
                        if (!s.contains("=")) {
                            throw new IllegalArgumentException();
                        }
                            String[] stringMap = s.split("=");
                            if (stringMap.length == 2) {
                            values.put(stringMap[0], stringMap[1]);
                        }
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
                    });
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

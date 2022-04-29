package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] argsSplit = arg.split("=", 2);
            if (check(argsSplit)) {
                for (String string : argsSplit) {
                    values.put(string.replace("-", ""), argsSplit[1]);
                }
            }
        }
    }

    private boolean check(String[] argsSplit) {
        if (!argsSplit[0].startsWith("-") || argsSplit[1] == null) {
            throw new IllegalArgumentException();
        } else {
            return true;
        }
    }

    public static ArgsName of(String[] args) {
        if ((args.length == 0) || (args.length > 1 && args[1].endsWith("="))) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

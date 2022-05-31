package ru.job4j.io.searchfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FindFiles {
    public static void editArgs(ArgsName argsName) throws IOException {
        List<Path> listPath = new ArrayList<>();
        Path start = Paths.get(argsName.get("d"));
        String typeOfSearch = argsName.get("t");
        if ("name".equals(typeOfSearch)) {
            listPath = search(start, path -> path.toFile().getName().equals(argsName.get("n")));
        }
        if ("regex".equals(typeOfSearch)) {
            listPath = search(start, path -> Pattern.matches(argsName.get("n"), path.toFile().getName()));
        }
        if ("mask".equals(typeOfSearch)) {
            String name = argsName.get("n")
                    .replace("?", ".")
                    .replace("*", ".+");
            listPath = search(start, path -> Pattern.matches(name, path.toFile().getName()));
        }
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(argsName.get("o")))) {
            listPath.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searchFiles = new SearchFiles(condition);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPaths();
    }

    private static void validationDirectory(Path directory) {
        if (!directory.toFile().isDirectory()) {
            throw new IllegalArgumentException("Don't found Directory or wrong extension");
        }
    }

    public static void main(String[] args)  throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validationDirectory(Paths.get(argsName.get("d")));
        editArgs(argsName);
    }
}

package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (validation(args)) {
            search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1]))
                    .forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(".txt"));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean validation(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder and file extension are required. "
                    + "Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION.");
        }
        if (!new File(args[0]).isDirectory()) {
            throw new IllegalArgumentException("Search must start from Directory");
        }
        return true;
    }
}



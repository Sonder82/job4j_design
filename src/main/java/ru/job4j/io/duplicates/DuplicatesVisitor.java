package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс реализует поиск дубликатов
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    public DuplicatesVisitor() {
    }

    /**
     * Используем HashMap,где в key хранится fileProperty, в value список  file.
     * В случае дубликата fileProperty, file добавляем в список,который принадлежит этому fileProperty.
     * @param file файл
     * @param attrs атрибут файла
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (!duplicates.containsKey(fileProperty)) {
            duplicates.put(fileProperty, new ArrayList<>(List.of(file.toAbsolutePath())));
        } else {
            duplicates.get(fileProperty).add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * С помощью Stream проходим по Hashmap.
     * В случае если в value записано несколько files(больше 1-го), это дубликаты.
     * @return список files дубликатов
     */
    public List<Path> getDuplicates() {
        return duplicates.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toList());
    }
}

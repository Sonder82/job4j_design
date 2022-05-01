package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;
import static ru.job4j.io.Search.validation;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path sourceFile : sources) {
                zip.putNextEntry(new ZipEntry(sourceFile.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sourceFile.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validationDirectory(Path directory) {
        if (!directory.toFile().isDirectory()) {
            throw new IllegalArgumentException("Don't found Directory");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName name = ArgsName.of(args);
        Path directory = Paths.get(name.get("d"));
        validationDirectory(directory);
        String extension = name.get("e");
        Path directoryToZip = Paths.get(name.get("o"));
            Zip zip = new Zip();
            zip.packFiles(
                    search(directory, p -> !p.toFile().getName().endsWith(extension)
                    ), directoryToZip.toFile());
        }
    }

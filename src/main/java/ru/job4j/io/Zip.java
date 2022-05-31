package ru.job4j.io;

import ru.job4j.io.searchfiles.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.searchfiles.Search.search;

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

    private static void validationDirectoryAndExtension(Path directory, String extension) {
        if (!directory.toFile().isDirectory()) {
            throw new IllegalArgumentException("Don't found Directory or wrong extension");
        }
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("Extension doesn't start with dot");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName name = ArgsName.of(args);
        Path directory = Paths.get(name.get("d"));
        String extension = name.get("e");
        validationDirectoryAndExtension(directory, extension);
        Path directoryToZip = Paths.get(name.get("o"));
            Zip zip = new Zip();
            zip.packFiles(
                    search(directory, p -> !p.toFile().getName().endsWith(extension)
                    ), directoryToZip.toFile());
        }
    }

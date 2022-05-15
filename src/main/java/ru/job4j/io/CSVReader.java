package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Задача класса прочитать данные из CSV файла и вывести их.
 * В качестве входных данных задается путь к файлу path, разделитель delimiter, приемник данных out и фильтр по столбцам filter.
 */
public class CSVReader {
    /**
     * Метод выполняет валидацию входных параметров
     * @param args входные аргументы
     * @return argsname
     */
    private static ArgsName validation(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Number of parameters must be 4.");
        }
        ArgsName argsName = ArgsName.of(args);
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        if (!path.endsWith(".csv")) {
            throw new IllegalArgumentException("File must be csv.");
        }
        if (!Path.of(path).toFile().exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("Delimiter must be \";\".");
        }
        if (out == null || out.isEmpty()) {
            throw new IllegalArgumentException("Out can't be empty.");
        }
        if (filter == null || filter.isEmpty()) {
            throw new IllegalArgumentException("Filter can't be empty.");
        }
        return argsName;
    }

    /**
     * Метод должен прочитать файл по пути file.csv и вывести только столбцы name, age в консоль
     * Согласно, аргумента filter, находим индекс требуемых столбцов в первой строке
     * По индексу столбцов находим остальные требуемые столбцы
     * @param argsName наименование аргументов
     */
    public static void handle(ArgsName argsName) {
        List<String> result = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        List<String> stringColumnNames = Arrays.asList(argsName.get("filter").split(","));
        List<Integer> columnIndexes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(argsName.get("path")))) {
            List<String> firstLine = Arrays.asList(scanner.nextLine().split(delimiter));
            stringColumnNames.forEach(columnName -> columnIndexes.add(firstLine.indexOf(columnName)));
            columnIndexes.forEach(index -> result.add(firstLine.get(index)));

            while (scanner.hasNextLine()) {
                List<String> line = Arrays.asList(scanner.nextLine().split(delimiter));
                columnIndexes.forEach(index -> result.add(line.get(index)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if ("stdout".equals(out)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int indexListResult = 0; indexListResult < result.size();) {
                for (int indexFilter = 0; indexFilter < columnIndexes.size(); indexFilter++) {
                    stringBuilder.append(result.get(indexListResult++));
                    if (indexFilter == columnIndexes.size() - 1) {
                        System.out.println(stringBuilder);
                    } else {
                        stringBuilder.append(delimiter);
                    }
                }
                stringBuilder.setLength(0);
            }
        } else {
            try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int indexListResult = 0; indexListResult < result.size();) {
                    for (int indexFilter = 0; indexFilter < columnIndexes.size(); indexFilter++) {
                        stringBuilder.append(result.get(indexListResult++));
                        if (indexFilter == columnIndexes.size() - 1) {
                            writer.println(stringBuilder);
                        } else {
                            stringBuilder.append(delimiter);
                        }
                    }
                    stringBuilder.setLength(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * В качестве входных данных задается путь к файлу path, разделитель delimiter, приемник данных out и фильтр по
     * столбцам filter.
     * @param args -path=data/file.csv -delimiter=";"  -out=stdout -filter=name,age
     * @throws Exception - чтение файла.
     */
    public static void main(String[] args) throws Exception {
        handle(validation(args));
    }
}

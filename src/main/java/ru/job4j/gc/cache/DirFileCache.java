package ru.job4j.gc.cache;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * В классе будет выполняться работа по кешированию содержимого определенного файла.
 * Класс наследуется от абстрактного класса AbstractCache<K, V>
 * Соответственно мы переопределим абстрактный метод load, и в данном случае
 * будем работать с файлами.
 * Захотим работать с СУБД создадим другой класс и унаследуемся от нашего
 * абстрактного класса AbstractCache<K, V>, и метод load будет написан под эти цели.
 * Все как в паттерне Фабрика
 */
public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод выполняет чтение содержимого файла по относительному пути.
     *
     * @param key относительный путь к файлу в директории
     * @return содержимое файла в виде строки
     */

    @Override
    protected String load(String key) {

        String contents = null;
        try {
            contents = Files.readString(Paths.get(cachingDir, key));
        } catch (IOException e) {
            System.out.println("Данного файла в директории не существует.");
        }
        return contents;
    }

}

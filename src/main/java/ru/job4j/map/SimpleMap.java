package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс описывает реализацию собственной структуры данных - HashMap
 *
 * @param <K> значение ключа
 * @param <V> значение содержимого
 * @author Aleksey Novoselov
 * @version 1.0
 */
public class SimpleMap<K, V> implements Map<K, V> {

    /**
     * коэффициент загрузки
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * размер таблицы
     */
    private int capacity = 8;

    /**
     * число элементов в таблице
     */
    private int count = 0;

    /**
     * количество модификаций таблицы(при добавлении, удалении элементов)
     */
    private int modCount = 0;

    /**
     * карта с элементами
     */
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод вставки пары
     *
     * @param key   ключ
     * @param value значение
     * @return возвращает false в случае отсутствия места в таблице.
     */
    @Override
    public boolean put(K key, V value) {
        boolean add;
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            add = false;
        } else {
            table[index] = new MapEntry<>(key, value);
            add = true;
            modCount++;
            count++;
        }
        return add;
    }

    /**
     * Метод получает значение по ключу
     *
     * @param key ключ
     * @return возвращает null если значения нет, в противном случае значение
     */
    @Override
    public V get(K key) {
        V value = null;
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> mapEntry = table[index];
        if (mapEntry != null && mapEntry.key.equals(key)) {
            value = mapEntry.value;
        }
        return value;
    }

    /**
     * Метод удаляет значение по ключу
     *
     * @param key ключ
     * @return возвращает true если значение успешно удалено, иначе false.
     */
    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> mapEntry = table[index];
        boolean isRemove = false;
        if (mapEntry != null && mapEntry.key.equals(key)) {
            table[indexFor(hash(mapEntry.hashCode()))] = null;
            isRemove = true;
            modCount++;
            count--;
        }
        return isRemove;
    }

    /**
     * Итератор
     *
     * @return возвращает итератор
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            /**
             * количество ожидаемых модификаций таблицы (при добавлении, удалении элементов)
             */
            private int expectedModCount = modCount;

            /**
             * курсор итератора
             */
            private int index = 0;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    /**
     * Метод реализует хэш-функцию.
     *
     * @param hashCode принимает хэш-код
     * @return хэш
     */
    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode) ^ (hashCode >>> 16);
    }

    /**
     * Метод позволяет получить индекс значения по hash
     *
     * @param hash принимает hash
     * @return возвращает индекс
     */
    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    /**
     * Метод расширяет размер карты
     */
    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                int newIndex = indexFor(hash(mapEntry.hashCode()));
                newTable[newIndex] = mapEntry;
            }
        }
        table = newTable;
    }

    /**
     * Структура хранения данных карта
     *
     * @param <K> ключ
     * @param <V> значение
     */
    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

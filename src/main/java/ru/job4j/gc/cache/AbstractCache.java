package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Создать структуру данных типа кэш.
 * Кэш должен быть абстрактный.
 * Необходимо иметь возможность задать ключ получения объекта кеша
 * и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.
 * @param <K> ключ получения объекта кеша
 * @param <V> значение объекта кеша
 */
public abstract class AbstractCache<K, V> {

    /**
     * Хранить содержимое будем в HashMap
     */
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Метод позволяет добавить объекты в наш HashMap.
     * @param key ключ получения объекта кеша
     * @param value значение объекта кеша
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Метод позволяет получить безопасную ссылку
     * из хранилища кеша.
     * В случае если в кеше нет безопасной ссылки,
     * то выполняем загрузку информации(в нашем примере файл) в кеш
     * с помощью метода load.
     * Затем вставляем в наш HashMap безопасную ссылку
     * @param key ключ получения объекта кеша
     * @return безопасную ссылку на объект кеша
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            SoftReference softReference = new SoftReference(value);
            cache.put(key, softReference);
        }
        return value;
    }

    /**
     * Метод загрузки в кеш
     * @param key
     * @return
     */
    protected abstract V load(K key);
}

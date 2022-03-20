package ru.job4j.list;

import java.util.*;

/**
 * Класс описывает реализацию списка на основе динамического массива, аналог ArrayList.
 * @author Aleksey Novoselov
 * @version 1.0
 */

public class SimpleArrayList<T> implements List<T> {
    /**
     * массив элементов, контейнер
     */
    private T[] container;

    /**
     * количество элементов в контейнере
     */
    private  int size;

    /**
     * количество модификаций контейнера(при добавлении, удалении элементов)
     */
    private int modCount;

    /**
     *
     * @param capacity емкость массива(контейнера)
     */
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Добавление элемента.
     * Метод принимает на вход элемент.
     * Перед добавлением элемента проверяем позволяет ли емкость массива.
     * Если не позволяет, создаем новый массив в 2 раза больше с помощью метода Arrays.copyOf.
     * @param value элемент, который добавляется в массив.
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            upSize();
        }
        container[size++] = value;
        modCount++;
    }

    private void upSize() {
        if (container.length == 0) {
            container =  (T[]) new Object[10];
        }
        container = Arrays.copyOf(container, container.length * 2);
    }

    /**
     * Изменение значения в массиве.
     * Метод принимает значение индекса и новое значение элемента.
     * @param index индекс элемента.
     * @param newValue новое значение элемента.
     * @return возвращает старое значение элемента.
     */
    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    /**
     * Удаление элемента.
     * Метод принимает значение индекса.
     * Для удаления нужно использовать метод System.arraycopy().
     * При удаления элемента мы сдвигаем элементы массива влево(ориентир точки сдвига по index).
     * На последнее место ставим null, чтобы не было утечки памяти (если удаляем последний элемент).
     * @param index индекс элемента
     * @return возвращает удаленный элемент.
     */
    @Override
    public T remove(int index) {
        modCount++;
        T deleteValue = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return deleteValue;
    }

    /**
     * Получение элемента.
     * Метод принимает значение индекса.
     * В методах, где используется индекс нужно делать валидацию.
     * Индекс должен находиться в рамках добавленных элементов.
     * Для проверки индекса используем метод Objects.checkIndex().
     * @param index значение индекса.
     * @return возвращает значение элемента по индексу.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * Количество элементов.
     * 
     * @return возвращает количество элементов в массиве.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Итератор
     * @return возвращает итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             * количество ожидаемых модификаций контейнера(при добавлении, удалении элементов)
             */
           private int expectedModCount = modCount;

            /**
             * курсор итератора
             */
           private int point;


            /**
             * Проверка есть ли следующий элемент.
             * Проверяем была ли на момент итерирования изменена коллекция.
             * Если было изменение вылетает исключение. Это называется fail-fast поведение.
             * @return возвращает true,если есть следующий элемент.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            /**
             * Получение следующего элемента.
             * Если итератор "уперся" получаем исключение.
             * @return возвращает элемент массива.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}

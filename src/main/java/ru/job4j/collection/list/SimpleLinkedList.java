package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс описывает реализацию связанного списка, аналог LinkedList
 *
 * @author Aleksey Novoselov
 * @version 1.0
 */
public class SimpleLinkedList<E> implements List<E> {

    /**
     * Количество элементов
     */
    private int size;

    /**
     * Первый узел в списке
     */
    private Node<E> first;

    /**
     * Последний узел в списке
     */
    private Node<E> last;

    /**
     * Количество модификаций в списке(при добавлении элемента)
     */
    private int modCount;


    private static class Node<E> {
        /**
         * Каждый узел содержит в себе элемент, который мы добавили,а также
         * ссылку на предыдущий и следующий узел.
         */
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Добавление элемента в список.
     * Метод добавляет элемент в конец списка.
     * @param value элемент,который необходимо добавить в список.
     */
    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Поиск элемента по индексу.
     * Метод осуществляет перебор элементов в узлах(нодах) до указанного индекса.
     * В зависимости от расположения индекса(относительно size) поиск осуществляется с первого или последнего нода.
     * @param index индекс элемента
     * @return возвращает элемент
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl;
        if (index < (size >> 1)) {
            rsl = first;
            for (int i = 0; i < index; i++) {
                rsl = rsl.next;
            }
        } else {
            rsl = last;
            for (int i = size - 1; i > index; i--) {
                rsl = rsl.prev;
            }
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            /**
             * Первый узел.
             */
            Node<E> current = first;

            /**
             * количество ожидаемых модификаций контейнера(при добавлении, удалении элементов)
             */
            private int expectedModCount = modCount;

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
                return current != null;
            }

            /**
             * Получение следующего элемента.
             * Если итератор "уперся" получаем исключение.
             * @return возвращает элемент узла.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = current.item;
                current = current.next;
                return item;
            }
        };
    }
}

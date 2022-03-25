package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс описывает реализацию методов в односвязном списке.
 * @author Aleksey Novoselov
 * @version 1.0
 * @param <T>
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод реализует добавление первого элемента.
     * @param value добавляемый элемент.
     */
    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    /**
     * Метод реализует удаление первого элемента из односвязного списка.
     * Создаем временную ноду,куда запишем первую ноду списка.
     * Далее удалим первую ноду(запишем в head следующую ноду)
     * В удаляемом узле обнуляем ссылку на хранимое значение и ссылку на следующий узел.
     * @return возвращает первый элемент односвязного списка.
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tempNode = head;
        head = head.next;
        T value = tempNode.value;
        tempNode.value = null;
        tempNode.next = null;
        return value;
    }

    /**
     * Метод реализует задачу перевернуть односвязный список.
     * Сначала делаем валидацию(список пустой или имеет один элемент).
     * "Запишем заранее" в следующий элемент(Node<T> next) значение текущего.следующего.
     * Затем "разворачиваем ссылку" для текущего элемента. В текущий.следующий записываем значение из предыдущего.
     * Затем в качестве текущего значения принимаем текущий.следующий. И начинаем также работать с его ссылками.
     * После выполнения цикла головой списка становится значение в previous.
     * @return возвращает true после того как список перевернут.
     */
    public boolean revert() {
        boolean result = (head != null && head.next != null);

        if (result) {
        Node<T> previous = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }
    return result;
}

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

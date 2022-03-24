package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс описывает работу используя очередь Queue.
 * Применяем "очередь на двух стеках"
 * FIFO first input first output(первый пришел, первый ушел)
 * @param <T>
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод возвращает первое значение и удаляет его из коллекции.
     * Последовательно перекладывать элементы из стек in в out.
     * @return возвращаем, что положили первым
     */
    public T poll() {
        if (in.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод помещает значение в конец.
     * Помещаем в стек in.
     * @param value значение.
     */
    public void push(T value) {
        in.push(value);
    }
}

package ru.job4j.collection;

/**
 * Класс описывает работу методов с контейнером Stack
 *
 * @param <T>
 */
public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * Метод возвращает значение и удаляет его из коллекции.
     *
     * @return возвращает значение.
     */
    public T pop() {
        return linked.deleteFirst();
    }


    /**
     * Метод принимает значение и возвращает его в коллекцию.
     *
     * @param value значение
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}

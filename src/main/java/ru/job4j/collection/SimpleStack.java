package ru.job4j.collection;

/**
 * Класс описывает работу методов с контейнером Stack
 *
 * @param <T>
 */
public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * размер списка
     */
    private int size;

    /**
     * Метод возвращает значение и удаляет его из коллекции.
     * При этом уменьшаем размер коллекции.
     * @return возвращает значение.
     */
    public T pop() {
        size--;
        return linked.deleteFirst();
    }


    /**
     * Метод принимает значение и возвращает его в коллекцию.
     * Увеличиваем размер коллекции.
     * @param value значение
     */
    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    /**
     * Метод проверяет размер коллекции(пустая или нет).
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
}

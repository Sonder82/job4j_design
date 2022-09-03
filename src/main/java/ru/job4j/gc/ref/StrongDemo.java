package ru.job4j.gc.ref;

import java.util.concurrent.TimeUnit;

public class StrongDemo {

    public static void main(String[] args) throws InterruptedException {
        example1();
        example2();
    }

    /**
     * В методе создаем объекты и далее их за'null'яем.
     * Вызываем сборщик мусора и ждем некоторое время.
     * Объекты удаляются, т.к. ссылки на них null
     * @throws InterruptedException исключение
     */
    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * В методе мы создаем объекты вместе с вложенными.
     * Удаляя внешние объекты, удаляются и вложенные, несмотря на то, что они не null.
     * @throws InterruptedException исключение
     */
    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }
}

package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftDemo {

    public static void main(String[] args) {
        example1();
        example2();
    }

    /**
     * В методе несмотря на то, что мы за'null'уляем сильную ссылку,
     * на объект остается безопасная ссылки,
     * а это значит объект будет удален только при нехватке памяти.
      */
    private static void example1() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        System.out.println(soft.get());
    }

    /**
     * В методе мы создаем много объектов, но на них ссылается безопасная ссылка.
     * Если мы создаем большое количество объектов при малом хипе, мы увидим,
     * что объекты начнут удаляться, т.к. станет не хватать памяти.
      */
    private static void example2() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            objects.add(new SoftReference<Object>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }
}

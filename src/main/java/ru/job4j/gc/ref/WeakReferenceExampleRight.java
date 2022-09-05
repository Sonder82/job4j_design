package ru.job4j.gc.ref;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class WeakReferenceExampleRight {

    public static void main(String[] args) throws InterruptedException {
        References ref = new References("Hello") {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<References> weakRef = new WeakReference<>(ref);
        References refStr = weakRef.get();
        ref = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        if (refStr != null) {
            System.out.println(weakRef.get().getString());
        } else {
            System.out.println("Garbage collect reference");
        }
    }
}

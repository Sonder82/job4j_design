package ru.job4j.gc.ref;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class WeakReferenceExampleWrong {

    public static void main(String[] args) throws InterruptedException {
        References ref = new References("Hello") {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<References> weakRef = new WeakReference<>(ref);
        ref = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        References refStr = weakRef.get();
        if (refStr != null) {
            System.out.println(weakRef.get().getString());
        } else {
            System.out.println("Garbage collect reference");
        }
    }
}


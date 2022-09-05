package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class SoftReferenceExample {

    public static void main(String[] args) throws InterruptedException {
        References ref = new References("Hello") {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        SoftReference<References> softRef = new SoftReference<>(ref);
        References refStr = softRef.get();
        ref = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        if (refStr != null) {
            System.out.println(softRef.get().getString());
        } else {
            System.out.println("Garbage collect reference");
        }
    }
}

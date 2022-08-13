package ru.job4j.gc.prof;

import java.util.concurrent.TimeUnit;

/**
 * 1. Эксперименты с различными GC.
 * В этом задании мы будем профилировать
 * различные типы сортировки.
 *
 * В данном классе описывается
 * cортировка пузырьком.
 */

public class BubbleSort implements Sort {

    @Override
    public boolean sort(Data data) {
        int[] array = data.getClone();
        long start = System.nanoTime();
        sort(array);
        long end = System.nanoTime();
        long timeInMillis = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);
        System.out.println("Time spend in ms: " + timeInMillis);
        return true;
    }

    public  void sort(int[] array) {
        int out, in;
        for (out = array.length - 1; out >= 1; out--) {
            for (in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    swap(array, in, in + 1);
                }
            }
        }
    }

    public void swap(int[] array, int in, int in1) {
        int temp = array[in];
        array[in] = array[in1];
        array[in1] = temp;
    }
}

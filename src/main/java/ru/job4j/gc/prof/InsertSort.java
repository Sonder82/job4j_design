package ru.job4j.gc.prof;

import java.util.concurrent.TimeUnit;

/**
 * 1. Эксперименты с различными GC.
 * В этом задании мы будем профилировать
 * различные типы сортировки.
 *
 * В данном классе описывается
 * cортировка методом вставки.
 *
 */

public class InsertSort implements Sort {

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

    private  void sort(int[] array) {
        int in, out;
        for (out = 1; out < array.length; out++) {
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
    }
}

package ru.job4j.ood.lsp.parking;

public class Truck implements Vehicle {

    private static final int SIZE_PASSENGER_CAR = 1;
    private int size;

    public boolean checkSize(int size) {
        if (size <= SIZE_PASSENGER_CAR) {
            throw new IllegalArgumentException("Size must be more then 1");
        }
        return true;
    }

    @Override
    public int sizeForParkPlace() {
        return size;
    }
}

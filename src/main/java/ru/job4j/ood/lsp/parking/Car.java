package ru.job4j.ood.lsp.parking;

public class Car implements Vehicle {

    private static final int SIZE_PASSENGER_CAR = 1;

    @Override
    public int sizeForParkPlace() {
        return SIZE_PASSENGER_CAR;
    }
}

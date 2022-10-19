package ru.job4j.ood.lsp.parking;

/**
 * Грузовая машина может разместиться на месте,
 * предназначенном для грузовых машин,
 * либо на N парковочных мест для легковых машин, стоящих рядом.
 * Размер грузового а/м > 1.
 */
public class Truck implements Vehicle {

    private int size;

    public Truck(int size) {
        checkSize(size);
        this.size = size;
    }

    public boolean checkSize(int size) {
        if (size <= Car.SIZE_PASSENGER_CAR) {
            throw new IllegalArgumentException("Size must be more then 1");
        }
        return true;
    }

    @Override
    public int sizeForParkPlace() {
        return this.size;
    }
}

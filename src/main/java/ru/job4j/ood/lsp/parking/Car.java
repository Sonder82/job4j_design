package ru.job4j.ood.lsp.parking;

/**
 * Легковая машина может занять только место,
 * предназначенное для легковой машины.
 * Размер парковочного места для легкового а/м задано
 * константой и определено как 1.
 */
public class Car implements Vehicle {

    public static final int SIZE_PASSENGER_CAR = 1;

    @Override
    public int sizeForParkPlace() {
        return SIZE_PASSENGER_CAR;
    }
}

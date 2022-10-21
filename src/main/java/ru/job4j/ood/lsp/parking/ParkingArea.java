package ru.job4j.ood.lsp.parking;

import java.util.*;

public class ParkingArea implements Parking {

    private int countParkCar;
    private int countParkTruck;
    private HashSet<Vehicle> cars = new HashSet<>();
    private HashSet<Vehicle> trucks = new HashSet<>();

    public ParkingArea(int countParkCar, int countParkTruck) {
        this.countParkCar = countParkCar;
        this.countParkTruck = countParkTruck;
        HashSet<Vehicle> cars = new HashSet<>(countParkCar);
        HashSet<Vehicle> trucks = new HashSet<>(countParkTruck);
    }

    @Override
    public boolean add(Vehicle vehicle) {
        boolean result;
        if (countParkCar >= Car.SIZE_PASSENGER_CAR
                && vehicle.getSize() == Car.SIZE_PASSENGER_CAR) {
            cars.add(vehicle);
            countParkCar--;
            result = true;
        } else if (countParkTruck >= Car.SIZE_PASSENGER_CAR
                && vehicle.getSize() > Car.SIZE_PASSENGER_CAR) {
            trucks.add(vehicle);
            countParkTruck--;
            result = true;
        } else {
            result = checkUseCarPlace(vehicle);
        }
        return result;
    }

    private boolean checkUseCarPlace(Vehicle vehicle) {
        boolean result = false;
        if (countParkTruck == 0 && countParkCar >= vehicle.getSize()) {
            trucks.add(vehicle);
            countParkCar = countParkCar - vehicle.getSize();
            result = true;
        }
        return result;
    }

    @Override
    public HashSet<Vehicle> getAll() {
        HashSet<Vehicle> union = new HashSet<>(cars);
        union.addAll(trucks);
        return union;
    }
}

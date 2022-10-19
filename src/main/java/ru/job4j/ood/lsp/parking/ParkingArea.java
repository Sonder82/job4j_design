package ru.job4j.ood.lsp.parking;

import java.util.*;

public class ParkingArea implements Parking {
    private int countParkCar;
    private int countParkTruck;
    HashSet<Vehicle> cars = new HashSet<>();
    HashSet<Vehicle> tracks = new HashSet<>();

    public ParkingArea(int countParkCar, int countParkTruck) {
        this.countParkCar = countParkCar;
        this.countParkTruck = countParkTruck;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }
}

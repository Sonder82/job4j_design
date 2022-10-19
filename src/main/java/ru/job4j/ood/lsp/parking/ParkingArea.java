package ru.job4j.ood.lsp.parking;

import java.util.*;

public class ParkingArea implements Parking {
    HashSet<Vehicle> cars = new HashSet<>();
    HashSet<Vehicle> tracks = new HashSet<>();

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }
}

package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    boolean add(Vehicle vehicle);
    List<Vehicle> getAll();
}

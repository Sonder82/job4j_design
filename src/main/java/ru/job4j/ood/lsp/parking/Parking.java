package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * В интерфейсе представлены методы для добавления
 * транспортного средства на парковку и получения
 * листа всех ТС на парковке.
 *
 */
public interface Parking {
    boolean add(Vehicle vehicle);
    List<Vehicle> getAll();
}

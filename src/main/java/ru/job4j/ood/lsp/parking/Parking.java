package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    void add(Automobile automobile);
    List<Automobile> getAll();
}

package ru.job4j.ood.lsp.parking;

public class Automobile {
    private String numberPlate;
    private int size;

    public Automobile(String numberPlate, int size) {
        this.numberPlate = numberPlate;
        this.size = size;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

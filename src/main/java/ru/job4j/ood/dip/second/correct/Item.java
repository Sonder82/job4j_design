package ru.job4j.ood.dip.second.correct;

public class Item {
    private int number;
    private String name;

    public Item(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}

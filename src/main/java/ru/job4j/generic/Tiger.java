package ru.job4j.generic;

public class Tiger extends Predator {
    private int weight;


    public Tiger(String name, int age, String habitat, int weight) {
        super(name, age, habitat);
        this.weight = weight;
    }
}

package ru.job4j.generics;

public class Predator extends Animal {
    private String habitat;

    public Predator(String name, int age, String habitat) {
        super(name, age);
        this.habitat = habitat;
    }
}

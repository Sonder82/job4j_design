package ru.job4j.ood.dip.second.incorrect;

/**
 * В аргументы метода передаются реализации.
 * Это нарушает принцип DIP.
 */
public class Service {

    public Item createRandomItem(GeneratorNumber generatorNumber, String name) {
        return new Item(generatorNumber.generateNumber(10), name);
    }
}

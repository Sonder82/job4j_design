package ru.job4j.ood.dip.second.correct;

/**
 * В метод передали абстракции.
 * Сделали зависимость от них.
 */
public class Service {

    public Item createRandomItem(GeneratorNumbers generator, GeneratorName generatorName) {
        return new Item(generator.generate(10), generatorName.toString());
    }
}

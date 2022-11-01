package ru.job4j.ood.dip.second.incorrect;

import java.util.Random;

public class GeneratorNumber {
    public int generateNumber(int num) {
        return (int) (Math.random() * ++num);
    }
}

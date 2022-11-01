package ru.job4j.ood.dip.second.correct;

public class SimpleGeneratorNumber implements GeneratorNumbers {
    @Override
    public int generate(int num) {

       return (int) (Math.random() * ++num);
    }
}

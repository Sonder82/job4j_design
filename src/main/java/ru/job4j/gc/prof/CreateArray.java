package ru.job4j.gc.prof;

import java.util.Random;

public class CreateArray implements UserAction {

    private final Output out;

    public CreateArray(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create an array";
    }

    @Override
    public boolean execute(Input input, Data data) {
        out.println("Create an new array");
        Random random = new Random();
        data.insert(250000);
        return true;
    }
}

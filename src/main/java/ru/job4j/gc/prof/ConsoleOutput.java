package ru.job4j.gc.prof;

public class ConsoleOutput implements Output {

    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }
}

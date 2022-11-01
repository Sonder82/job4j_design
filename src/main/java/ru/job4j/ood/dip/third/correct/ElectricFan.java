package ru.job4j.ood.dip.third.correct;

public class ElectricFan implements Switchable {
    @Override
    public void turnOn() {
        System.out.println("Fun: Fun turned on");
    }

    @Override
    public void turnOff() {
        System.out.println("Fun: Fun turned off");
    }
}

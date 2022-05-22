package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean fourWheelDrive;
    private final int run;
    private final String color;
    private final Registration registration;
    private final String[] statuses;

    public Car(boolean fourWheelDrive, int run, String color, Registration registration, String[] statuses) {
        this.fourWheelDrive = fourWheelDrive;
        this.run = run;
        this.color = color;
        this.registration = registration;
        this.statuses = statuses;
    }

    public boolean isFourWheelDrive() {
        return fourWheelDrive;
    }

    public int getRun() {
        return run;
    }

    public String getColor() {
        return color;
    }

    public Registration getRegistration() {
        return registration;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Car{" + "fourWheelDrive=" + fourWheelDrive + ", run=" + run + ", color='" + color
                + '\'' + ", registration=" + registration + ", statuses=" + Arrays.toString(statuses) + '}';
    }
}

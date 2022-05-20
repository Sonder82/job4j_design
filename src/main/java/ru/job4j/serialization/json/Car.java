package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.json.Registration;

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

    @Override
    public String toString() {
        return "Car{" + "fourWheelDrive=" + fourWheelDrive + ", run=" + run + ", color='" + color
                + '\'' + ", registration=" + registration + ", statuses=" + Arrays.toString(statuses) + '}';
    }
}

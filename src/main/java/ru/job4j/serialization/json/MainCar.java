package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainCar {
    public static void main(String[] args) {
        Car car = new Car(true, 2587, "black", new Registration(96, "a777aa"),
                new String[]{"OnSale", "UnderWarranty"});

        /**
         * Преобразуем объект person в json-строку.
         */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        /**
         * Модифицируем json-строку.
         */
        final String carJson =
                "{"
                        + "\"fourWheelDrive\":false,"
                        + "\"run\":35065,"
                        + "\"registration\":"
                        + "{"
                        + "\"regionCode\":\"96\","
                        + "\"numberPlate\":\"a789ak\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Reserved\",\"UnderWarranty\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}

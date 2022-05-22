package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainCar {
    public static void main(String[] args) {
        Car car = new Car(true, 2587, "black", new Registration(96, "a777aa"),
                new String[]{"OnSale", "UnderWarranty"});

        /*
          Преобразуем объект car в json-строку.
         */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        /*
          Модифицируем json-строку.
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

        /*
          Преобразуем объект Registration в JSONObject из json-строки
         */
        JSONObject jsonRegistration = new JSONObject("{"
                + "\"regionCode\":\"96\","
                + "\"numberPlate\":\"a789ak\""
                + "}");
        System.out.println(jsonRegistration);

        /*
           JSONArray из ArrayList
         */
        List<String> list = new ArrayList<>();
        list.add("OnSale");
        list.add("UnderWarranty");
        JSONArray jsonStatuses = new JSONArray(list);
        System.out.println(jsonStatuses);

        /*
           JSONObject напрямую методом put
         */
        final Car carModVer = new Car(true, 35065, "black",
                new Registration(96, "a789ak"), new String[]{"OnSale", "UnderWarranty"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fourWheelDrive", carModVer.isFourWheelDrive());
        jsonObject.put("run", carModVer.getRun());
        jsonObject.put("color", carModVer.getColor());
        jsonObject.put("registration", jsonRegistration);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект carModVer в json-строку */
        System.out.println(new JSONObject(carModVer).toString());
    }
}

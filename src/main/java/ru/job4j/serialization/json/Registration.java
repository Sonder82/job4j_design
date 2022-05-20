package ru.job4j.serialization.json;

import ru.job4j.serialization.Contact;

import java.io.*;
import java.nio.file.Files;

public class Registration implements Serializable {
    private final int regionCode;
    private final String numberPlate;

    public Registration(int regionCode, String numberPlate) {
        this.regionCode = regionCode;
        this.numberPlate = numberPlate;
    }

    public int getRegionCode() {
        return regionCode;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    @Override
    public String toString() {
        return "Registration{" + "regionCode=" + regionCode + ", numberPlate='" + numberPlate + '\'' + '}';
    }
}

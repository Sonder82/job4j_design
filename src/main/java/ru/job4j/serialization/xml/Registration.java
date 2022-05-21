package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "registration")
@XmlAccessorType(XmlAccessType.FIELD)
public class Registration {

    @XmlAttribute
    private int regionCode;
    private String numberPlate;

    public Registration() {
    }

    public Registration(int regionCode, String numberPlate) {
        this.regionCode = regionCode;
        this.numberPlate = numberPlate;
    }

    @Override
    public String toString() {
        return "Registration{" + "regionCode=" + regionCode + ", numberPlate='" + numberPlate + '\'' + '}';
    }
}

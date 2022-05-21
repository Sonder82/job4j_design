package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean fourWheelDrive;

    @XmlAttribute
    private int run;

    @XmlAttribute
    private String color;
    private Registration registration;

    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Car() { }

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

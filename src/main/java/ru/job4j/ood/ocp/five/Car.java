package ru.job4j.ood.ocp.five;

public class Car implements Delivery {

        @Override
        public void deliveryType() {
            System.out.println("Доставка автомобилем");
        }

        public void rentCar(Car car) {
            System.out.println("Автомобиль для аренды");
        }
    }

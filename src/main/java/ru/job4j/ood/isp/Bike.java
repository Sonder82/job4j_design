package ru.job4j.ood.isp;

/**
 * Нарушение принципа ISP.
 * Ошибка вызвана добавлением в интерфейс Vehicle,
 * метода openDoors.
 * Это привело к тому, что в классе Bike этот метод не используется.
 * Поскольку у мотоцикла нет дверей.
 * Для правильного решения необходимо разделение интерфейсов.
 */
public class Bike implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Drive the bike");
    }

    @Override
    public void stop() {
        System.out.println("Stop the bike");
    }

    @Override
    public void openDoors() {
        throw new UnsupportedOperationException("Bike hasn't doors");
    }
}

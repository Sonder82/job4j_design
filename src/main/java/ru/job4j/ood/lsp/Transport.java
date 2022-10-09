package ru.job4j.ood.lsp;

/**
 * В этом примере нарушено правило инвариантов.
 * Правило гласит, что все унаследованные условия базового класса должны быть сохранены и в подклассе.
 * Класс автомобиль имеет двигатель и может выполнять условия базового класса.
 * Класс велосипед не имеет двигатель и не может выполнить условие базового класса.
 */

public class Transport {
    Engine engine;

    public Transport(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void startEngine(Engine engine) {
        System.out.println("Запуск двигателя");
    }

    class Car extends Transport {
        public Car(Engine engine) {
            super(engine);
        }

        @Override
        public void startEngine(Engine engine) {
            super.startEngine(engine);
        }

        class Bicycle extends Transport {

            public Bicycle(Engine engine) {
                super(engine);
            }

            @Override
            public void startEngine(Engine engine) {
                super.startEngine(engine);
            }
        }
    }
}

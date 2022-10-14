package ru.job4j.ood.isp;

/**
 * Нарушение принципа ISP на примере заказа фастфуда.
 * Ошибка вызвана написанием интерфейса OrderService,
 * где все виды заказов поместили в одно место.
 * Причем заказы отличаются по функционалу.
 * Если мы создаем класс заказа бургеров, мы имплементируем интерфейс
 * и так получается что остальные заказы fri & combo использоваться не будут.
 * Приходится их поддерживать.
 * Для правильного решения необходимо разделение интерфейсов.
 */

public class BurgerOrderService implements OrderService {

    @Override
    public void orderBurger(int quantity) {
        System.out.println("Received order of " + quantity + " burgers");
    }

    @Override
    public void orderFries(int fries) {

        throw new UnsupportedOperationException("No fries in burger only order");
    }

    @Override
    public void orderCombo(int quantity, int fries) {

        throw new UnsupportedOperationException("No combo in burger only order");
    }
}

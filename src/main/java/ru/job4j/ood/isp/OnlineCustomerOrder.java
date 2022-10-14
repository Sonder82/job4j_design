package ru.job4j.ood.isp;

/**
 * Нарушение ISP на примере заказов в ресторане.
 * В интерфейс указано 5 методов. Это признак нарушения ISP.
 * Также часть методов в данном примере не использовалась.
 * Для исправления ситуации можно разделить на два интерфейса
 * PaymentInterface и OrderInterface.
 */

public class OnlineCustomerOrder implements DeliveryPayRestaurant {
    @Override
    public void onlineOrder() {
        System.out.println("Make online order");
    }

    @Override
    public void telephoneOrder() {
        throw new UnsupportedOperationException("Not Applicable for Online Order");
    }

    @Override
    public void walkInCustomerOrder() {
        throw new UnsupportedOperationException("Not Applicable for Online Order");
    }

    @Override
    public void payOnline() {
        System.out.println("Pay by credit card");
    }

    @Override
    public void payInPerson() {
        throw new UnsupportedOperationException("Not Applicable for Online Order");
    }
}

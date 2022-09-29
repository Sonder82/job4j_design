package ru.job4j.ood.ocp.second.right;

public class PaymentProcessor {
    public void processPayment(TypePay typePay) {
        typePay.acceptPayment();
    }
}

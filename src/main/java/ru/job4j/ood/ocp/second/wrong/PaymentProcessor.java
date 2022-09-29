package ru.job4j.ood.ocp.second.wrong;

public class PaymentProcessor {

    public void processPayment(PaymentMode paymentMode) {
        if (paymentMode == PaymentMode.CASH) {
            CashPayment cashPayment = new CashPayment();
            cashPayment.acceptPayment();
        } else if (paymentMode == PaymentMode.CREDIT_CARD) {
            CreditCardPayment creditCardPayment = new CreditCardPayment();
            creditCardPayment.acceptPayment();
        }
    }
}

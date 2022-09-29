package ru.job4j.ood.ocp.second.right;

public class CreditCardPayment implements TypePay {

    @Override
    public void acceptPayment() {
        System.out.println("Paid through credit card");
    }
}

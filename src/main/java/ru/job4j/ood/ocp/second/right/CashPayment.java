package ru.job4j.ood.ocp.second.right;

public class CashPayment implements TypePay {

    @Override
    public void acceptPayment() {
        System.out.println("Paid through cash payment");
    }
}

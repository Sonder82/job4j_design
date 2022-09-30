package ru.job4j.ood.ocp.fourth.right;

public class EmailStrategy implements SendingStrategy {

    @Override
    public void send(String msg) {
        System.out.println("Sending Email: " + msg);
    }
}

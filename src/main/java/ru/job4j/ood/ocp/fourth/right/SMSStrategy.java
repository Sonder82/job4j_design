package ru.job4j.ood.ocp.fourth.right;

public class SMSStrategy implements SendingStrategy {
    @Override
    public void send(String msg) {
        System.out.println("Sending SMS: " + msg);
    }
}

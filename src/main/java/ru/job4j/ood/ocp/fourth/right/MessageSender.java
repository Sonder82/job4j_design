package ru.job4j.ood.ocp.fourth.right;

public class MessageSender {
    private SendingStrategy strategy;

    public MessageSender(SendingStrategy strategy) {
        this.strategy = strategy;
    }

    public void send(String msg) {
        this.strategy.send(msg);
    }
}

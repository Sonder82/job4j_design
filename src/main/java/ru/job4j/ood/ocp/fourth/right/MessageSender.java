package ru.job4j.ood.ocp.fourth.right;

/**
 * Для исправления ситуации в классе MessageSender,применена абстракция(интерфейс SendingStrategy)
 * Поле представлено этой абстракцией.
 * Все это позволит расширить функционал не изменяя кода.
 */

public class MessageSender {
    private SendingStrategy strategy;

    public MessageSender(SendingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sendType(String msg) {
        this.strategy.send(msg);
    }
}

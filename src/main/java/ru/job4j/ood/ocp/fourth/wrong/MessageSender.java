package ru.job4j.ood.ocp.fourth.wrong;

public class MessageSender {
    public void send(String msg, String type) {
        if (type.equals(TypeMessage.SMS)) {
            System.out.println(TypeMessage.SMS + msg);
        } else if (type.equals(TypeMessage.EMAIL)) {
            System.out.println(TypeMessage.EMAIL + msg);
        }
    }
}

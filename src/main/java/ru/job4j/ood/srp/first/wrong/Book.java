package ru.job4j.ood.srp.first.wrong;

public class Book {

    private String text;
    private String name;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public void print() {
        System.out.println(text);
    }
}

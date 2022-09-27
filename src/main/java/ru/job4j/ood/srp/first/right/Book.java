package ru.job4j.ood.srp.first.right;

import java.util.Objects;

public class Book {

    private String text;
    private String name;

    public Book(String text, String name) {
        this.text = text;
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(text, book.text) && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, name);
    }
}

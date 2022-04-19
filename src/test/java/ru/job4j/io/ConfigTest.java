package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Aleksey"));
        assertThat(config.value("surName"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Aleksey"));
        assertThat(config.value("surName"), is("Novoselov"));
    }

    @Test(expected =  IllegalArgumentException.class)
    public void whenWrongWritePair() {
        String path = "./data/pair_illegal_argument.properties";
        Config config = new Config(path);
        config.load();
    }
}
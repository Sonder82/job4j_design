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

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongWritePair() {
        String path = "./data/pair_illegal_argument.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutEquals() {
        String path = "./data/pair_not_contains_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithComments() {
        String path = "./data/pair_with_multiEquals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Aleksey=surName=Novoselov"));
        assertThat(config.value("surName"), is("Novoselov=name=Aleksey"));
        assertThat(config.value("age"), is(Matchers.nullValue()));
    }
}
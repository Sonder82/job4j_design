package ru.job4j.io;

import org.junit.Test;
import ru.job4j.io.searchfiles.ArgsName;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertThat(jvm.get("request"), is("?msg=Exit="));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        jvm.get("Xms");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx="});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArrayLengthIsNull() {
        ArgsName jvm = ArgsName.of(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongKey() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-=512"});
    }
}
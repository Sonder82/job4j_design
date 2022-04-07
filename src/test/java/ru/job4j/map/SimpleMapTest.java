package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutAndGet() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        map.put(2, "second");
        assertThat(map.get(1), Is.is("first"));
        assertThat(map.get(2), Is.is("second"));
    }

    @Test
    public void whenPutAndGetNull() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        map.put(2, "second");
        String result = map.get(3);
        assertNull(result);
    }

    @Test
    public void whenRemoveByKey() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        map.put(2, "second");
        assertTrue(map.remove(1));
        assertFalse(map.remove(3));
    }

    @Test
    public void whenPutWithTheSameKey() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "first"));
        assertFalse(map.put(1, "second"));
        assertTrue(map.put(2, "second"));
    }

    @Test
    public void whenCheckIterator() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        assertThat(1, is(iterator.next()));
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        map.put(1, "first");
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Map<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }
}
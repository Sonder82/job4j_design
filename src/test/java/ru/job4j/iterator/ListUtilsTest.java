package ru.job4j.iterator;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void addBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfUseFilterFor() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 2));
        ListUtils.removeIf(input, x -> x == 2);
        assertThat(input, is(Arrays.asList(0, 1)));
    }

    @Test
    public void whenReplaceIfUseFilterAndReplaceableValue() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 2));
        ListUtils.replaceIf(input, x -> x == 2, 4);
        assertThat(input, is(Arrays.asList(0, 1, 4, 4)));
    }

    @Test
    public void whenRemoveElementsFromAnotherList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        List<Integer> element = new ArrayList<>(Arrays.asList(0, 1));
        ListUtils.removeAll(input, element);
        assertThat(input, is(Arrays.asList(2, 3)));
    }
}

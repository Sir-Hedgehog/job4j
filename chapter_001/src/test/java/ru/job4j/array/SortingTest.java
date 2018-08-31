package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SortingTest {
    @Test
    public void whenPlusTheSameNumber() {
        Sorting sort = new Sorting();
        int[] input1 = {0, 2, 3, 8, 29};
        int[] input2 = {1, 7, 10, 13, 16};
        int[] result = sort.plus(input1, input2);
        int[] expect = new int[] {0, 1, 2, 3, 7, 8, 10, 13, 16, 29};
        assertThat(result, is(expect));
    }

    @Test
    public void whenPlusTheDifferentNumber() {
        Sorting sort = new Sorting();
        int[] input1 = {2, 3, 4};
        int[] input2 = {1, 7, 10, 16};
        int[] result = sort.plus(input1, input2);
        int[] expect = new int[] {1, 2, 3, 4, 7, 10, 16};
        assertThat(result, is(expect));
    }

    @Test
    public void whenPlusTheDifferentNumber1() {
        Sorting sort = new Sorting();
        int[] input1 = {2, 3, 5, 99};
        int[] input2 = {1, 4, 7};
        int[] result = sort.plus(input1, input2);
        int[] expect = new int[] {1, 2, 3, 4, 5, 7, 99};
        assertThat(result, is(expect));
    }

    @Test
    public void whenTheFirstArrayIsEmpty() {
        Sorting sort = new Sorting();
        int[] input1 = {};
        int[] input2 = {1, 4, 7, 10, 16};
        int[] result = sort.plus(input1, input2);
        int[] expect = new int[] {1, 4, 7, 10, 16};
        assertThat(result, is(expect));
    }

    @Test
    public void whenTheSecondArrayIsEmpty() {
        Sorting sort = new Sorting();
        int[] input1 = {1, 2, 3};
        int[] input2 = {};
        int[] result = sort.plus(input1, input2);
        int[] expect = new int[] {1, 2, 3};
        assertThat(result, is(expect));
    }

    @Test
    public void whenTheBothArraysAreEmpty() {
        Sorting sort = new Sorting();
        int[] input1 = {};
        int[] input2 = {};
        int[] result = sort.plus(input1, input2);
        int[] expect = new int[] {};
        assertThat(result, is(expect));
    }
}

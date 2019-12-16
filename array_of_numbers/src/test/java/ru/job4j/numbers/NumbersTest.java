package ru.job4j.numbers;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 16.12.2019
 */

public class NumbersTest {
    @Test
    public void checkTheFirstIndex() {
        int current = 0;
        ArrayOfNumbers array = new ArrayOfNumbers();
        int[] input = {1, 2, 3};
        int[] result = array.generate(input, current);
        int[] expect = {1, 6, 6};
        assertThat(result, is(expect));
    }

    @Test
    public void checkTheSecondIndex() {
        int current = 1;
        ArrayOfNumbers array = new ArrayOfNumbers();
        int[] input = {1, 2, 3};
        int[] result = array.generate(input, current);
        int[] expect = {6, 2, 6};
        assertThat(result, is(expect));
    }

    @Test
    public void checkTheThirdIndex() {
        int current = 2;
        ArrayOfNumbers array = new ArrayOfNumbers();
        int[] input = {1, 2, 3};
        int[] result = array.generate(input, current);
        int[] expect = {6, 6, 3};
        assertThat(result, is(expect));
    }
}

package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenFirstNumberMoreSecondNumber() {
        BubbleSort bubble = new BubbleSort();
        int[] input = new int[] {7, 13, 4, 4, 8, 9, 0};
        int[] result = bubble.sort(input);
        int[] expect = new int[] {0, 4, 4, 7, 8, 9, 13};
        assertThat(result, is(expect));
    }
}

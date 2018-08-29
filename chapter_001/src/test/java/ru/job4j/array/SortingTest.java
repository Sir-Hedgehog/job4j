package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SortingTest {
    @Test
    public void whenFirstSortArrayPlusSecondSortArray() {
        Sorting sort = new Sorting();
        int[] input1 = {0, 2, 3, 4, 8, 29};
        int[] input2 = {1, 4, 7, 10, 13, 16};
        int[] result = sort.plus(input1, input2);
        int[] expect = new int[] {0, 1, 2, 3, 4, 4, 7, 8, 10, 13, 16, 29};
        assertThat(result, is(expect));
    }
}

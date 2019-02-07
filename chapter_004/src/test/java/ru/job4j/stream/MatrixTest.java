package ru.job4j.stream;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void whenThereIsMatrixThenReturnsList() {
        Matrix matrix = new Matrix();
        Integer[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> result = matrix.collect(array);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(9);
        assertThat(result, is(expected));
    }
}

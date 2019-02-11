package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiapasonTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = List.of(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquareFunctionThenSquareResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(4, 7, x -> x * x);
        List<Double> expected = List.of(16D, 25D, 36D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogFunctionThenLogResults() {
        Diapason function = new Diapason();
        List<Double> result = function.diapason(2, 5, x -> Math.log10(x));
        List<Double> expected = List.of(0.3010299956639812D, 0.47712125471966244D, 0.6020599913279624D);
        assertThat(result, is(expected));
    }

}

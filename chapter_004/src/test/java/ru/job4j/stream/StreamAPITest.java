package ru.job4j.stream;

import org.junit.Test;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StreamAPITest {
    @Test
    public void whenPerformOnNumbersThen() {
        StreamAPI api = new StreamAPI();
        Integer[] numbers = {1, 2, 6, 8, 9};
        Optional<Integer> result = api.perform(numbers);
        Optional<Integer> expected = Optional.of(104);
        assertThat(result, is(expected));
    }
}

package ru.job4j.stream;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 15.02.2019
 */

class StreamAPI {
    Optional<Integer> perform(Integer[] numbers) {
        return Arrays.stream(numbers)
                .filter(number -> number % 2 == 0)
                .map(x -> x * x)
                .reduce((x, y) -> x + y);
    }
}

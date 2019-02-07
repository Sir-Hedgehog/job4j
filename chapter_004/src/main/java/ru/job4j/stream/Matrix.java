package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 07.02.2019
 */

class Matrix {
    List<Integer> collect(Integer[][] matrix) {
        return Arrays.stream(matrix).flatMap(array -> Arrays.stream(array)).collect(Collectors.toList());
    }
}

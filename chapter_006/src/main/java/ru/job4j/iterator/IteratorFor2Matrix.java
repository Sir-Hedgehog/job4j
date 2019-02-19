package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 18.02.2019
 */

class IteratorFor2Matrix implements Iterator {
    private final int[][] matrix;
    private int in = 0;
    private int out = 0;

    IteratorFor2Matrix(final int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return matrix.length - 1 > out;
    }

    @Override
    public Integer next() {
        if (matrix[out].length == in) {
            out++;
            in = 0;
        }
        return matrix[out][in++];
    }
}

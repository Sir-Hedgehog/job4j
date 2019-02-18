package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 18.02.2019
 */

class EvenIterator implements Iterator {
    private int[] numbers;
    private int parity = 0;

    EvenIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int index = parity; index < numbers.length; index++) {
            if (numbers[index] % 2 == 0) {
                result = true;
                parity = index;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (numbers[parity] % 2 != 0) {
            parity++;
        } else {
            parity++;
        }
        return numbers[parity - 1];
    }
}

package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 20.02.2019
 */

class Converter {
    private Iterator<Integer> current;
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                boolean result = true;
                while (current == null || !current.hasNext()) {
                    if (!it.hasNext()) {
                        result = false;
                        break;
                    }
                    current = it.next();
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return current.next();
            }
        };
    }
}

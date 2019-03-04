package ru.job4j.set;

import ru.job4j.list.SimpleDynamicArray;
import java.util.Iterator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 04.03.2019
 */

public class SimpleSet<E> implements Iterable<E> {
    private SimpleDynamicArray<E> array;

    public SimpleSet(SimpleDynamicArray<E> array) {
        this.array = array;
    }

    public void add(E value) {
        if (array.getSize() == 0) {
            array.add(value);
        }
        if (array.getSize() >= 1 && !this.contains(value)) {
            array.add(value);
        }
    }

    private boolean contains(E value) {
        boolean result = false;
        for (final E element : array) {
            if (element == value || value != null && value.equals(element)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}

package ru.job4j.comparator;

import java.util.Comparator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 25.12.2018
 */

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        char[] first = left.toCharArray();
        char[] second = right.toCharArray();
        int size = first.length;
        if (first.length > second.length) {
            size = second.length;
        }
        for (int index = 0; index < size; index++) {
            if (first[index] > second[index]) {
                result = 1;
                break;
            } else if (first[index] < second[index]) {
                result = -1;
                break;
            }
        }
        if (result == 0 && first.length > second.length) {
            result = 1;
        } else if (result == 0 && first.length < second.length) {
            result = -1;
        }
        return result;
    }
}

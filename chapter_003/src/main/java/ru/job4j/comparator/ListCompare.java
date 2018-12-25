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
        int size = left.length();
        if (left.length() > right.length()) {
            size = right.length();
        }
        for (int index = 0; index < size; index++) {
            result = Character.compare(left.charAt(index), right.charAt(index));
            if (result > 0 || result < 0) {
                break;
            }
        }
        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}

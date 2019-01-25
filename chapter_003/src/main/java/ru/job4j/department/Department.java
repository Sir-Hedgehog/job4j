package ru.job4j.department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 25.01.2019
 */

class Department {
     List<String> toList(String[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    List<String> sortNaturalOrder(List<String> input) {
        input.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String first, String second) {
                        return first.compareTo(second);
                    }
                }
        );
        return input;
    }

    List<String> sortReverseOrder(List<String> input) {
        input.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String first, String second) {
                        return second.compareTo(first);
                    }
                }
        );
        return input;
    }
}

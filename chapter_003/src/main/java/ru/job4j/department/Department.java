package ru.job4j.department;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 10.02.2019
 */

class Department {
    TreeSet<String> naturalOrder = new TreeSet<>();
    TreeSet<String> reversedOrder = new TreeSet<>((first, second) -> {
        int result = second.compareTo(first);
        if (second.length() == first.length()) {
            result = 0;
        }
        return result;
    });

    public void add(String department) {
        String[] split = department.split("\\\\");
        StringBuilder current = new StringBuilder();
        for (String line : split) {
            current = current.append(line);
            naturalOrder.add(current.toString());
            reversedOrder.add(current.toString());
            current = current.append("\\");
        }
    }
}

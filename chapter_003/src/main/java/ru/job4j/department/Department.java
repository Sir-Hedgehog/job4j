package ru.job4j.department;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 13.02.2019
 */

class Department {
    TreeSet<String> naturalOrder = new TreeSet<>();
    TreeSet<String> reversedOrder = new TreeSet<>((first, second) -> {
        int result = second.compareTo(first);
        if (result == 0) {
            result = Integer.compare(first.length(), second.length());
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

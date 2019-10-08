package ru.job4j.scripts2;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 08.10.2019
 */

public class TrueOrder {
    /**
     * Метод выстраивает отсортированный список
     * @param unsorted - неотсортированный список скриптов
     * @return - отсортированный список скриптов
     */
    public List<Integer> order(Map<Integer, List<Integer>> unsorted, Integer script) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new LinkedHashSet<>();
        result.add(script);
        for (List<Integer> current : unsorted.values()) {
            if (!current.isEmpty()) {
                set.addAll(current);
            }
        }
        for (Integer current : unsorted.keySet()) {
            if (!set.contains(current)) {
                result.add(current);
            }
        }
        for (Integer current : unsorted.keySet()) {
            if (set.contains(current)) {
                result.add(current);
            }
        }
        return result;
    }
}

package ru.job4j.scripts;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 12.10.2019
 */

public class TrueOrder {
    /**
     * Метод выстраивает отсортированный список скриптов
     * @param unsorted - неотсортированная картотека скриптов
     * @return - отсортированный список скриптов
     */
    public List<Integer> order(Map<Integer, List<Integer>> unsorted, Integer script) {
        Set<Integer> intermediary = new LinkedHashSet<>();
        Deque<Integer> deque = new LinkedList<>();
        intermediary.add(script);
        deque.add(script);
        while (!unsorted.isEmpty()) {
            Integer current = deque.pollFirst();
            if (unsorted.containsKey(current)) {
                deque.addAll(unsorted.get(current));
                intermediary.addAll(unsorted.get(current));
                unsorted.remove(current);
            }
        }
        return new ArrayList<>(intermediary);
    }
}

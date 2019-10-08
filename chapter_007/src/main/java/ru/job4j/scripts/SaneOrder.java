package ru.job4j.scripts;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 23.09.2019
 */

public class SaneOrder {
    private VulnerabilityScript root;

    public SaneOrder(VulnerabilityScript root) {
        this.root = root;
    }

    /**
     * Метод выстраивает отсортированную очередь (используется алгоритм обхода в ширину)
     * @param unsorted - неотсортированный список скриптов
     * @return - отсортированный список скриптов
     */

    public Set<Integer> order(List<VulnerabilityScript> unsorted) {
        Deque<VulnerabilityScript> deque = new LinkedList<>();
        Set<Integer> result = new LinkedHashSet<>();
        result.add(this.root.getScriptId());
        deque.add(this.root);
        while (!unsorted.isEmpty()) {
            VulnerabilityScript script = deque.pollFirst();
            for (int index = 0; index < unsorted.size(); index++) {
                if (!Objects.requireNonNull(script).getDependencies().isEmpty()
                        && script.getDependencies().contains(unsorted.get(index).getScriptId())) {
                    result.add(unsorted.get(index).getScriptId());
                    deque.add(unsorted.get(index));
                    unsorted.remove(index);
                    index--;
                }
            }
        }
        return result;
    }
}



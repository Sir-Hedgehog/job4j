package ru.job4j.scripts;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 08.09.2019
 */

public class SaneOrder  {
    private List<VulnerabilityScript> result = new ArrayList<>();

    public String order(List<VulnerabilityScript> list) {
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).getDependencies().isEmpty()) {
                result.add(0, list.get(index));
                list.remove(index);
                index--;
            }
        }
        while (!list.isEmpty()) {
            for (int out = 0; out < result.size(); out++) {
                for (int in = 0; in < list.size(); in++) {
                    int current = result.get(out).getScriptId();
                    list.get(in).getDependencies().removeIf(x -> current == x);
                    if (list.get(in).getDependencies().isEmpty()) {
                        result.add(0, list.get(in));
                        list.remove(in);
                        break;
                    }
                }
            }
        }
        return result.toString();
    }
}

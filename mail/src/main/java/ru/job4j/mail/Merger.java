package ru.job4j.mail;

import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 05.11.2019
 */

public class Merger {
    /**
     * Метод объединяет почты одного и того же пользователя
     * @param map - неотсортированная картотека почтовых адресов
     * @return - отсортированный список почтовых адресов
     */
    public Map<String, TreeSet<String>> merge(ConcurrentHashMap<String, TreeSet<String>> map) {
        Map<String, TreeSet<String>> result = new ConcurrentHashMap<>();
        for (Map.Entry<String, TreeSet<String>> outer : map.entrySet()) {
            for (String mail : outer.getValue()) {
                if (!mail.contains("@")) {
                    return null;
                }
            }
        }
        for (Map.Entry<String, TreeSet<String>> outer : map.entrySet()) {
            for (Map.Entry<String, TreeSet<String>> inner : map.entrySet()) {
                if (!(outer.getKey().equals(inner.getKey()))) {
                    for (String mail : inner.getValue()) {
                        if (outer.getValue().contains(mail)) {
                            result.put(outer.getKey(), outer.getValue());
                            result.get(outer.getKey()).addAll(inner.getValue());
                            map.entrySet().remove(inner);
                            break;
                        } else {
                            result.put(outer.getKey(), outer.getValue());
                            map.entrySet().remove(inner);
                        }
                    }
                }
            }
        }
        return result;
    }
}

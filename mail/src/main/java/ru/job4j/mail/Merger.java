package ru.job4j.mail;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 14.11.2019
 */

public class Merger {
    private String mail;

    public Merger(String mail) {
        this.mail = mail;
    }

    /**
     * Метод объединяет почты одного и того же пользователя
     * @param mapOfMails - неотсортированная картотека почтовых адресов
     * @return - отсортированный список почтовых адресов
     */

    public Map<String, LinkedHashSet<String>> merge(ConcurrentHashMap<String, LinkedHashSet<String>> mapOfMails) {
        Map<String, LinkedHashSet<String>> result = new HashMap<>();
        LinkedHashSet<String> deque = new LinkedHashSet<>();
        String key = "";
        deque.add(this.mail);
        for (Map.Entry<String, LinkedHashSet<String>> mails : mapOfMails.entrySet()) {
            for (String mail : mails.getValue()) {
                if (!mail.contains("@")) {
                    return null;
                }
            }
        }
        while (!mapOfMails.isEmpty()) {
            String current = deque.iterator().next();
            deque.remove(current);
            LinkedHashSet<String> intermediary = new LinkedHashSet<>();
            for (Map.Entry<String, LinkedHashSet<String>> mails : mapOfMails.entrySet()) {
                if (mails.getValue().contains(current)) {
                    intermediary.addAll(mails.getValue());
                    key = mails.getKey();
                    mapOfMails.remove(key);
                } else {
                    deque.addAll(mails.getValue());
                }
            }
            result.put(key, intermediary);
        }
        return result;
    }
}

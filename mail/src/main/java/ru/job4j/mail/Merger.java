package ru.job4j.mail;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 16.12.2019
 */

public class Merger {

    /**
     * Метод объединяет почты одного и того же пользователя
     * @param mapOfMails - неотсортированная картотека почтовых адресов
     * @return - отсортированный список почтовых адресов
     */

    public Map<String, Set<String>> merge(TreeMap<String, Set<String>> mapOfMails) {
        Map<String, Set<String>> result = new HashMap<>();
        Map<String, String> backward = new HashMap<>();
        for (Map.Entry<String, Set<String>> mails : mapOfMails.entrySet()) {
            String name = null;
            for (String element : mails.getValue()) {
                if (backward.containsKey(element)) {
                    name = backward.get(element);
                    break;
                }
            }
            if (name != null) {
                result.get(name).addAll(mails.getValue());
                for (String current : mails.getValue()) {
                    backward.put(current, name);
                }
            } else {
                name = mails.getKey();
                result.put(name, new HashSet<>());
                result.get(name).addAll(mails.getValue());
                for (String gr : mails.getValue()) {
                    backward.put(gr, name);
                }
            }
        }
        return result;
    }
}

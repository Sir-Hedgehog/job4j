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









        //ConcurrentHashMap<String, Set<String>> result = new ConcurrentHashMap<>();
        //Deque<String> deque = new LinkedList<>();
        //Set<String> deque = new LinkedHashSet<>();
        //String key = "";
        /*ConcurrentHashMap<String, LinkedHashSet<String>> result = new ConcurrentHashMap<>();
        //Deque<String> deque = new LinkedList<>();
        Set<String> deque = new LinkedHashSet<>();
        List<String> list = new ArrayList<>();
        String key = "";

        //LinkedHashSet<String> agent = new LinkedHashSet<>();
        //List<String> listOfAgents = new ArrayList<>();
        //List<String> listOfAbsent = new ArrayList<>();
        //List<String> list2 = new ArrayList<>(new LinkedHashSet<>(list1));
        deque.add(this.mail);
        for (Map.Entry<String, LinkedHashSet<String>> users : mapOfMails.entrySet()) {
            for (String mail : users.getValue()) {
                if (!mail.contains("@")) {
                    return null;
                }
            }
        }
        for (Map.Entry<String, LinkedHashSet<String>> mails : mapOfUsers.entrySet()) {
            if (!mails.getKey().contains("@")) {
                return null;
            }
        }
        while (!mapOfMails.isEmpty()) {
            String current = deque.iterator().next();
            deque.remove(current);
            LinkedHashSet<String> intermediary = new LinkedHashSet<>();
            LinkedHashSet<String> values = new LinkedHashSet<>();
            for (Map.Entry<String, LinkedHashSet<String>> users : mapOfUsers.entrySet()) {
                if (users.getKey().equals(current)) {
                    intermediary.addAll(users.getValue());
                } else {
                    list.addAll(users.getValue());
                }
            }
            for (String elements : list) {
                if (intermediary.contains(elements)) {

                }
            }
            for (Map.Entry<String, LinkedHashSet<String>> mails : mapOfMails.entrySet()) {
                if (intermediary.contains(mails.getKey())) {
                    values.addAll(mails.getValue());
                    key = mails.getKey();
                    mapOfMails.remove(mails.getKey());
                } else {
                    deque.addAll(mails.getValue());
                }
            }
            result.put(key, values);
        }*/

        /*for (LinkedHashSet<String> users : mapOfUsers.values()) {
            //String current = deque.pollFirst();
            String current = deque.iterator().next();
            deque.remove(current);
            LinkedHashSet<String> intermediary = new LinkedHashSet<>();
            for (Map.Entry<String, LinkedHashSet<String>> mails : mapOfMails.entrySet()) {
                if (mails.getValue().contains(current) && users.contains(mails.getKey())) {
                    intermediary.addAll(mails.getValue());
                    key = mails.getKey();
                    mapOfMails.remove(key);
                } else {
                    deque.addAll(mails.getValue());
                }
            }
            if (result.isEmpty()) {
                result.put(key, intermediary);
            }
            for (Map.Entry<String, LinkedHashSet<String>> point : result.entrySet()) {
                if (point.getValue().contains(current)) {
                    intermediary.addAll(point.getValue());
                    result.put(point.getKey(), intermediary);
                    key = point.getKey();
                    break;
                }
            }

            result.put(key, intermediary);
            if (mapOfMails.isEmpty()) {
                break;
            }
        }*/
        //return result;

        /*while (!mapOfMails.isEmpty()) {
            //String current = deque.pollFirst();
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
            if (result.isEmpty()) {
                result.put(key, intermediary);
            }
            for (Map.Entry<String, LinkedHashSet<String>> point : result.entrySet()) {
                if (point.getValue().contains(current)) {
                    intermediary.addAll(point.getValue());
                    result.put(point.getKey(), intermediary);
                    key = point.getKey();
                    break;
                }
            }
            result.put(key, intermediary);
        }
        return result;*/

}

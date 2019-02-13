package ru.job4j.list;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 18.12.2018
 */

class SortUser {

    TreeSet<User> sort(List<User> list) {
        TreeSet<User> users = new TreeSet<>();
        for (User user : list) {
            users.add(user);
        }
        return users;
    }

    List<User> sortNameLength(List<User> input) {
        input.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User one, User two) {
                        Integer first = one.getName().length();
                        Integer second = two.getName().length();
                        return second.compareTo(first);
                    }
                }
        );
        return input;
    }

    List<User> sortByAllFields(List<User> input) {
        input.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User one, User two) {
                        return one.getName().compareTo(two.getName());
                    }
                }.thenComparing(new Comparator<User>() {
                    @Override
                    public int compare(User one, User two) {
                        return one.getAge().compareTo(two.getAge());
                    }
                })
        );
        return input;
    }
}

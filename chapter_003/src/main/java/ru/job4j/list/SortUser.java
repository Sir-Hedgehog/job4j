package ru.job4j.list;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 13.12.2018
 */

public class SortUser {
    public TreeSet<User> sort(List<User> list) {
        TreeSet<User> users = new TreeSet<>();
        for (User user : list) {
            users.add(user);
        }
        return users;
    }
}

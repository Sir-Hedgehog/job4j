package ru.job4j.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 15.03.2019
 */

public class Analysis {

    public Info differ(List<User> previous, List<User> current) {
        int removed = 0;
        int offered = 0;
        int varied = 0;
        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            if (!current.contains(user)) {
                removed++;
            }
            map.put(user.id, user.name);
        }
        for (User user : current) {
            if (!previous.contains(user)) {
                offered++;
            }
            if (map.containsKey(user.id) && !map.containsValue(user.name)) {
                varied++;
            }
        }
        return new Info(offered, varied, removed);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }
}

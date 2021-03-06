package ru.job4j.bank;

import java.util.Objects;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 22.01.2019
 */

public class User implements Comparable<User> {
    private String name;
    private String passport;

    User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    String getName() {
        return name;
    }

    String getPassport() {
        return passport;
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
        return passport.equals(user.passport)
                && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }

    @Override
    public String toString() {
        return "User {" + "name: " + name + ", passport: " + passport + '}';
    }

    @Override
    public int compareTo(User input) {
        return this.name.compareTo(input.name);
    }
}

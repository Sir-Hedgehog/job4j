package ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private GregorianCalendar birthday;

    public User(String name, int children, GregorianCalendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}

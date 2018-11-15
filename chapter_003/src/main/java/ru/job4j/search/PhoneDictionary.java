package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 15.11.2018
 */

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержит key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедших пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        if (this.persons.contains(key)) {
            for (int index = 0; index < this.persons.size(); index++) {
                result.add(this.persons.get(index));
            }
        }
        return result;
    }
}


package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 11.02.2018
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
    List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (var index = 0; index < this.persons.size(); index++) {
            Person person = this.persons.get(index);
            if (person.getName().contains(key)
                    || person.getSurname().contains(key)
                    || person.getPhone().contains(key)
                    || person.getAddress().contains(key)) {
                result.add(this.persons.get(index));
            }
        }
        return result;
    }
}


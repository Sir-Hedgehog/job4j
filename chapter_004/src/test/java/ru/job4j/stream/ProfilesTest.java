package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {
    @Test
    public void whenThereIsProfileThenShowsAddress() {
        Profiles profiles = new Profiles();
        List<Profile> list = new ArrayList<>();
        list.add(new Profile(new Address("Москва", "Старокачаловская", 145, 45)));
        list.add(new Profile(new Address("Курск", "Ленина", 15, 2)));
        list.add(new Profile(new Address("Санкт-Петербург", "Невская", 17, 7)));
        List<Address> result = profiles.collect(list);
        List<Address> expected = new ArrayList<>();
        expected.add(new Address("Москва", "Старокачаловская", 145, 45));
        expected.add(new Address("Курск", "Ленина", 15, 2));
        expected.add(new Address("Санкт-Петербург", "Невская", 17, 7));
        assertThat(result, is(expected));
    }

    @Test
    public void whenThereAreAddressesThenShowsUniqueAddresses() {
        Profiles profiles = new Profiles();
        List<Address> list = new ArrayList<>();
        list.add(new Address("Санкт-Петербург", "Невская", 17, 7));
        list.add(new Address("Москва", "Старокачаловская", 145, 45));
        list.add(new Address("Москва", "Старокачаловская", 145, 45));
        list.add(new Address("Курск", "Ленина", 15, 2));
        list.add(new Address("Санкт-Петербург", "Невская", 17, 7));
        List<Address> result = profiles.uniqueAndSort(list);
        List<Address> expected = new ArrayList<>();
        expected.add(new Address("Курск", "Ленина", 15, 2));
        expected.add(new Address("Москва", "Старокачаловская", 145, 45));
        expected.add(new Address("Санкт-Петербург", "Невская", 17, 7));
        assertThat(result, is(expected));
    }


}

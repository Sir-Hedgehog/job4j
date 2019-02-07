package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 07.02.2019
 */

class Profiles {
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(profile -> profile.getAddress()).collect(Collectors.toList());
    }

    List<Address> uniqueAndSort(List<Address> addresses) {
        return addresses.stream().distinct().sorted(Comparator.comparing(address -> address.getCity())).collect(Collectors.toList());
    }
}

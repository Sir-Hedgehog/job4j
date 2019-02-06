package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

class Profiles {
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(profile -> profile.getAddress()).collect(Collectors.toList());
    }
}

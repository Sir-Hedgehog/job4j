package ru.job4j.mail;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 16.12.2019
 */

public class MergerTest {
    @Test
    public void checkMergersOfTheFirstLevel() {
        Merger merger = new Merger();
        TreeMap<String, Set<String>> mapOfMails = new TreeMap<>();
        Set<String> user1 = new LinkedHashSet<>(Set.of("1@", "2@"));
        Set<String> user2 = new LinkedHashSet<>(Set.of("1@"));
        Set<String> user3 = new LinkedHashSet<>(Set.of("3@"));
        Set<String> user4 = new LinkedHashSet<>(Set.of("4@", "1@"));
        mapOfMails.put("us1", user1);
        mapOfMails.put("us2", user2);
        mapOfMails.put("us3", user3);
        mapOfMails.put("us4", user4);
        Map<String, Set<String>> result = merger.merge(mapOfMails);
        Map<String, Set<String>> expect = new HashMap<>(Map.of(
                "us1", new LinkedHashSet<>(Set.of("1@", "2@", "4@")),
                "us3", new LinkedHashSet<>(Set.of("3@"))));
        assertThat(result, is(expect));
    }

    @Test
    public void checkMergersOfTheSecondLevel() {
        Merger merger = new Merger();
        TreeMap<String, Set<String>> mapOfMails = new TreeMap<>();
        Set<String> user1 = new LinkedHashSet<>(Set.of("1@", "2@"));
        Set<String> user2 = new LinkedHashSet<>(Set.of("5@", "6@"));
        Set<String> user3 = new LinkedHashSet<>(Set.of("6@", "7@"));
        Set<String> user4 = new LinkedHashSet<>(Set.of("7@"));
        mapOfMails.put("us1", user1);
        mapOfMails.put("us2", user2);
        mapOfMails.put("us3", user3);
        mapOfMails.put("us4", user4);
        Map<String, Set<String>> result = merger.merge(mapOfMails);
        Map<String, Set<String>> expect = new HashMap<>(Map.of(
                "us1", new LinkedHashSet<>(Set.of("1@", "2@")),
                "us2", new LinkedHashSet<>(Set.of("5@", "6@", "7@"))));
        assertThat(result, is(expect));
    }

    @Test
    public void checkMergersOfTheThirdLevel() {
        Merger merger = new Merger();
        TreeMap<String, Set<String>> mapOfMails = new TreeMap<>();
        Set<String> mails1 = new LinkedHashSet<>(Set.of("1@", "2@"));
        Set<String> mails2 = new LinkedHashSet<>(Set.of("2@", "3@"));
        Set<String> mails3 = new LinkedHashSet<>(Set.of("3@", "4@"));
        Set<String> mails4 = new LinkedHashSet<>(Set.of("4@", "1@"));
        mapOfMails.put("us1", mails1);
        mapOfMails.put("us2", mails2);
        mapOfMails.put("us3", mails3);
        mapOfMails.put("us4", mails4);
        Map<String, Set<String>> result = merger.merge(mapOfMails);
        Map<String, LinkedHashSet<String>> expect = new HashMap<>(Map.of(
                "us1", new LinkedHashSet<>(Set.of("1@", "2@", "3@", "4@"))));
        assertThat(result, is(expect));
    }
}

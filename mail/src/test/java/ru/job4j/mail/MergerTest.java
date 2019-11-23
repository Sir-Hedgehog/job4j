package ru.job4j.mail;

import org.junit.Test;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 14.11.2019
 */

public class MergerTest {
    @Test
    public void checkMergersOfTheFirstLevel() {
        Merger merger = new Merger("1@");
        //mapOfMails - неотсортированная картотека почтовых адресов
        ConcurrentHashMap<String, LinkedHashSet<String>> mapOfMails = new ConcurrentHashMap<>();
        LinkedHashSet<String> mails1 = new LinkedHashSet<>(Set.of("1@", "2@"));
        LinkedHashSet<String> mails2 = new LinkedHashSet<>(Set.of("1@"));
        LinkedHashSet<String> mails3 = new LinkedHashSet<>(Set.of("3@"));
        LinkedHashSet<String> mails4 = new LinkedHashSet<>(Set.of("4@", "1@"));
        mapOfMails.put("us1", mails1);
        mapOfMails.put("us2", mails2);
        mapOfMails.put("us3", mails3);
        mapOfMails.put("us4", mails4);
        Map<String, LinkedHashSet<String>> result = merger.merge(mapOfMails);
        Map<String, LinkedHashSet<String>> expect = new HashMap<>(Map.of(
                "us4", new LinkedHashSet<>(Set.of("1@", "2@", "4@")),
                "us3", new LinkedHashSet<>(Set.of("3@"))));
        System.out.println(result);
        assertThat(result, is(expect));
    }

    /*@Test
    public void checkMergersOfTheSecondLevel() {
        Merger merger = new Merger("1@");
        ConcurrentHashMap<String, LinkedHashSet<String>> mapOfMails = new ConcurrentHashMap<>();
        LinkedHashSet<String> mails1 = new LinkedHashSet<>(Set.of("1@", "2@"));
        LinkedHashSet<String> mails2 = new LinkedHashSet<>(Set.of("5@", "6@"));
        LinkedHashSet<String> mails3 = new LinkedHashSet<>(Set.of("6@", "7@"));
        LinkedHashSet<String> mails4 = new LinkedHashSet<>(Set.of("7@"));
        mapOfMails.put("us1", mails1);
        mapOfMails.put("us2", mails2);
        mapOfMails.put("us3", mails3);
        mapOfMails.put("us4", mails4);
        Map<String, LinkedHashSet<String>> result = merger.merge(mapOfMails);
        Map<String, LinkedHashSet<String>> expect = new HashMap<>(Map.of(
                "us1", new LinkedHashSet<>(Set.of("1@", "2@")),
                "us2", new LinkedHashSet<>(Set.of("5@", "6@", "7@"))));
        assertThat(result, is(expect));
    }

    @Test
    public void checkMergersOfTheThirdLevel() {
        Merger merger = new Merger("1@");
        ConcurrentHashMap<String, LinkedHashSet<String>> mapOfMails = new ConcurrentHashMap<>();
        LinkedHashSet<String> mails1 = new LinkedHashSet<>(Set.of("1@", "2@"));
        LinkedHashSet<String> mails2 = new LinkedHashSet<>(Set.of("2@", "3@"));
        LinkedHashSet<String> mails3 = new LinkedHashSet<>(Set.of("4@", "3@"));
        LinkedHashSet<String> mails4 = new LinkedHashSet<>(Set.of("4@", "1@"));
        mapOfMails.put("us1", mails1);
        mapOfMails.put("us2", mails2);
        mapOfMails.put("us3", mails3);
        mapOfMails.put("us4", mails4);
        Map<String, LinkedHashSet<String>> result = merger.merge(mapOfMails);
        Map<String, LinkedHashSet<String>> expect = new HashMap<>(Map.of(
                "us4", new LinkedHashSet<>(Set.of("1@", "2@", "3@", "4@", "5@"))));
        assertThat(result, is(expect));
    }*/
}

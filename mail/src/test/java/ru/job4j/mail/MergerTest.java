package ru.job4j.mail;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 05.11.2019
 */

public class MergerTest {
    @Test
    public void checkMergers() {
        Merger merger = new Merger();
        ConcurrentHashMap<String, TreeSet<String>> map = new ConcurrentHashMap<>();
        TreeSet<String> set1 = new TreeSet<>();
        set1.add("1@");
        set1.add("2@");
        set1.add("7@");
        TreeSet<String> set2 = new TreeSet<>();
        set2.add("1@");
        set2.add("7@");
        TreeSet<String> set3 = new TreeSet<>();
        set3.add("3@");
        set3.add("5@");
        TreeSet<String> set4 = new TreeSet<>();
        set4.add("4@");
        set4.add("1@");
        TreeSet<String> set5 = new TreeSet<>();
        set5.add("5@");
        map.put("us1", set1);
        map.put("us2", set2);
        map.put("us3", set3);
        map.put("us4", set4);
        map.put("us5", set5);
        Map<String, TreeSet<String>> result = merger.merge(map);
        Map<String, TreeSet<String>> expect = new HashMap<>(Map.of(
                "us3", new TreeSet<>(Set.of("3@", "5@")),
                "us1", new TreeSet<>(Set.of("1@", "2@", "7@", "4@"))));
        System.out.println(result);
        assertThat(result, is(expect));
    }
    @Test
    public void checkMails() {
        Merger merger = new Merger();
        ConcurrentHashMap<String, TreeSet<String>> map = new ConcurrentHashMap<>();
        TreeSet<String> set1 = new TreeSet<>();
        set1.add("1@");
        set1.add("200@");
        TreeSet<String> set2 = new TreeSet<>();
        set2.add("100@");
        TreeSet<String> set3 = new TreeSet<>();
        set3.add("300");
        TreeSet<String> set4 = new TreeSet<>();
        set4.add("400@");
        set4.add("100");
        map.put("us1", set1);
        map.put("us2", set2);
        map.put("us3", set3);
        map.put("us4", set4);
        Map<String, TreeSet<String>> result = merger.merge(map);
        assertThat(null, is(result));
    }
}

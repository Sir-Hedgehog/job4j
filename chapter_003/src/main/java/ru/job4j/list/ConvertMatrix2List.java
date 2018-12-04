package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 04.12.2018
 */

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int out = 0; out < array.length; out++) {
            for (int in = 0; in < array[0].length; in++) {
                list.add(array[out][in]);
            }
        }
        return list;
    }
}

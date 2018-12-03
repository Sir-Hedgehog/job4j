package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 03.12.2018
 */

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        int columns = array.length;
        int rows = array[0].length;
        List<Integer> list = new ArrayList<>();
        for (int out = 0; out < columns; out++) {
            for (int in = 0; in < rows; in++) {
                list.add(array[out][in]);
            }
        }
        return list;
    }
}

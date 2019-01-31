package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 31.01.2018
 */

class ConvertList2Array {
    int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((float) list.size() / rows);
        int[][] array = new int[rows][cells];
        int index = 0;
        for (int out = 0; out < rows; out++) {
            for (int in = 0; in < cells; in++) {
                if (index < list.size()) {
                    array[out][in] = list.get(index);
                }
                index++;
            }
        }
        return array;
    }

    List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int in : array) {
                result.add(in);
            }
        }
        return result;
    }
}


package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 05.12.2018
 */

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
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

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int in : array) {
                result.add(in);
            }
        }
        return result;
    }
}


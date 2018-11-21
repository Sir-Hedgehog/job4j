package ru.job4j.list;

import java.util.LinkedList;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil(list.size() / rows);
        for (int in : list) {
            if (list.size() % rows != 0) {
                list.add(0);
            }
        }
        int[][] array = new int[rows][cells];
        return array;
    }
}

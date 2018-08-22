package ru.job4j.array;

public class Matrix {
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int index = 0; index < table.length; index++) {
            for (int index1 = 0; index1 < table.length; index1++) {
                table[index][index1] = (index + 1) * (index1 + 1);
            }
        }
        return table;
    }
}

package ru.job4j.array;

import java.util.Arrays;

public class Sorting {
    public int[] plus(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        for (int out = result.length - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (result[in] > result[in + 1]) {
                    int temp = result[in];
                    result[in] = result[in + 1];
                    result[in + 1] = temp;
                }
            }
        }
        return result;
    }
}

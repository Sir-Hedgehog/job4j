package ru.job4j.array;

public class Sorting {
    public int[] plus(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int one = 0, two = 0, score = 0;
        while (one < first.length && two < second.length) {
            if (first[one] < second[two]) {
                result[score++] = first[one++];
            } else {
                result[score++] = second[two++];
            }
        }
        while (one < first.length) {
            result[score++] = first[one++];
        }
        while (two < second.length) {
            result[score++] = second[two++];
        }
        return result;
    }
}

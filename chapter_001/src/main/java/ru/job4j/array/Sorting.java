package ru.job4j.array;

public class Sorting {
    public int[] plus(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int indexFirst = 0, indexSecond = 0, indexResult = 0;
        while (indexFirst < first.length && indexSecond < second.length) {
            if (first[indexFirst] < second[indexSecond]) {
                result[indexResult++] = first[indexFirst++];
            } else {
                result[indexResult++] = second[indexSecond++];
            }
        }
        while (indexFirst < first.length) {
            result[indexResult++] = first[indexFirst++];
        }
        while (indexSecond < second.length) {
            result[indexResult++] = second[indexSecond++];
        }
        return result;
    }
}

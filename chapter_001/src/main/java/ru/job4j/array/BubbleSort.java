package ru.job4j.array;

public class BubbleSort {
    public int[] sort(int[] array) {
        for (int index = 0; index < array.length; index++) {
            for (int index1 = 0; index1 < array.length - 1; index1++) {
                if (array[index1] > array[index1 + 1]) {
                    int temp = array[index1];
                    array[index1] = array[index1 + 1];
                    array[index1 + 1] = temp;
                }
            }
        }
        return array;
    }
}

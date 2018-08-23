package ru.job4j.array;

public class BubbleSort {
    public int[] sort(int[] array) {
        for (int index = array.length - 1; index > 0; index--) {
            for (int index1 = 0; index1 < index; index1++) {
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

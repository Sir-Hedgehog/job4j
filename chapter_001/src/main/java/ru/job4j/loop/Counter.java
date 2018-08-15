package ru.job4j.loop;

public class Counter {
    public int add(int start, int finish) {
        int sum = 0;
        for (int count = start; count <= finish; count++) {
            if (count % 2 == 0) {
                sum += count;
            }
        }
        return sum;
    }
}

package ru.job4j.max;

public class Max {

    public int max(int first, int second, int third) {
        return this.max(third, this.max(first, second));
    }

    public int max(int first, int second) {
        return first > second ? first : second;
    }

}

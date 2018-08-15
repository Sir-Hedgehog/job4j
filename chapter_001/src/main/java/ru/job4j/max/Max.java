package ru.job4j.max;

public class Max {
    public int max(int first, int second, int third) {
        int temp = this.max2(third, this.max1(first, second));
        return temp;
    }

    public int max1(int first, int second){
        return first > second ? first : second;
    }

    public int max2(int first, int second){
        return first > second ? first : second;
    }
}

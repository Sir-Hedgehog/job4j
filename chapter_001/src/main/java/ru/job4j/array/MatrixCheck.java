package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
            for (int index1 = 0, index = data.length - 1; index1 < data.length - 1; index1++, index--) {
                if (data[0][data.length - 1] != data[index][index1]) {
                    result = false;
                    break;
                }
            }
            for (int index = 1; index < data.length; index++) {
                if (data[0][0] != data[index][index]) {
                    result = false;
                    break;
                }
            }
            return result;
    }
}

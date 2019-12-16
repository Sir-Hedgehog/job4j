package ru.job4j.numbers;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 16.12.2019
 */

public class ArrayOfNumbers {

    /**
     * Метод получает массив, в содержимое ячеек которого нужно записать произведение всех значений ячеек, кроме указанного в параметре
     * @param input - исходный массив
     * @param current - индекс-исключение
     * @return результирующий массив
     */

    public int[] generate(int[] input, int current) {
        int resultOfMultiply = 1;
        for (int index : input) {
            resultOfMultiply = index * resultOfMultiply;
        }
        for (int index = 0; index < input.length; index++) {
            if (index != current) {
                input[index] = resultOfMultiply;
            }
        }
        return input;
    }
}

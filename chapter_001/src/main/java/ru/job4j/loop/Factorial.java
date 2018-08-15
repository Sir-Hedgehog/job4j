package ru.job4j.loop;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class Factorial {

    /**
     * Метод вычисляет факториал
     *
     * @param n - число, которое нужно возвести в факториал.
     * @return результат возведения числа в факториал
     */

    public int calc(int n) {
        int factor = 1;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                factor *= i;
            }
            return factor;
        } else if (n == 0) {
            return factor;
        } else {
            return -1;
        }
    }
}

package ru.job4j.hotel;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 16.05.2019
 */

public class Hotels {
    public int[] determine(int quantity, Integer[] rating) {
        List<Integer> list = Arrays.asList(rating);
        Map<Integer, Integer> map = new TreeMap<>();
        int[] result = new int[rating.length];
        if (quantity > 4 && quantity % 5 == 0 && list.size() == quantity) {
            for (int index = 0; index < quantity; index++) {
                map.put(index, rate(list.get(index)));
            }
            Collections.sort(list);
            for (int out = 0; out < map.size(); out++) {
                for (int in = 0; in < list.size(); in++) {
                    if (list.get(in).equals(map.get(out))) {
                        if (map.size() > 5) {
                            float variable = in + 1;
                            int current = map.size() / 5;
                            int total = (int) Math.ceil(variable / current);
                            result[out] = total;
                            break;
                        }
                        if (map.size() == 5) {
                            result[out] = in + 1;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    private int rate(int input) {
        int output = 0;
        if (input >= 1 && input <= 100) {
            output = input;
        }
        return output;
    }
}





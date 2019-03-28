package ru.job4j.hotel;

import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 21.03.2019
 */

public class Hotels {
    private int star;

    public int[] determineStars(int quantity, Rating[] rating) {
        List<Rating> list = Arrays.asList(rating);
        Map<Rating, Integer> map = new TreeMap<>();
        int[] result = null;
        if (quantity > 4 && quantity % 5 == 0 && list.size() == quantity) {
            for (Rating index : list) {
                if (index.getEvaluation() >= 1 && index.getEvaluation() <= 100) {
                    map.put(index, quantity);
                }
            }
            for (int star = 0; star < quantity; star++) {
                map.get(list.get(star));
                result[star] = star;
            }
        }
        return result;
    }
}





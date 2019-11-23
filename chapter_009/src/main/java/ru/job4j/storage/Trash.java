package ru.job4j.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 23.11.2019
 */

public class Trash  {
    private List<String> foods = new ArrayList<>();

    public void setFood(Food food) {
        foods.add(food.getName());
    }

    public List<String> getFood() {
        return foods;
    }
}

package ru.job4j.storage;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 24.11.2019
 */

public class Warehouse implements Store {
    private List<String> foods = new ArrayList<>();

    @Override
    public void setFood(Food food) {
        foods.add(food.getName());
    }

    public List<String> getFood() {
        return foods;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        Period plan = Period.between(food.getCreateDate(), food.getExpireDate());
        Period fact = Period.between(food.getCreateDate(), LocalDate.now());
        double percent = (double) fact.getDays() / plan.getDays() * 100;
        if (percent >= 0.0 && percent <= 25.0) {
            result = true;
        }
        return result;
    }
}

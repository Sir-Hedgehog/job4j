package ru.job4j.storage;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 25.11.2019
 */

public class Shop implements Store {
    private double percent;
    private List<String> foods = new ArrayList<>();
    private Map<String, Double> discountFoods = new HashMap<>();

    /**
     * Метод распределяет товар в зависимости от степени годности
     * @param food - товар
     */

    public void setFood(Food food) {
        if (percent > 25.0 && percent <= 75.0) {
            foods.add(food.getName());
        } else if (percent > 75.0 && percent <= 100.0) {
            this.setDiscountFoods(food);
        }
    }

    /**
     * Метод устанавливает скидку на товар
     * @param food - товар
     */

    private void setDiscountFoods(Food food) {
        food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount());
        discountFoods.put(food.getName(), food.getPrice());
    }

    public List<String> getFood() {
        return foods;
    }

    /**
     * Метод возвращает карту наименований товаров и цены с учетом скидки
     * @return карта наименований товаров и цены с учетом скидки
     */

    public Map<String, Double> getDiscountFoods() {
        return discountFoods;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        Period plan = Period.between(food.getCreateDate(), food.getExpireDate());
        Period fact = Period.between(food.getCreateDate(), LocalDate.now());
        percent = (double) fact.getDays() / plan.getDays() * 100;
        if (percent > 25.0 && percent <= 100.0) {
            result = true;
        }
        return result;
    }
}

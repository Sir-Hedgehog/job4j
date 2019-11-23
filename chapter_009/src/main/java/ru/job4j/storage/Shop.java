package ru.job4j.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 23.11.2019
 */

public class Shop {
    private List<String> foods = new ArrayList<>();
    private Map<String, Double> discountFoods = new HashMap<>();

    public void setFood(Food food) {
        foods.add(food.getName());
    }

    public List<String> getFood() {
        return foods;
    }

    /**
     * Метод устанавливает скидку с учетом категории товара
     * @param food - товар
     */

    public void setDiscountFoods(Food food) {
        food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount());
        discountFoods.put(food.getName(), food.getPrice());
    }

    /**
     * Метод возвращает карту наименований товаров и цены с учетом скидки
     * @return карта наименований товаров и цены с учетом скидки
     */

    public Map<String, Double> getDiscountFoods() {
        return discountFoods;
    }
}

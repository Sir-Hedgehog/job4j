package ru.job4j.storage;

import java.util.List;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 27.11.2019
 */

public class ControlQuality {
    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;

    public ControlQuality(Warehouse warehouse, Shop shop, Trash trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    /**
     * Метод распределяет продукты по хранилищам
     */
    public void distribute(Food food) {
        if (warehouse.accept(food)) {
            warehouse.setFood(food);
        } else if (shop.accept(food)) {
            shop.setFood(food);
        } else if (trash.accept(food)) {
            trash.setFood(food);
        }
    }

    /**
     * Метод перераспределяет продукты по хранилищам
     * @param foods - список продуктов
     */
    public void resort(List<Food> foods) {
        warehouse.getFood().clear();
        shop.getFood().clear();
        shop.getDiscountFoods().clear();
        trash.getFood().clear();
        for (Food food : foods) {
            this.distribute(food);
        }
        for (Map.Entry<String, Double> foodsWithDiscount : shop.getDiscountFoods().entrySet()) {
            shop.setOldPriceWithoutDiscount(foodsWithDiscount);
        }
    }
}

package ru.job4j.storage;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 23.11.2019
 */

public class ControlQuality {
    private Food food;
    private Warehouse warehouse = new Warehouse();
    private Shop shop = new Shop();
    private Trash trash = new Trash();

    public ControlQuality(Food food) {
        this.food = food;
    }

    /**
     * Метод распределяет продукты по хранилищам
     */

    public void distribute() {
        Period plan = Period.between(this.food.getCreateDate(), this.food.getExpireDate());
        Period fact = Period.between(this.food.getCreateDate(), LocalDate.now());
        int current = fact.getDays();
        int current1 = plan.getDays();
        double percent = (double) current / current1 * 100;
        if (percent >= 0.0 && percent <= 25.0) {
            warehouse.setFood(food);
        } else if (percent > 25.0 && percent <= 75.0) {
            shop.setFood(food);
        } else if (percent > 75.0 && percent <= 100.0) {
            shop.setDiscountFoods(food);
        } else {
            trash.setFood(food);
        }
    }

    /**
     * Метод проверяет наличие товара на складе
     * @return список наименований товара
     */

    public List<String> checkWarehouse() {
        return warehouse.getFood();
    }

    /**
     * Метод проверяет наличие товара в магазине без скидки
     * @return список наименований товара
     */

    public List<String> checkShopWithoutDiscount() {
        return shop.getFood();
    }

    /**
     * Метод проверяет наличие товара в магазине со скидкой
     * @return карта наименований товара и цены с учетом скидки
     */

    public Map<String, Double> checkShopWithDiscount() {
        return shop.getDiscountFoods();
    }

    /**
     * Товар в мусоре
     * @return список наименований товара
     */

    public List<String> checkTrash() {
        return trash.getFood();
    }
}

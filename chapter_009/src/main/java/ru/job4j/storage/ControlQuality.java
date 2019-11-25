package ru.job4j.storage;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 25.11.2019
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
}

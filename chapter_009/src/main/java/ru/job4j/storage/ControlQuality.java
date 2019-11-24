package ru.job4j.storage;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 24.11.2019
 */

public class ControlQuality {
    private Store store;

    public ControlQuality(Store store) {
        this.store = store;
    }

    /**
     * Метод распределяет продукты по хранилищам
     */
    public void distribute(Food food) {
        if (store.accept(food)) {
            store.setFood(food);
        }
    }
}

package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 3.12.2019
 */

public class ParkingForCars implements Distributor {
    private int size;
    private List<String> automobiles = new ArrayList<>();
    private String[] array;

    public ParkingForCars(int size) {
        this.size = size;
        array = new String[this.size];
    }

    /**
     * Метод добавляет машины на парковочные места с учетом размера парковки
     * @param cars - автомобиль
     */

    public void setCars(Cars cars) {
        automobiles.add(cars.getModel());
        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) {
                array[index] = automobiles.get(automobiles.size() - 1);
            }
        }
    }

    /**
     * Метод получает размер парковочных мест
     * @return - размер парковочных мест
     */

    public int getSizeOfParking() {
        return array.length;
    }

    public List<String> getCars() {
        return automobiles;
    }

    /**
     * Метод проверяет, является ли машина легковой и есть ли места на парковке
     * @param cars - автомобиль
     * @return - результат проверки
     */

    @Override
    public boolean accept(Cars cars) {
        boolean result = false;
        if (cars.getWeight() <= 3500.0 && automobiles.size() != array.length) {
            result = true;
        }
        return result;
    }
}

package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 3.12.2019
 */

public class ParkingForTrucks implements Distributor {
    private List<String> trucks = new ArrayList<>();
    private String[] array;

    public ParkingForTrucks(int size) {
        array = new String[size];
    }



    public void setCars(Cars cars) {
        trucks.add(cars.getModel());
        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) {
                array[index] = trucks.get(trucks.size() - 1);
                break;
            }
        }
    }

    public List<String> getCars() {
        return trucks;
    }

    /**
     * Метод проверяет, является ли машина грузовой и есть ли места на парковке
     * @param cars - автомобиль
     * @return - результат проверки
     */

    @Override
    public boolean accept(Cars cars) {
        boolean result = false;
        if (cars.getWeight() > 3500.0 && trucks.size() != array.length) {
            result = true;
        }
        return result;
    }
}

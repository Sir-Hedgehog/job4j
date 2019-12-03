package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 3.12.2019
 */

public class BasicParking implements Distributor {
    private ParkingForCars parkingForCars;
    private ParkingForTrucks parkingForTrucks;
    private List<String> parking = new ArrayList<>();

    public BasicParking(ParkingForCars parkingForCars, ParkingForTrucks parkingForTrucks) {
        this.parkingForCars = parkingForCars;
        this.parkingForTrucks = parkingForTrucks;
    }

    public void setCars(Cars cars) {
        parking.add(cars.getModel());
    }

    public List<String> getCars() {
        return parking;
    }

    /**
     * Метод дает согласие на размещение машины на парковке в зависимости от ее типа.
     * При этом, если парковка для грузовых автомобилей заполнена, то новая грузовая машина может припарковаться в
     * парковке для легковых машин, но займет она при этом два парковочных места.
     * @param cars - машина
     * @return - согласие на размещение машины
     */

    @Override
    public boolean accept(Cars cars) {
        boolean result = false;
        if (parkingForCars.accept(cars)) {
            parkingForCars.setCars(cars);
            this.setCars(cars);
            result = true;
        } else if (parkingForTrucks.accept(cars)) {
            parkingForTrucks.setCars(cars);
            this.setCars(cars);
            result = true;
        } else if (parkingForCars.getSizeOfParking() - parkingForCars.getCars().size() >= 2 && cars.getWeight() > 3500.0 && !parkingForTrucks.accept(cars)) {
            parkingForCars.setCars(cars);
            parkingForCars.setCars(cars);
            this.setCars(cars);
            this.setCars(cars);
            result = true;
        }
        return result;
    }
}

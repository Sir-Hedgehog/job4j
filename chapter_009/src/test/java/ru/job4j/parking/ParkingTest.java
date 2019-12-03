package ru.job4j.parking;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 3.12.2019
 */

public class ParkingTest {
    @Test
    public void whenAllCarsParkedThenGetSuccess() {
        BasicParking parking = new BasicParking(new ParkingForCars(2), new ParkingForTrucks(2));
        Cars car1 = new Truck("Mercedes", "р345ра", 3600.0);
        Cars car2 = new Truck("ЛиАЗ", "р123но", 7020.35);
        Cars car3 = new Car("Renault", "е789зх", 2200.09);
        Cars car4 = new Car("Ford", "г987пт", 2356.55);
        Service service = new Service(parking);
        String success1 = service.distribute(car1);
        assertThat(success1, is("Автомобиль успешно припаркован!"));
        String success2 = service.distribute(car2);
        assertThat(success2, is("Автомобиль успешно припаркован!"));
        String success3 = service.distribute(car3);
        assertThat(success3, is("Автомобиль успешно припаркован!"));
        String success4 = service.distribute(car4);
        assertThat(success4, is("Автомобиль успешно припаркован!"));
    }

    @Test
    public void whenOneCarDidntParkedThenGetFail() {
        BasicParking parking = new BasicParking(new ParkingForCars(1), new ParkingForTrucks(2));
        Cars car1 = new Truck("Mercedes", "р345ра", 3600.0);
        Cars car2 = new Truck("ЛиАЗ", "р123но", 7020.35);
        Cars car3 = new Car("Renault", "е789зх", 2200.09);
        Cars car4 = new Car("Ford", "г987пт", 2356.55);
        Service service = new Service(parking);
        String success1 = service.distribute(car1);
        assertThat(success1, is("Автомобиль успешно припаркован!"));
        String success2 = service.distribute(car2);
        assertThat(success2, is("Автомобиль успешно припаркован!"));
        String success3 = service.distribute(car3);
        assertThat(success3, is("Автомобиль успешно припаркован!"));
        String success4 = service.distribute(car4);
        assertThat(success4, is("Нет парковочных мест"));
    }

    @Test
    public void whenOneTruckDidntParkedThenGetFail() {
        BasicParking parking = new BasicParking(new ParkingForCars(2), new ParkingForTrucks(1));
        Cars car1 = new Car("Renault", "е789зп", 2200.09);
        Cars car2 = new Car("Ford", "г987пт", 2356.55);
        Cars car3 = new Truck("Mercedes", "р345ра", 3600.0);
        Cars car4 = new Truck("ЛиАЗ", "р123но", 7020.35);
        Service service = new Service(parking);
        String success1 = service.distribute(car1);
        assertThat(success1, is("Автомобиль успешно припаркован!"));
        String success2 = service.distribute(car2);
        assertThat(success2, is("Автомобиль успешно припаркован!"));
        String success3 = service.distribute(car3);
        assertThat(success3, is("Автомобиль успешно припаркован!"));
        String success4 = service.distribute(car4);
        assertThat(success4, is("Нет парковочных мест"));
    }

    @Test
    public void whenOneTruckParkedOnTheCarParkingThenGetSuccess() {
        BasicParking parking = new BasicParking(new ParkingForCars(4), new ParkingForTrucks(1));
        Cars car1 = new Truck("Mercedes", "р345ра", 3600.0);
        Cars car2 = new Truck("ЛиАЗ", "р123но", 7020.35);
        Cars car3 = new Car("Renault", "е789зх", 2200.09);
        Cars car4 = new Car("Ford", "г987пт", 2356.55);
        Service service = new Service(parking);
        String success1 = service.distribute(car1);
        assertThat(success1, is("Автомобиль успешно припаркован!"));
        String success2 = service.distribute(car2);
        assertThat(success2, is("Автомобиль успешно припаркован!"));
        String success3 = service.distribute(car3);
        assertThat(success3, is("Автомобиль успешно припаркован!"));
        String success4 = service.distribute(car4);
        assertThat(success4, is("Автомобиль успешно припаркован!"));
    }

    @Test
    public void checkPresenceOfCarsOnTheParking() {
        ParkingForCars parkingForCars = new ParkingForCars(2);
        ParkingForTrucks parkingForTrucks = new ParkingForTrucks(2);
        BasicParking parking = new BasicParking(parkingForCars, parkingForTrucks);
        Cars car1 = new Truck("Mercedes", "р345ра", 3600.0);
        Cars car2 = new Truck("ЛиАЗ", "р123но", 7020.35);
        Cars car3 = new Car("Renault", "е789зх", 2200.09);
        Cars car4 = new Car("Ford", "г987пт", 2356.55);
        Service service = new Service(parking);
        service.distribute(car1);
        service.distribute(car2);
        service.distribute(car3);
        service.distribute(car4);
        List<String> list = parking.getCars();
        assertThat(list.get(0), is("Mercedes"));
        assertThat(list.get(1), is("ЛиАЗ"));
        assertThat(list.get(2), is("Renault"));
        assertThat(list.get(3), is("Ford"));
    }

    @Test
    public void checkPresenceOfCarsOnTheParkingForCars() {
        ParkingForCars parkingForCars = new ParkingForCars(2);
        ParkingForTrucks parkingForTrucks = new ParkingForTrucks(2);
        BasicParking parking = new BasicParking(parkingForCars, parkingForTrucks);
        Cars car1 = new Truck("Mercedes", "р345ра", 3600.0);
        Cars car2 = new Truck("ЛиАЗ", "р123но", 7020.35);
        Cars car3 = new Car("Renault", "е789зх", 2200.09);
        Cars car4 = new Car("Ford", "г987пт", 2356.55);
        Service service = new Service(parking);
        service.distribute(car1);
        service.distribute(car2);
        service.distribute(car3);
        service.distribute(car4);
        List<String> list = parkingForCars.getCars();
        assertThat(list.get(0), is("Renault"));
        assertThat(list.get(1), is("Ford"));
    }

    @Test
    public void checkPresenceOfTrucksOnTheParkingForTrucks() {
        ParkingForCars parkingForCars = new ParkingForCars(2);
        ParkingForTrucks parkingForTrucks = new ParkingForTrucks(2);
        BasicParking parking = new BasicParking(parkingForCars, parkingForTrucks);
        Cars car1 = new Truck("Mercedes", "р345ра", 3600.0);
        Cars car2 = new Truck("ЛиАЗ", "р123но", 7020.35);
        Cars car3 = new Car("Renault", "е789зх", 2200.09);
        Cars car4 = new Car("Ford", "г987пт", 2356.55);
        Service service = new Service(parking);
        service.distribute(car1);
        service.distribute(car2);
        service.distribute(car3);
        service.distribute(car4);
        List<String> list = parkingForTrucks.getCars();
        assertThat(list.get(0), is("Mercedes"));
        assertThat(list.get(1), is("ЛиАЗ"));
    }

    @Test
    public void checkPresenceOfCarsWithTruckOnTheParkingForCars() {
        ParkingForCars parkingForCars = new ParkingForCars(4);
        ParkingForTrucks parkingForTrucks = new ParkingForTrucks(1);
        BasicParking parking = new BasicParking(parkingForCars, parkingForTrucks);
        Cars car1 = new Truck("Mercedes", "р345ра", 3600.0);
        Cars car2 = new Truck("ЛиАЗ", "р123но", 7020.35);
        Cars car3 = new Car("Renault", "е789зх", 2200.09);
        Cars car4 = new Car("Ford", "г987пт", 2356.55);
        Service service = new Service(parking);
        service.distribute(car1);
        service.distribute(car2);
        service.distribute(car3);
        service.distribute(car4);
        List<String> list = parkingForCars.getCars();
        assertThat(list.get(0), is("ЛиАЗ"));
        assertThat(list.get(1), is("ЛиАЗ"));
        assertThat(list.get(2), is("Renault"));
        assertThat(list.get(3), is("Ford"));
    }
}

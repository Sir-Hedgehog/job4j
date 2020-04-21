package ru.job4j.automobiles;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 21.04.2020
 */

public class RunCarHistory {
    private static Session session;
    private static SessionFactory factory;

    private static void createHibernateSession() {
        factory = new Configuration().configure("automobiles.cfg.xml").buildSessionFactory();
        session = factory.openSession();
        session.beginTransaction();
    }

    private static void closeHibernateSession() {
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public static void main(String[] args) {
        //добавление данных
        createHibernateSession();
        addDriversAndCars();
        readDrivers();
        readCars();
        closeHibernateSession();

        //обновление данных
        createHibernateSession();
        updateDriversAndCars();
        readDrivers();
        readCars();
        closeHibernateSession();

        //удаление данных
        createHibernateSession();
        deleteDriversAndCars();
        readDrivers();
        readCars();
        closeHibernateSession();
    }

    private static void readDrivers() {
        List<Driver> drivers = session.createQuery("from Driver", Driver.class).list();
        System.out.println(drivers.toString());
    }

    private static void readCars() {
        List<Car> cars = session.createQuery("from Car", Car.class).list();
        System.out.println(cars.toString());
    }

    private static void addDriversAndCars() {
        Engine engine1 = new Engine();
        engine1.setPower(381);
        engine1.setVolume(2993);

        Engine engine2 = new Engine();
        engine2.setPower(131);
        engine2.setVolume(1998);

        Engine engine3 = new Engine();
        engine3.setPower(277);
        engine3.setVolume(3726);

        session.save(engine1);
        session.save(engine2);
        session.save(engine3);

        Car car1 = new Car(engine1);
        car1.setModel("BMW");
        car1.setYearOfRelease("2013");
        car1.setBodyType("SUV");

        Car car2 = new Car(engine2);
        car2.setModel("Chevrolet");
        car2.setYearOfRelease("2006");
        car2.setBodyType("sedan");

        Car car3 = new Car(engine3);
        car3.setModel("Mazda");
        car3.setYearOfRelease("2012");
        car3.setBodyType("SUV");

        Set<Car> cars1 = new HashSet<>();
        cars1.add(car1);
        cars1.add(car2);

        Set<Car> cars2 = new HashSet<>();
        cars2.add(car2);
        cars2.add(car3);

        Driver driver1 = new Driver();
        driver1.setName("Роберт");
        driver1.setAge(21);
        driver1.setSex("муж");
        driver1.setCars(cars1);

        Driver driver2 = new Driver();
        driver2.setName("Джон");
        driver2.setAge(39);
        driver2.setSex("муж");
        driver2.setCars(cars2);

        session.save(car1);
        session.save(car2);
        session.save(car3);
        session.save(driver1);
        session.save(driver2);
    }

    private static void updateDriversAndCars() {
        Engine updatedEngine = null;
        Car updatedCar = null;
        Driver updatedDriver = null;

        List<Car> cars = session.createQuery("from Car where id = 1", Car.class).list();
        for (Car car : cars) {
            car.setModel("Mercedes-Benz");
            car.setYearOfRelease("2015");
            car.setBodyType("hatchback");
            updatedCar = car;
        }
        session.update(updatedCar);

        List<Engine> engines = session.createQuery("from Engine where id = 2", Engine.class).list();
        for (Engine engine : engines) {
            engine.setPower(122);
            engine.setVolume(1595);
            updatedEngine = engine;
        }
        session.update(updatedEngine);

        List<Driver> drivers = session.createQuery("from Driver where id = 2", Driver.class).list();
        for (Driver driver : drivers) {
            driver.setName("Василий");
            driver.setAge(55);
            driver.setSex("муж");
            updatedDriver = driver;
        }
        session.update(updatedDriver);

    }

    private static void deleteDriversAndCars() {
        Engine deletedEngine = null;
        Car deletedCar = null;
        Driver deletedDriver = null;

        List<Driver> drivers = session.createQuery("from Driver where id = 2", Driver.class).list();
        for (Driver driver : drivers) {
            deletedDriver = driver;
        }

        List<Car> cars = session.createQuery("from Car where id = 3", Car.class).list();
        for (Car car : cars) {
            deletedCar = car;
        }

        List<Engine> engines = session.createQuery("from Engine where id = 3", Engine.class).list();
        for (Engine engine : engines) {
            deletedEngine = engine;
        }

        session.delete(deletedDriver);
        session.delete(deletedCar);
        session.delete(deletedEngine);
    }
}

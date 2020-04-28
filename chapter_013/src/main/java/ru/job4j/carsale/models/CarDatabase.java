package ru.job4j.carsale.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

public class CarDatabase implements TemplateBase {
    private final SessionFactory factory;
    private static final TemplateBase INSTANCE = new CarDatabase();

    public CarDatabase() {
        factory = new Configuration().configure("carsale.cfg.xml").buildSessionFactory();
    }

    /**
     * Метод дает право создать единственный экземпляр класса для взаимосвязи с валидационным блоком
     * @return - экземпляр класса CarDatabase
     */

    public static TemplateBase getInstance() {
        return INSTANCE;
    }

    /**
     * Метод сохраняет в БД данные нового объявления
     * @param seller - продавец
     * @param car - автомобиль
     * @return - успешность операции
     */

    @Override
    public boolean saveAd(Seller seller, Car car) {
        return this.template(session -> {
            session.save(seller);
            session.save(car);
            return true;
        });
    }

    /**
     * Метод обновляет статус объявления
     * @param joinId - идентификатор пользователя
     * @return - новый статус
     */

    @Override
    public String updateStatus(String joinId) {
        return this.template(
                session -> {
                    List<Car> elect = session.createQuery("from Car where seller.id = " + joinId, Car.class).list();
                    String newStatus = this.setOtherStatus(elect.get(0).getStatus());
                    elect.get(0).setStatus(newStatus);
                    session.update(elect.get(0));
                    return newStatus;
                });
    }

    /**
     * Метод формирует список всех объявлений
     * @return - список всех объявлений
     */

    @Override
    public List<List<String>> getData() {
        return this.template(session -> {
            List<Car> cars = session.createQuery("from Car", Car.class).list();
            List<List<String>> result = new ArrayList<>();
            for (Car car : cars) {
                List<String> current = new ArrayList<>();
                current.add(car.getImage());
                current.add(car.getSeller().getName());
                current.add(car.getSeller().getNumber());
                current.add(car.getModel());
                current.add(car.getBodyType());
                current.add(car.getYearOfRelease());
                current.add(car.getPower());
                current.add(car.getVolume());
                current.add(car.getPrice());
                current.add(car.getMileage());
                current.add(car.getStatus());
                current.add(String.valueOf(car.getSeller().getId()));
                result.add(current);
            }
            return result;
        });
    }

    /**
     * Метод формирует общий шаблон для редактирования БД
     * @param command - данные созданной сессии с получением на выходе отредактированную таблицу или успешность операции
     * @return - отредактированная таблица или успешность операции (в зависимости от контекста)
     */

    private <T> T template(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (final Exception exception) {
            session.getTransaction().rollback();
            throw exception;
        } finally {
            session.close();
        }
    }

    /**
     * Метод распознает текущий статус и меняет его на другой
     * @param status - текущий статус
     * @return - обновленный статус
     */

    private String setOtherStatus(String status) {
        if (status.equals("в продаже")) {
            status = "продан";
        } else if (status.equals("продан")) {
            status = "в продаже";
        }
        return status;
    }
}

package ru.job4j.carsale.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 05.04.2020
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
     * Метод формирует результирующий список объявлений исходя из выбранных фильтров
     * @return - список объявлений
     */

    @Override
    public List<List<String>> getData(String firm, String photo, String time) {
        String substring = this.getResultQuery(firm, photo, time);
        return this.template(session -> {
            List<Car> cars = session.createNativeQuery("select * from Car" + substring, Car.class).list();
            return this.getResultList(cars);
        });
    }

    /**
     * Метод получает данные для запроса в БД
     * @param firm - фильтр по марке автомобиля
     * @param photo - фильтр по наличию фотографии
     * @param time - фильтр по времени создания объявления
     * @return - данные для запроса в БД
     */

    private String getResultQuery(String firm, String photo, String time) {
        String sortFirm = this.filterFirms(firm);
        String sortPhoto = this.filterPhoto(photo);
        String sortTime = this.filterTime(time);
        return this.filterAll(firm, sortFirm, sortPhoto, sortTime);
    }

    /**
     * Метод формирует данные о марке автомобиля для запроса в БД
     * @param firm - марка автомобиля
     * @return - отсортированные данные
     */

    private String filterFirms(String firm) {
        String sortFirm = "";
        if (!firm.equals("") && !firm.equals("allCars")) {
            sortFirm = " model = '" + firm + "'";
        }
        return sortFirm;
    }

    /**
     * Метод формирует данные о наличии фотографии для запроса в БД
     * @param photo - наличие фотографии
     * @return - отсортированные данные
     */

    private String filterPhoto(String photo) {
        String sortPhoto = "";
        if (photo.equals("withPhoto")) {
            sortPhoto = " image != 'Фото не выбрано'";
        } else if (photo.equals("withoutPhoto")) {
            sortPhoto = " image = 'Фото не выбрано'";
        }
        return sortPhoto;
    }

    /**
     * Метод формирует данные по времени создания объявления для запроса в БД
     * @param time - время создания
     * @return - отсортированные данные
     */

    private String filterTime(String time) {
        String sortTime = "";
        if (time.equals("twelveHours")) {
            sortTime = " date_of_creation >= current_timestamp - INTERVAL '12 HOUR'";
        } else if (time.equals("day")) {
            sortTime = " date_of_creation >= current_timestamp - INTERVAL '1 DAY'";
        } else if (time.equals("week")) {
            sortTime = " date_of_creation >= current_timestamp - INTERVAL '7 DAY'";
        } else if (time.equals("month")) {
            sortTime = " date_of_creation >= current_timestamp - INTERVAL '1 MONTH'";
        }
        return sortTime;
    }

    /**
     * Метод формирует данные исходя из информации по каждому из фильтров
     * @param firm - марка автомобиля
     * @param sortFirm - отсортированные данные по марке автомобиля
     * @param sortPhoto - отсортированные данные по наличию фотографии
     * @param sortTime - отсортированные данные по времени создания
     * @return - отсортированные данные
     */

    private String filterAll(String firm, String sortFirm, String sortPhoto, String sortTime) {
        String result = "";
        if (!sortFirm.equals("") && !sortPhoto.equals("") && !sortTime.equals("") && !firm.equals("allCars")) {
            result = " where" + sortFirm + " and" + sortPhoto + " and" + sortTime;
        } else if (!sortFirm.equals("") && sortPhoto.equals("") && sortTime.equals("")) {
            result = " where" + sortFirm;
        } else if (sortFirm.equals("") && !sortPhoto.equals("") && sortTime.equals("")) {
            result = " where" + sortPhoto;
        } else if (sortFirm.equals("") && sortPhoto.equals("") && !sortTime.equals("")) {
            result = " where" + sortTime;
        } else if (!sortFirm.equals("") && !sortPhoto.equals("") && sortTime.equals("")) {
            result = " where" + sortFirm + " and" + sortPhoto;
        } else if (!sortFirm.equals("") && sortPhoto.equals("") && !sortTime.equals("")) {
            result = " where" + sortFirm + " and" + sortTime;
        } else if (sortFirm.equals("") && !sortPhoto.equals("") && !sortTime.equals("")) {
            result = " where" + sortPhoto + " and" + sortTime;
        }
        return result;
    }

    /**
     * Метод разбивает сущность, полученную из БД, на отдельные части с целью более удобного применения их во фронте
     * @param cars - список автомобилей
     * @return - список составляющих частей каждого из созданных объявлений с учетом фильтра
     */

    private List<List<String>> getResultList(List<Car> cars) {
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
            current.add(car.getCreateDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")));
            result.add(current);
        }
        return result;
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

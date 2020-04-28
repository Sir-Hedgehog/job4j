package ru.job4j.carsale.models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

public class ValidateService implements Validation {
    private static final Pattern CHECK_OF_NAME = Pattern.compile("((([A-Z]){1}([a-z]){1,}(\\s){1}){1}(([A-Z]){1}([a-z]){1,}){1})|((([А-Я]){1}([а-я]){1,}(\\s){1}){1}(([А-Я]){1}([а-я]){1,}){1})");
    private static final Pattern CHECK_OF_PHONE = Pattern.compile("^\\+7\\((\\d){3}\\)(\\d){3}-(\\d){2}-(\\d){2}$");
    private static final Pattern CHECK_OF_MILEAGE = Pattern.compile("^(((\\d){1,})((\\s){1}))((([a-z]){1,})|(([а-я]){1,}))$");
    private static final Pattern CHECK_OF_PRICE = Pattern.compile("^(((\\d){1,})((\\s){1}))((([a-z]){1,})|(([а-я]){1,}))$");
    private static final Validation INSTANCE = new ValidateService();
    private final TemplateBase database = CarDatabase.getInstance();

    /**
     * Метод дает право создать единственный экземпляр класса для взаимосвязи с сервлетами
     * @return - экземпляр класса ValidateService
     */

    public static Validation getInstance() {
        return INSTANCE;
    }

    /**
     * Метод проверяет введенное имя на валидность
     * @param seller - продавец
     * @return - валидные данные или нет
     */

    private boolean checkName(Seller seller) {
        Matcher matcher = CHECK_OF_NAME.matcher(seller.getName());
        return matcher.find();
    }

    /**
     * Метод проверяет введенный номер телефона на валидность
     * @param seller - продавец
     * @return - валидные данные или нет
     */

    private boolean checkPhone(Seller seller) {
        Matcher matcher = CHECK_OF_PHONE.matcher(seller.getNumber());
        return matcher.find();
    }

    /**
     * Метод проверяет введенные данные по пробегу на валидность
     * @param car - автомобиль
     * @return - валидные данные или нет
     */

    private boolean checkMileage(Car car) {
        Matcher matcher = CHECK_OF_MILEAGE.matcher(car.getMileage());
        return matcher.find();
    }

    /**
     * Метод проверяет введенные данные по стоимости на валидность
     * @param car - автомобиль
     * @return - валидные данные или нет
     */

    private boolean checkPrice(Car car) {
        Matcher matcher = CHECK_OF_PRICE.matcher(car.getMileage());
        return matcher.find();
    }

    /**
     * Метод проверяет данные нового объявления на валидность
     * @param seller - продавец
     * @param car - автомобиль
     * @return - успешность операции
     */

    @Override
    public boolean validateAdd(Seller seller, Car car) {
        boolean result = false;
        if (checkName(seller) && checkPhone(seller) && checkMileage(car) && checkPrice(car)) {
            result = database.saveAd(seller, car);
        }
        return result;
    }

    /**
     * Метод работает в качестве шаблона, с помощью которого в будущем можно сформировать фильтры валидности для обновления данных
     * @return - успешность операции
     */

    @Override
    public String validateUpdate(String joinId) {
        return database.updateStatus(joinId);
    }

    /**
     * Метод работает в качестве шаблона, с помощью которого в будущем можно сформировать фильтры валидности для получения данных
     * @return - успешность операции
     */

    @Override
    public List<List<String>> validateGetData() {
        return database.getData();
    }
}

package ru.job4j.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 05.04.2020
 */

public class ValidateService implements Validation {
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);
    private static final Pattern CHECK_OF_NAME = Pattern.compile("((([A-Z]){1}([a-z]){1,}(\\s){1}){2}(([A-Z]){1}([a-z]){1,}){1})|((([А-Я]){1}([а-я]){1,}(\\s){1}){2}(([А-Я]){1}([а-я]){1,}){1})");
    private static final Pattern CHECK_OF_PHONE = Pattern.compile("^\\+7\\((\\d){3}\\)(\\d){3}-(\\d){2}-(\\d){2}$");
    private Map<Integer, Integer> takenPlaces = new HashMap<>();
    private final Store logic = CinemaDatabase.getInstance();

    private static Validation validateInstance = new ValidateService();

    /**
     * Метод дает право создать единственный экзепляр класса для взаимосвязи с сервлетом
     * @return - экзепляр класса ValidateService
     */

    public static Validation getInstance() {
        return validateInstance;
    }

    /**
     * Метод проверяет введенное имя на валидность
     * @param account - аккаунт пользователя
     * @return - валидные данные или нет
     */

    private boolean checkName(Account account) {
        Matcher matcher = CHECK_OF_NAME.matcher(account.getName());
        return matcher.find();
    }

    /**
     * Метод проверяет введенный номер телефона на валидность
     * @param account - аккаунт пользователя
     * @return - валидные данные или нет
     */

    private boolean checkPhone(Account account) {
        Matcher matcher = CHECK_OF_PHONE.matcher(account.getPhone());
        return matcher.find();
    }

    /**
     * Метод сохраняет выбранное место в кинозале
     * @param hall - кинозал
     * @return - успешность операции
     */

    @Override
    public boolean choosePlace(Hall hall) {
        boolean result = false;
        if (!takenPlaces.containsKey(hall.getRow()) || (takenPlaces.containsKey(hall.getRow()) && takenPlaces.get(hall.getRow()) != hall.getPlace())) {
            takenPlaces.put(hall.getRow(), hall.getPlace());
            logic.takePlace(hall);
            result = true;
        }
        return result;
    }

    /**
     * Метод сохраняет аккаунт пользователя
     * @param account - аккаунт пользователя
     * @return - успешность операции
     */

    @Override
    public boolean addData(Account account) {
        boolean result = false;
        if (checkName(account) && checkPhone(account)) {
            try {
                logic.addAccount(account);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
            result = true;
        }
        return result;
    }

    /**
     * Метод получает список существующих пользователей
     * @return - список существующих пользователей
     */

    @Override
    public CopyOnWriteArrayList<Hall> getTakenPlaces() {
        return logic.findTakenPlaces();
    }
}

package ru.job4j.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 5.0
 * @since 10.06.2020
 */

public class ValidateService implements Validation {
    private static final Logger LOG = LoggerFactory.getLogger(ValidateService.class);
    private static final Pattern CHECK_OF_NAME = Pattern.compile("((([A-Z]){1}([a-z]){1,}(\\s){1}){2}(([A-Z]){1}([a-z]){1,}){1})|((([А-Я]){1}([а-я]){1,}(\\s){1}){2}(([А-Я]){1}([а-я]){1,}){1})");
    private static final Pattern CHECK_OF_PHONE = Pattern.compile("^\\+7\\((\\d){3}\\)(\\d){3}-(\\d){2}-(\\d){2}$");
    private final Store logic = CinemaDatabase.instanceOf();
    private static Validation validateInstance = new ValidateService();

    /**
     * Метод дает право создать единственный экзепляр класса для взаимосвязи с сервлетом
     * @return - экземпляр класса ValidateService
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
     * Метод проверяет введенные данные на валидность и в случае успеха передает их в базу данных на обработку
     * @param hall - выбранное место
     * @param account - аккаунт
     * @return - успешность операции
     */

    @Override
    public boolean validateTransaction(Hall hall, Account account) {
        boolean result = false;
        if (checkName(account) && checkPhone(account)) {
            try {
                logic.realizeTransaction(hall, account);
                result = true;
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Метод получает список существующих пользователей
     * @return - список существующих пользователей
     */

    @Override
    public List<Hall> getTakenPlaces() {
        return logic.findTakenPlaces();
    }
}

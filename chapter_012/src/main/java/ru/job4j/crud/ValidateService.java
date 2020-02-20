package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 18.02.2020
 */

public class ValidateService implements Validate {
    private static final Pattern CHECK_OF_NAME = Pattern.compile("^(([A-Z]|[А-Я]){1}([a-z]|[а-я]){1,})$");
    private static final Pattern CHECK_OF_EMAIL = Pattern.compile("^((\\w{1,}[-._]{0,1}\\w{1,})+@(\\w{1,}[-._]{0,1}\\w{1,})+[.]{1}[a-z]{2,4})$");
    private static final Pattern CHECK_OF_LOGIN = Pattern.compile("^(\\w{1,}[-._]{0,1}\\w{1,})$");
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    private final Store logic = MemoryStore.getInstance();

    private static ValidateService validateInstance = new ValidateService();

    /**
     * Метод дает право создать единственный экзепляр класса для взаимосвязи с сервлетом
     * @return - экзепляр класса ValidateService
     */

    public static ValidateService getInstance() {
        return validateInstance;
    }

    /**
     * Метод проверяет введенное имя на валидность
     * @param user - пользователь
     * @return - валидные данные или нет
     */

    public boolean checkName(User user) {
        Matcher matcher = CHECK_OF_NAME.matcher(user.getName());
        return matcher.find();
    }

    /**
     * Метод проверяет введенный адрес почты на валидность
     * @param user - пользователь
     * @return - валидные данные или нет
     */

    public boolean checkEmail(User user) {
        Matcher matcher = CHECK_OF_EMAIL.matcher(user.getEmail());
        return matcher.find();
    }

    /**
     * Метод проверяет введенный логин на валидность
     * @param user - пользователь
     * @return - валидные данные или нет
     */

    public boolean checkLogin(User user) {
        Matcher matcher = CHECK_OF_LOGIN.matcher(user.getLogin());
        return matcher.find();
    }

    /**
     * Метод добавляет нового пользователя с учетом фильтра валидности
     * @param user - пользователь
     * @return - успешность операции
     */

    public boolean add(User user) {
        boolean result = false;
        if (checkName(user) && checkEmail(user) && checkLogin(user)) {
            logic.add(user);
            result = true;
            LOG.info("LOGGER: Successful execution to add");
        }
        return result;
    }

    /**
     * Метод обновляет данные существующего пользователя с учетом фильтра валидности
     * @param user - пользователь
     * @return - успешность операции
     */

    public boolean update(int id, User user) {
        boolean result = false;
        if (checkName(user) && checkEmail(user) && checkLogin(user)) {
            logic.update(id, user);
            result = true;
            LOG.info("LOGGER: Successful execution to update");
        }
        return result;
    }

    /**
     * Метод удаляет существующего пользователя
     * @return - успешность операции
     */

    public boolean delete(int id) {
        boolean result = logic.delete(id);
        if (result) {
            LOG.info("LOGGER: Successful execution to delete");
        }
        return result;
    }

    /**
     * Метод получает список существующих пользователей
     * @return - список существующих пользователей
     */

    public CopyOnWriteArrayList<User> findAll() {
        return logic.findAll();
    }

    /**
     * Метод получает данные существующего пользователя по id
     * @return - данные существующего пользователя
     */

    public User findById(int id) {
        return logic.findById(id);
    }
}

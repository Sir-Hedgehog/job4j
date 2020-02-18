package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 18.02.2020
 */

public class UserServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();
    private final Map<String, Consumer<HttpServletRequest>> actions = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    /**
     * Конструктор, в котором происходит инициализация функций каждого из возможных действий метода doPost() (см. шаблон Dispatch)
     */

    public UserServlet() {
        actions.put("add", this::add);
        actions.put("update", this::update);
        actions.put("delete", this::delete);
    }

    /**
     * Метод создания сервлета и его первого запуска
     */

    @Override
    public void init() {
        LOG.info("LOGGER: it's works the method init()");
    }

    /**
     * Метод выводит на страницу список всех зарегистрированных пользователей
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        List<User> users = collection.findAll();
        this.show(users, response);
        LOG.info("LOGGER: it's worked the method doGet()");
    }

    /**
     * Метод формирует различные функции для изменения списка пользователей и выводит их на страницу
     * @param request - запрос серверу
     * @param response - ответа сервера
     */

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String action = request.getParameter("action");
        Consumer<HttpServletRequest> consumer = actions.get(action);
        consumer.accept(request);
        LOG.info("LOGGER: it's worked the method doPost()");
        doGet(request, response);
    }

    /**
     * Метод проверки необходимых валидных ключей для добавления нового пользователя
     * @param request - запрос серверу
     */

    private void add(HttpServletRequest request) {
        this.collection.add(
                new User(
                        request.getParameter("name"),
                        request.getParameter("login"),
                        request.getParameter("email")));
        LOG.info("LOGGER: transfer of params to add");
    }

    /**
     * Метод проверки необходимых валидных ключей для обновления существующего пользователя
     * @param request - запрос серверу
     */

    private void update(HttpServletRequest request) {
        this.collection.update(
                Integer.valueOf(request.getParameter("id")),
                new User(
                        request.getParameter("name"),
                        request.getParameter("login"),
                        request.getParameter("email")));
        LOG.info("LOGGER: transfer of params to update");
    }

    /**
     * Метод проверки необходимого валидного ключа для удаления существующего пользователя
     * @param request - запрос серверу
     */

    private void delete(HttpServletRequest request) {
        this.collection.delete(Integer.valueOf(request.getParameter("id")));
        LOG.info("LOGGER: transfer of params to delete");
    }

    /**
     * Метод, формирующий результирующий список зарегистрированных пользователей
     * @param list - список пользователей
     * @param response - запрос серверу
     */

    private void show(List<User> list, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        for (User user : list) {
            out.println("<!DOCTYPE html>"
                        + "<html>"
                            + "<head>"
                                + "<title>"
                                    + "CRUD"
                                + "</title>"
                                + "<meta charset=\"utf-8\">"
                            + "</head>"
                            + "<body>"
                                + "<p>"
                                    + user.toString()
                                + "</p>"
                            + "</body>"
                        + "</html>");
        }
        out.flush();
    }
}

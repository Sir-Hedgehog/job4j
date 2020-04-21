package ru.job4j.controllers;

import ru.job4j.models.Account;
import ru.job4j.models.ValidateService;
import ru.job4j.models.Validation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 05.04.2020
 */

public class AccountServlet extends HttpServlet {
    private final Validation validation = ValidateService.getInstance();

    /**
     * Метод через ajax получает данные от пользователя с целью их проверки на корректность
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        if (this.writeData(username, phone)) {
            response.getWriter().write("Покупка билета успешно завершена!");
        } else {
            response.getWriter().write("Введите корректные данные!");
        }
    }

    /**
     * Проверка корректности данных и их добавление в случае успешности
     * @return успешность операции
     */

    private boolean writeData(String username, String phone) {
        return validation.addData(new Account(username, phone));
    }
}


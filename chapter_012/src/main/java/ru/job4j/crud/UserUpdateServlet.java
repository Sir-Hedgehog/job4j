package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 20.02.2020
 */

public class UserUpdateServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();

    /**
     * Метод получает данные о пользователе, которые необходимо обновить
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        response.sendRedirect(String.format("%s/edit.jsp?id=%s", request.getContextPath(), request.getParameter("id")));
    }

    /**
     * Метод формирует результат обновления данных о пользователе
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        boolean result = collection.update(
                Integer.valueOf(request.getParameter("id")),
                new User(
                        request.getParameter("name"),
                        request.getParameter("login"),
                        request.getParameter("email")));
        if (result) {
            response.sendRedirect(String.format("%s/validEdit.jsp", request.getContextPath()));
        } else {
            response.sendRedirect(String.format("%s/invalidEdit.jsp?id=%s", request.getContextPath(), request.getParameter("id")));
        }
    }
}

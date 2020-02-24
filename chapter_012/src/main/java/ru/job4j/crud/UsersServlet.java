package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 24.02.2020
 */

public class UsersServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();

    /**
     * Метод формирует список существующих пользователей
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        request.setAttribute("clients", collection.findAll());
        request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
    }

    /**
     * Метод удаляет пользовательский аккаунт при нажатии на кнопку "Удалить"
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        collection.delete(Integer.valueOf(request.getParameter("id")));
        this.doGet(request, response);
    }
}

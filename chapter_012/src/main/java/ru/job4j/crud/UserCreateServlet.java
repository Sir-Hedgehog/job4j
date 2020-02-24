package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 20.02.2020
 */

public class UserCreateServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();

    /**
     * Метод создает форму для добавления новых пользователей
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        request.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(request, response);
    }

    /**
     * Метод формирует результат добавления пользователя
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        boolean result = collection.add(
                new User(
                        request.getParameter("name"),
                        request.getParameter("login"),
                        request.getParameter("email")));
        if (result) {
            request.getRequestDispatcher("/WEB-INF/views/validCreate.jsp").forward(request, response);
            response.sendRedirect(String.format("%s/", request.getContextPath()));
        } else {
            request.getRequestDispatcher("/WEB-INF/views/invalidCreate.jsp").forward(request, response);
            response.sendRedirect(String.format("%s/", request.getContextPath()));
        }
    }
}

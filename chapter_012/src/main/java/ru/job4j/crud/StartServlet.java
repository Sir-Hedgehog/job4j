package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 24.02.2020
 */

public class StartServlet extends HttpServlet {

    /**
     * Метод формирует сервлет-контроллер для получения стартовой страницы
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        request.getRequestDispatcher("/WEB-INF/views/startView.jsp").forward(request, response);
    }
}


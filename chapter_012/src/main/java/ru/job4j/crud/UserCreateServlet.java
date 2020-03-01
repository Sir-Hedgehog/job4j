package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 01.03.2020
 */

public class UserCreateServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    /**
     * Метод создает форму для добавления новых пользователей
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        request.setAttribute("file", "Фото не выбрано");
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
        boolean result = this.collection.add(new User(
                request.getParameter("name"),
                request.getParameter("login"),
                request.getParameter("email"),
                request.getParameter("file")));
        LOG.info("RESULT: " + result);
        if (result) {
            request.getRequestDispatcher("/WEB-INF/views/validCreate.jsp").forward(request, response);
            response.sendRedirect(String.format("%s/", request.getContextPath()));
        } else {
            request.setAttribute("file", "Фото не выбрано");
            request.getRequestDispatcher("/WEB-INF/views/invalidCreate.jsp").forward(request, response);
            response.sendRedirect(String.format("%s/", request.getContextPath()));
        }
    }
}

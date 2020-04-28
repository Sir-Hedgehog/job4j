package ru.job4j.carsale.controllers;

import ru.job4j.carsale.models.ValidateService;
import ru.job4j.carsale.models.Validation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

public class UpdateStatusServlet extends HttpServlet {
    private final Validation validationDatabase = ValidateService.getInstance();

    /**
     * Метод получает введенный пользователем идентификатор и обновляет статус объявления, данные которого получаем из БД
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String joinId = request.getParameter("joinId");
        String result = validationDatabase.validateUpdate(joinId);
        response.getWriter().write(result);
    }
}

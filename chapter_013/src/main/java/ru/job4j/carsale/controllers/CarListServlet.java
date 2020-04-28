package ru.job4j.carsale.controllers;

import com.google.gson.Gson;
import ru.job4j.carsale.models.ValidateService;
import ru.job4j.carsale.models.Validation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

public class CarListServlet extends HttpServlet {
    private final Validation validationDatabase = ValidateService.getInstance();

    /**
     * Метод выводит список всех объявлений из БД
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<List<String>> result = validationDatabase.validateGetData();
        String json = new Gson().toJson(result);
        response.getWriter().write(json);
    }
}

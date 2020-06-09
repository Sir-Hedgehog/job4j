package ru.job4j.controllers;

import com.google.gson.Gson;
import ru.job4j.models.Hall;
import ru.job4j.models.ValidateService;
import ru.job4j.models.Validation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 09.06.2020
 */

public class HallServlet extends HttpServlet {
    private final Validation validation = ValidateService.getInstance();

    /**
     * Метод передает информацию о занятых местах в зале
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        CopyOnWriteArrayList<String> values = new CopyOnWriteArrayList<>();
        for (Hall hall : validation.getTakenPlaces()) {
            values.add("" + hall.getRow() + hall.getPlace());
        }
        String json = new Gson().toJson(values);
        response.getWriter().write(json);
    }
}

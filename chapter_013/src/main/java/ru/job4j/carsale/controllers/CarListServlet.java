package ru.job4j.carsale.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carsale.models.ValidateService;
import ru.job4j.carsale.models.Validation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 05.04.2020
 */

public class CarListServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CarListServlet.class);
    private final Validation validationDatabase = ValidateService.getInstance();

    /**
     * Метод выводит список объявлений из БД в зависимости от указанных фильтров
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String firm = request.getParameter("firm");
        String photo = request.getParameter("photo");
        String time = request.getParameter("time");
        List<List<String>> result  = validationDatabase.validateGetData(firm, photo, time);
        String json = new Gson().toJson(result);
        response.getWriter().write(json);
    }
}

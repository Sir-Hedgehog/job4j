package ru.job4j.controllers;

import com.google.gson.Gson;
import ru.job4j.models.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 09.06.2020
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
        response.setContentType("json/application");
        response.setCharacterEncoding("UTF-8");
        String row = request.getParameter("row");
        String place = request.getParameter("place");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        List<String> values = new ArrayList<>();
        if (this.writeData(row, place, username, phone)) {
            values.add("true");
            values.add(username);
            values.add(phone);
        } else {
            values.add("false");
        }
        String json = new Gson().toJson(values);
        response.getWriter().write(json);
    }

    /**
     * Проверка корректности данных и их добавление в случае успешности
     * @return - успешность операции
     */

    private boolean writeData(String row, String place, String username, String phone) {
        int intRow = Integer.parseInt(row);
        int intPlace = Integer.parseInt(place);
        return validation.validateTransaction(new Hall(intRow, intPlace), new Account(username, phone));
    }
}


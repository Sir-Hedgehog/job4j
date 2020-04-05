package ru.job4j.controllers;

import com.google.gson.Gson;
import ru.job4j.models.Hall;
import ru.job4j.models.ValidateService;
import ru.job4j.models.Validation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 05.04.2020
 */

public class ChoiceServlet extends HttpServlet {
    private final Validation validation = ValidateService.getInstance();

    /**
     * Метод получает данные о выбранном месте в кинотеатре и делает проверку на того, занято оно или нет (есть в БД или нет)
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String block = request.getParameter("block");
        List<String> result = new ArrayList<>();
        String json = "";
        String row = String.valueOf(block.charAt(0));
        String place = String.valueOf(block.charAt(1));
        result.add(row);
        result.add(place);
        if (this.writeData(row, place)) {
            json = new Gson().toJson(result);
            response.getWriter().write(json);
        }
    }

    /**
     * Проверка того, занято место или нет (есть в БД или нет)
     * @param row - ряд
     * @param place - место
     * @return успешность операции
     */

    private boolean writeData(String row, String place) {
        int intRow = Integer.parseInt(row);
        int intPlace = Integer.parseInt(place);
        return validation.choosePlace(new Hall(intRow, intPlace));
    }
}

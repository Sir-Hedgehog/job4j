package ru.job4j.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 05.04.2020
 */

public class DetailsServlet extends HttpServlet {

    /**
     * В зависимости от выбора места в зале метод формирует окончательную цену за билет
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String row = request.getParameter("row");
        String place = request.getParameter("place");
        String price = "0 рублей";
        if (row.equals("1")) {
            price = "250 рублей";
        } else if (row.equals("2")) {
            price = "280 рублей";
        } else if (row.equals("3")) {
            price = "310 рублей";
        }
        String output = "Вы выбрали ряд " + String.valueOf(row) + " место " + String.valueOf(place) + ". Сумма: " + price;
        response.getWriter().write(output);
    }
}

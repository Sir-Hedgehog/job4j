package ru.job4j.carsale.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

public class DownloadServlet extends HttpServlet {

    /**
     * Метод отображает фото продаваемого автомобиля
     * @param request  - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("name=" + name);
        response.setContentType("image/jpg");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        File file = new File("/bin/autos" + File.separator + name);
        try (FileInputStream in = new FileInputStream(file)) {
            response.getOutputStream().write(in.readAllBytes());
        }
    }
}

package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 5.0
 * @since 03.03.2020
 */

public class UsersServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    /**
     * Метод формирует список существующих пользователей
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        request.setAttribute("clients", collection.findAll());
        List<String> images = new ArrayList<>();
        File folder = new File("/bin/images/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (File name : folder.listFiles()) {
            images.add(name.getName());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Метод удаляет пользовательский аккаунт вместе с картинкой при нажатии на кнопку "Удалить"
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.deletePicture(request);
        this.doGet(request, response);
    }

    /**
     * Реализация удаления
     * @param request - запрос серверу
     */

    private void deletePicture(HttpServletRequest request) {
        boolean resultOfDelete = true;
        for (File name : Objects.requireNonNull(new File("/bin/images/").listFiles())) {
            if (name.getName().equals(collection.findById(Integer.valueOf(request.getParameter("id"))).getPhotoId())) {
                resultOfDelete = name.delete();
            }
        }
        if (resultOfDelete) {
            collection.delete(Integer.valueOf(request.getParameter("id")));
        }
    }
}
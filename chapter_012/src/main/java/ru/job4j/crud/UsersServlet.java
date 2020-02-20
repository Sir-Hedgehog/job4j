package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.02.2020
 */

public class UsersServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();

    /**
     * Метод формирует список существующих пользователей
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        List<User> users = collection.findAll();
        this.show(users, request, response);
    }

    /**
     * Метод удаляет пользовательский аккаунт при нажатии на кнопку "Удалить"
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        collection.delete(Integer.valueOf(request.getParameter("id")));
        this.doGet(request, response);
    }

    /**
     * Метод детализирует формирование списка на странице пользователя
     * @param list - список пользователей
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    private void show(List<User> list, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        StringBuilder builder = new StringBuilder("<table border='1' bgcolor='#99ffcc' bordercolor='#41ac77'>");
        for (User user : list) {
            builder.append("<tr><td>").append(user.getId()).append("</td>")
                    .append("<td>").append(user.getName()).append("</td>")
                    .append("<td>").append(user.getLogin()).append("</td>")
                    .append("<td>").append(user.getEmail()).append("</td>")
                    .append("<td>").append(user.getCreateDate()).append("</td></tr>");
        }
        builder.append("</table>");
        for (User user : list) {
            builder.append("<form class='submit' action='").append(request.getContextPath()).append("/edit' method='get'>");
            builder.append("    <input type='hidden' name='id' value='").append(user.getId()).append("'/>");
            builder.append("    <input type='submit' value='Редактировать'>");
            builder.append("</form>");
            builder.append("<form class='submit' action='").append(request.getContextPath()).append("/list' method='post'>");
            builder.append("    <input type='hidden' name='id' value='").append(user.getId()).append("'/>");
            builder.append("    <input type='submit' value='Удалить'>");
            builder.append("</form>");
            builder.append("<br/>");
        }
        out.println("<!DOCTYPE html>"
                + "<html lang='ru'>"
                + "     <head>"
                + "         <title>"
                + "             Список пользователей"
                + "         </title>"
                + "         <meta charset='utf-8'>"
                + "         <style type='text/css'>"
                + "             table {"
                + "                 float: left;"
                + "                 margin-right: 10px;"
                + "             }"
                + "             td {"
                + "                 border-bottom: 1px solid #99ffcc;"
                + "                 border-right: 1px solid #99ffcc;"
                + "                 padding: 2px 5px;"
                + "             }"
                + "             input {"
                + "                 margin: 3px;"
                + "             }"
                + "             .submit {"
                + "                 display: inline-block;"
                + "             }"
                + "         </style>"
                + "     </head>"
                + "     <body>"
                +            builder.toString()
                + "     </body>"
                + "</html>");
        out.flush();
    }
}

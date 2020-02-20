package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.02.2020
 */

public class UserCreateServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();

    /**
     * Метод создает форму для добавления новых пользователей
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        this.show(request, response);
    }

    /**
     * Метод формирует результат добавления пользователя
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        boolean result = collection.add(
                new User(
                        request.getParameter("name"),
                        request.getParameter("login"),
                        request.getParameter("email")));
        PrintWriter out = response.getWriter();
        if (result) {
            out.println("<!DOCTYPE html>"
                    + "<html lang='ru'>"
                    + "     <head>"
                    + "          <title>"
                    + "              Создание аккаунта"
                    + "          </title>"
                    + "          <meta charset=\"utf-8\">"
                    + "      </head>"
                    + "      <body>"
                    + "         <p>"
                    +               "Пользователь успешно добавлен!"
                    + "         </p>"
                    + "     </body>"
                    + "</html>");
        } else {
            out.println("<!DOCTYPE html>"
                    + "<html lang='ru'>"
                    + "     <head>"
                    + "          <title>"
                    + "              Создание аккаунта"
                    + "          </title>"
                    + "          <meta charset=\"utf-8\">"
                    + "      </head>"
                    + "      <body>"
                    + "         <p>"
                    +               "Введите корректные данные!"
                    + "         </p>"
                    + "     </body>"
                    + "</html>");
            doGet(request, response);
        }
    }

    /**
     * Метод детализирует работу формы для добавления
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    private void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>"
                + "<html lang='ru'>"
                + "     <head>"
                + "         <title>"
                + "             Создание аккаунта"
                + "         </title>"
                + "         <meta charset=\"utf-8\">"
                + "     </head>"
                + "     <body>"
                + "         <form action='" + request.getContextPath() + "/create' method='post'>"
                + "             <table>"
                + "                 <tr>"
                + "                     <td>Введите имя:</td> "
                + "                     <td><input type='text' name='name'/><td>"
                + "                 </tr>"
                + "                 <tr>"
                + "                     <td>Введите логин:</td>"
                + "                     <td><input type='text' name='login'/></td>"
                + "                 </tr>"
                + "                 <tr>"
                + "                     <td>Введите email:</td>"
                + "                     <td><input type='text' name='email'/></td>"
                + "                 </tr>"
                + "             </table>"
                + "             <input type='submit' value='Добавить'>"
                + "         </form>"
                + "     </body>"
                + "</html>");
    }
}

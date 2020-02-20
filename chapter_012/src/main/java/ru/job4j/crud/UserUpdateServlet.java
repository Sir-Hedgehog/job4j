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

public class UserUpdateServlet extends HttpServlet {
    private final Validate collection = ValidateService.getInstance();

    /**
     * Метод получает данные о пользователе, которые необходимо обновить
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        User user = collection.findById(Integer.valueOf(request.getParameter("id")));
        this.show(user, request, response);
    }

    /**
     * Метод формирует результат обновления данных о пользователе
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        boolean result = collection.update(
                Integer.valueOf(request.getParameter("id")),
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
                    + "          <meta charset='utf-8'>"
                    + "      </head>"
                    + "      <body>"
                    + "         <p>"
                    + "             Изменения успешно внесены!"
                    + "         </p>"
                    + "         <p>"
                    + "             Для проверки нажмите на ссылку: "
                    + "             <a href='" + request.getContextPath() + "/list' title='Список пользователей'>обновленный список</a>"
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
                    + "          <meta charset='utf-8'>"
                    + "      </head>"
                    + "      <body>"
                    + "         <p>"
                    +               "Для изменения информации введите корректные данные!"
                    + "         </p>"
                    + "     </body>"
                    + "</html>");
            doGet(request, response);
        }
    }

    /**
     * Метод детализирует форму для обновления пользователя
     * @param user - выбранный пользователь для обновления его данных
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    private void show(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>"
                + "<html lang='ru'>"
                + "     <head>"
                + "         <title>"
                + "             Обновление данных"
                + "         </title>"
                + "         <meta charset=\"utf-8\">"
                + "     </head>"
                + "     <body>"
                + "         <form action='" + request.getContextPath() + "/edit' method='post'/>"
                + "             <p>"
                + "                 Имя: "
                + "                 <input type='text' name='name' value='" + user.getName() + "'/>"
                + "             </p>"
                + "             <p>"
                + "                 Логин: "
                + "                 <input type='text' name='login' value='" + user.getLogin() + "'/>"
                + "             </p>"
                + "             <p>"
                + "                 Электронная почта: "
                + "                 <input type='text' name='email' value='" + user.getEmail() + "'/>"
                + "             </p>"
                + "             <input type='hidden' name='id' value='" + user.getId() + "'/>"
                + "             <input type='submit' value='Сохранить'>"
                + "         </form>"
                + "     </body>"
                + "</html>");
    }
}

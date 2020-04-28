package ru.job4j.carsale.controllers;

import ru.job4j.carsale.models.Car;
import ru.job4j.carsale.models.Seller;
import ru.job4j.carsale.models.ValidateService;
import ru.job4j.carsale.models.Validation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

public class AddCarServlet extends HttpServlet {
    private final Validation validationDatabase = ValidateService.getInstance();

    /**
     * Метод получает данные нового объявления, передает для добавления в БД и получает идентификатор нового пользователя
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String result;
        //данные согласно новому объявлению
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String selectedImage = request.getParameter("selectedImage");
        String model = request.getParameter("model");
        String bodyType = request.getParameter("bodyType");
        String yearOfRelease = request.getParameter("yearOfRelease");
        String volume = request.getParameter("volume");
        String power = request.getParameter("power");
        String mileage = request.getParameter("mileage");
        String price = request.getParameter("price");
        //сохранение данных о продавце
        Seller seller = new Seller();
        seller.setName(name);
        seller.setNumber(phone);
        //сохранение данных о автомобиле
        Car car = new Car(seller);
        car.setImage(selectedImage);
        car.setModel(model);
        car.setBodyType(bodyType);
        car.setYearOfRelease(yearOfRelease);
        car.setVolume(volume);
        car.setPower(power);
        car.setMileage(mileage);
        car.setPrice(price);
        car.setStatus("в продаже");
        //результат генерации идентификатора пользователя
        if (validationDatabase.validateAdd(seller, car)) {
            result = "" + seller.getId();
        } else {
            result = "";
        }
        response.getWriter().write(result);
    }
}

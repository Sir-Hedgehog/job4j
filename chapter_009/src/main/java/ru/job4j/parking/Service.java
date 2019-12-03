package ru.job4j.parking;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 3.12.2019
 */

public class Service {
    private BasicParking basicParking;
    public Service(BasicParking basicParking) {
        this.basicParking = basicParking;
    }

    /**
     * Метод сообщает клиенту, можно ли припарковать его автомобиль. Если да, то паркуем.
     * @param cars - машина клиента
     * @return - ответ клиенту на его запрос припарковать машину
     */

    public String distribute(Cars cars) {
        String result = "";
        if (basicParking.accept(cars)) {
            result = "Автомобиль успешно припаркован!";
        } else {
            result = "Нет парковочных мест";
        }
        return result;
    }
}

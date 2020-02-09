package ru.job4j;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 09.02.2020
 */

public interface Elevator {

    /**
     * Метод открывает двери лифта
     */

    void openDoors();

    /**
     * Метод закрывает двери лифта
     */

    void closeDoors();

    /**
     * Метод проверяет, не перегружен ли лифт по количеству человек
     * @param limit - максимальное количество человек
     * @return - перегружен лифт или нет
     */

    boolean acceptPeople(int limit);

    /**
     * Метод дает людям доступ выйти из лифта
     */

    void letGo();

    /**
     * Метод подымает лифт наверх
     * @param floor - этаж, на который необходимо подняться
     * @return - успешность результата
     */

    boolean rise(int floor);

    /**
     * Метод опускает лифт вниз
     * @param floor - этаж, на который необходимо опуститься
     * @return - успешность результата
     */

    boolean descend(int floor);
}

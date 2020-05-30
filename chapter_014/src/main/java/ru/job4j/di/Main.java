package ru.job4j.di;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.05.2020
 */

public class Main {
    public static void main(String[] args) {
        //регистрация классов
        Context context = new Context();
        context.register(Store.class);
        context.register(ConsoleInput.class);
        context.register(StartUI.class);
        //получение основного объекта и его зависимостей
        StartUI ui = context.get(StartUI.class);
        //использование методов основного объекта и его зависимостей
        ui.ask("Хотите сыграть в беспроигрышную лотерею?");
        ui.ask("Билет для участия стоит сущие копейки!");
        ui.add("Вращаем барабан!");
        ui.add("С Вас 100 баксов!");
        ui.print();
    }
}

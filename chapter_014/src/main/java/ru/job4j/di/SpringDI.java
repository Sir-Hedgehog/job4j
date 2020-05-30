package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 08.05.2020
 */

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //сканирование классов
        context.scan("ru.job4j.di");
        context.refresh();
        //получение основного объекта и его зависимостей
        StartUI ui = context.getBean(StartUI.class);
        //использование методов основного объекта и его зависимостей
        ui.ask("Хотите сыграть в беспроигрышную лотерею?");
        ui.ask("Билет для участия стоит сущие копейки!");
        ui.add("Вращаем барабан!");
        ui.add("С Вас 100 баксов!");
        ui.print();
    }
}

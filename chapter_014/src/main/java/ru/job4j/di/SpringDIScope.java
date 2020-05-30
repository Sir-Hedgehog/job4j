package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 09.05.2020
 */

public class SpringDIScope {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //сканирование классов
        context.scan("ru.job4j.di");
        context.refresh();
        //получение объект класса в режиме prototype
        Store store = context.getBean(Store.class);
        store.add("The part of store");
        Store another = context.getBean(Store.class);
        another.add("The part of another");
        //вывод только результата добавления в объект another
        another.getAll().forEach(System.out::println);
    }
}

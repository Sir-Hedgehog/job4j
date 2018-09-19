package ru.job4j.tracker;

import java.sql.SQLOutput;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 19.09.2018
 */

public class StartUI {
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String REPLACE = "2";
    private static final String DELETE = "3";
    private static final String ID = "4";
    private static final String NAME = "5";
    private static final String EXIT = "6";
    private final Input input;
    private final Tracker tracker;

    /**
     * Конструтор, инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showItems();
            } else if (REPLACE.equals(answer)) {
                this.replaceItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (ID.equals(answer)) {
                this.showById();
            } else if (NAME.equals(answer)) {
                this.showByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавление новой заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка принята. Номер идентификатора: " + item.getId() + "-----------");
    }

    /**
     * Метод показывает все существующие заявки.
     */
    private void showItems() {
        System.out.println("------------ Вывод всех заявок --------------");
        Item[] item = this.tracker.findAll();
        if (item != null) {
            for (int index = 0; index < item.length; index++) {
                System.out.println("Заявка №" + (index + 1) + ": ");
                System.out.println(item[index].toString());
                System.out.println();
            }
        } else {
            System.out.println("------------ Заявок нет! --------------");
        }
    }

    /**
     * Метод обновляет заявку.
     */
    private void replaceItem() {
        System.out.println("------------ Обновление существующей заявки --------------");
        String id = this.input.ask("Введите идентификатор заявки: ");
        String name = this.input.ask("Введите новое имя заявки: ");
        String desc = this.input.ask("Введите новое описание заявки: ");
        Item item = new Item(name, desc);
        boolean result = this.tracker.replace(id, item);
        System.out.println("------------ Результат обновления --------------");
        if (!result) {
            System.out.println("------------ Существующая заявка с номером: " + item.getId() +  " обновлена! -----------");
        } else {
            System.out.println("------------ Заявка не найдена! --------------");
        }
    }

    /**
     * Метод удаляет заявку.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите идентификатор заявки, которую необходимо удалить: ");
        boolean result = this.tracker.delete(id);
        if (!result) {
            System.out.println("------------ Заявка успешно удалена --------------");
        } else {
            System.out.println("------------ Заявка не найдена --------------");
        }
    }

    /**
     * Метод находит заявку по идентификатору.
     */
    private void showById() {
        System.out.println("------------ Поиск по идентификатору --------------");
        String id = this.input.ask("Введите идентификатор заявки, которую необходимо найти: ");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("------------ Результат поиска --------------");
            System.out.println(item);
        } else {
            System.out.println("------------ Заявка не найдена! --------------");
        }
    }

    /**
     * Метод находит заявку по имени.
     */
    private void showByName() {
        System.out.println("------------ Поиск по имени --------------");
        String name = this.input.ask("Введите имя заявки, которую необходимо найти: ");
        Item[] item = this.tracker.findByName(name);
        System.out.println("------------ Результат поиска --------------");
        if (item != null) {
            for (int index = 0; index < item.length; index++) {
                System.out.println("Заявка с наименованием " + name + " №" + (index + 1) + ": ");
                System.out.println(item[index].toString());
            }
        } else {
            System.out.println("------------ Заявка не найдена! --------------");
        }
    }

    private void showMenu() {
        System.out.println("Меню");
        System.out.println("0. Добавить новую заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по идентификатору");
        System.out.println("5. Найти заявки по имени");
        System.out.println("6. Выйти");
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}

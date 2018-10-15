package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 10.10.2018
 */

public class MenuTracker {

    private final Input input;
    private final Tracker tracker;

    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Добавить новую заявку"));
        this.actions.add(new ShowItems(1, "Показать все заявки"));
        this.actions.add(new ReplaceItem(2, "Редактировать заявку"));
        this.actions.add(new DeleteItem(3, "Удалить заявку"));
        this.actions.add(new FindItemsById(4, "Найти заявку по идентификатору"));
        this.actions.add(new FindItemsByName(5, "Найти заявки по имени"));
        this.actions.add(new ExitProgram(6, "Выйти", ui));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        System.out.println("Меню");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Метод, передающий массив с заполненными ключами.
     */
    public int[] fillKeys() {
        int[] limit = new int[this.actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            limit[i] = actions.get(i).key();
        }
        return limit;
    }

    public class AddItem extends BaseAction {

        public AddItem(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка принята. Номер идентификатора: " + item.getId() + "-----------");
        }
    }

    public class ShowItems extends BaseAction {
        private int number;
        private String desc;

        public ShowItems(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Вывод всех заявок --------------");
            Item[] items = tracker.findAll();
            if (items.length > 0) {
                for (Item item : items) {
                    System.out.println(item);
                }
            } else {
                System.out.println("------------ Заявок нет! --------------");
            }
        }
    }

    public class ReplaceItem extends BaseAction {

        public ReplaceItem(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Обновление существующей заявки --------------");
            String id = input.ask("Введите идентификатор заявки: ");
            String name = input.ask("Введите новое имя заявки: ");
            String desc = input.ask("Введите новое описание заявки: ");
            Item item = new Item(name, desc);
            boolean result = tracker.replace(id, item);
            System.out.println("------------ Результат обновления --------------");
            if (result) {
                System.out.println("------------ Существующая заявка с номером: " + item.getId() + " обновлена! -----------");
            } else {
                System.out.println("------------ Заявка не найдена! --------------");
            }
        }
    }

    public class DeleteItem extends BaseAction {

        public DeleteItem(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите идентификатор заявки, которую необходимо удалить: ");
            boolean result = tracker.delete(id);
            if (result) {
                System.out.println("------------ Заявка успешно удалена --------------");
            } else {
                System.out.println("------------ Заявка не найдена --------------");
            }
        }
    }

    public class FindItemsById extends BaseAction {

        public FindItemsById(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск по идентификатору --------------");
            String id = input.ask("Введите идентификатор заявки, которую необходимо найти: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("------------ Результат поиска --------------");
                System.out.println(item);
            } else {
                System.out.println("------------ Заявка не найдена! --------------");
            }
        }
    }

    public class FindItemsByName extends BaseAction {

        public FindItemsByName(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск по имени --------------");
            String name = input.ask("Введите имя заявки, которую необходимо найти: ");
            Item[] item = tracker.findByName(name);
            System.out.println("------------ Результат поиска --------------");
            if (item.length > 0) {
                for (int index = 0; index < item.length; index++) {
                    System.out.println("Заявка с наименованием " + name + " №" + (index + 1) + ": ");
                    System.out.println(item[index].toString());
                }
            } else {
                System.out.println("------------ Заявка не найдена! --------------");
            }
        }
    }

    public class ExitProgram implements UserAction {
        private final StartUI ui;
        private int number;
        private String desc;

        public ExitProgram(int number, String desc, StartUI ui) {
            this.ui = ui;
            this.number = number;
            this.desc = desc;
        }

        @Override
        public int key() {
            return number;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            this.ui.stop();
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), desc);
        }
    }
}


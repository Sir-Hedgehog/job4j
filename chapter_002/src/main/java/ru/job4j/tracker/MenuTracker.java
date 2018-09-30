package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
    private static final int ADD = 0;
    private static final int SHOW = 1;
    private static final int REPLACE = 2;
    private static final int DELETE = 3;
    private static final int ID = 4;
    private static final int NAME = 5;
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
     * Метод для получения массива меню.
     * @return длину массива
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem(0, "Добавление новой заявки"));
        this.actions.add(new ShowItems(1, "Вывод всех заявок"));
        this.actions.add(new ReplaceItem(2, "Обновление существующей заявки"));
        this.actions.add(new DeleteItem(3, "Удаление заявки"));
        this.actions.add(new FindItemsById(4, "Поиск по идентификатору"));
        this.actions.add(new FindItemsByName(5, "Поиск по имени"));
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
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public class AddItem implements UserAction {
        private int number;
        private String desc;

        public AddItem(int number, String desc) {
            this.number = number;
            this.desc = desc;
        }

        @Override
        public int key() {
            return ADD;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавление новой заявки");
        }
    }

    public class ShowItems implements UserAction {
        private int number;
        private String desc;

        public ShowItems(int number, String desc) {
            this.number = number;
            this.desc = desc;
        }

        @Override
        public int key() {
            return SHOW;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Вывод всех заявок");
        }
    }

    public class ReplaceItem implements UserAction {
        private int number;
        private String desc;

        public ReplaceItem(int number, String desc) {
            this.number = number;
            this.desc = desc;
        }

        @Override
        public int key() {
            return REPLACE;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Обновление существующей заявки");
        }
    }

    public class DeleteItem implements UserAction {
        private int number;
        private String desc;

        public DeleteItem(int number, String desc) {
            this.number = number;
            this.desc = desc;
        }

        @Override
        public int key() {
            return DELETE;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удаление заявки");
        }
    }

    public class FindItemsById implements UserAction {
        private int number;
        private String desc;

        public FindItemsById(int number, String desc) {
            this.number = number;
            this.desc = desc;
        }

        @Override
        public int key() {
            return ID;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Поиск по идентификатору");
        }
    }

    public class FindItemsByName implements UserAction {
        private int number;
        private String desc;

        public FindItemsByName(int number, String desc) {
            this.number = number;
            this.desc = desc;
        }

        @Override
        public int key() {
            return NAME;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Поиск по имени");
        }
    }
}


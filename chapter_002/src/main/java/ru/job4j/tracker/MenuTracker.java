package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 31.01.2019
 */

class MenuTracker {

    private final Input input;
    private final Tracker tracker;
    private final Consumer<String> output;
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     * @param output вывод данных типа Consumer
     */
    MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Метод заполняет список.
     */
    void fillActions(StartUI ui) {
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
    void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    void show() {
        output.accept("Меню");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Метод, передающий массив с заполненными ключами.
     */
    int[] fillKeys() {
        int[] limit = new int[this.actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            limit[i] = actions.get(i).key();
        }
        return limit;
    }

    public class AddItem extends BaseAction {

        AddItem(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept(String.format("------------ Новая заявка принята. Номер идентификатора: %s -----------", item.getId()));
        }
    }

    public class ShowItems extends BaseAction {
        private int number;
        private String desc;

        ShowItems(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Вывод всех заявок --------------");
            List<Item> list = tracker.findAll();
            if (list.size() > 0) {
                for (Item item : list) {
                    output.accept(item.toString());
                }
            } else {
                output.accept("------------ Заявок нет! --------------");
            }
        }
    }

    public class ReplaceItem extends BaseAction {

        ReplaceItem(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Обновление существующей заявки --------------");
            String id = input.ask("Введите идентификатор заявки: ");
            String name = input.ask("Введите новое имя заявки: ");
            String desc = input.ask("Введите новое описание заявки: ");
            Item item = new Item(name, desc);
            boolean result = tracker.replace(id, item);
            output.accept("------------ Результат обновления --------------");
            if (result) {
                output.accept(String.format("------------ Существующая заявка с номером: %s обновлена! -----------", item.getId()));
            } else {
                output.accept("------------ Заявка не найдена! --------------");
            }
        }
    }

    public class DeleteItem extends BaseAction {

        DeleteItem(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Удаление заявки --------------");
            String id = input.ask("Введите идентификатор заявки, которую необходимо удалить: ");
            boolean result = tracker.delete(id);
            if (result) {
                output.accept("------------ Заявка успешно удалена --------------");
            } else {
                output.accept("------------ Заявка не найдена --------------");
            }
        }
    }

    public class FindItemsById extends BaseAction {

        FindItemsById(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Поиск по идентификатору --------------");
            String id = input.ask("Введите идентификатор заявки, которую необходимо найти: ");
            Item item = tracker.findById(id);
            if (item != null) {
                output.accept("------------ Результат поиска --------------");
                output.accept(item.toString());
            } else {
                output.accept("------------ Заявка не найдена! --------------");
            }
        }
    }

    public class FindItemsByName extends BaseAction {

        FindItemsByName(int number, String desc) {
            super(number, desc);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Поиск по имени --------------");
            String name = input.ask("Введите имя заявки, которую необходимо найти: ");
            List<Item> list = tracker.findByName(name);
            output.accept("------------ Результат поиска --------------");
            if (list.size() > 0) {
                for (int index = 0; index < list.size(); index++) {
                    output.accept(String.format("Заявка с наименованием %s №: %d: %s", name, index + 1, list.get(index).toString()));
                }
            } else {
                output.accept("------------ Заявка не найдена! --------------");
            }
        }
    }

    public class ExitProgram implements UserAction {
        private final StartUI ui;
        private int number;
        private String desc;

        ExitProgram(int number, String desc, StartUI ui) {
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


package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 28.09.2018
 */

public class StartUIConsoleTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private String showMenu() {
        StringBuilder menu = new StringBuilder()
                .append("Меню").append(System.lineSeparator())
                .append("0. Добавить новую заявку").append(System.lineSeparator())
                .append("1. Показать все заявки").append(System.lineSeparator())
                .append("2. Редактировать заявку").append(System.lineSeparator())
                .append("3. Удалить заявку").append(System.lineSeparator())
                .append("4. Найти заявку по идентификатору").append(System.lineSeparator())
                .append("5. Найти заявки по имени").append(System.lineSeparator())
                .append("6. Выйти").append(System.lineSeparator());
        return menu.toString();
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserSearchAllItemsThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test name", "desc"),
                new Item("test name1", "desc1"),
                new Item("test name2", "desc2")};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();

        StringBuilder result = new StringBuilder()
                .append(showMenu())
                .append("------------ Вывод всех заявок --------------").append(System.lineSeparator())
                .append(items[0]).append(System.lineSeparator())
                .append(items[1]).append(System.lineSeparator())
                .append(items[2]).append(System.lineSeparator())
                .append(showMenu());
        assertThat(new String(out.toString()), is(result.toString()));
    }

    @Test
    public void whenUpdateThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        StringBuilder result = new StringBuilder()
                .append(showMenu())
                .append("------------ Обновление существующей заявки --------------").append(System.lineSeparator())
                .append("------------ Результат обновления --------------").append(System.lineSeparator())
                .append("------------ Существующая заявка с номером: " + item.getId() + " обновлена! -----------").append(System.lineSeparator())
                .append(showMenu());
        assertThat(new String(out.toString()), is(result.toString()));
    }

    @Test
    public void whenDeleteItemThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item first = new Item("test name", "desc");
        tracker.add(first);
        Item second = new Item("test name", "desc");
        tracker.add(second);
        Input input = new StubInput(new String[]{"3", first.getId(), "6"});
        new StartUI(input, tracker).init();
        StringBuilder result = new StringBuilder()
                .append(showMenu())
                .append("------------ Удаление заявки --------------").append(System.lineSeparator())
                .append("------------ Заявка успешно удалена --------------").append(System.lineSeparator())
                .append(showMenu());
        assertThat(new String(out.toString()), is(result.toString()));
    }

    @Test
    public void whenIdThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        StringBuilder result = new StringBuilder()
                .append(showMenu())
                .append("------------ Поиск по идентификатору --------------").append(System.lineSeparator())
                .append("------------ Результат поиска --------------").append(System.lineSeparator())
                .append(item).append(System.lineSeparator())
                .append(showMenu());
        assertThat(new String(out.toString()), is(result.toString()));
    }

    @Test
    public void whenNameThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", "test name", "6"});
        new StartUI(input, tracker).init();
        StringBuilder result = new StringBuilder()
                .append(showMenu())
                .append("------------ Поиск по имени --------------").append(System.lineSeparator())
                .append("------------ Результат поиска --------------").append(System.lineSeparator())
                .append("Заявка с наименованием test name №1: ").append(System.lineSeparator())
                .append(item).append(System.lineSeparator())
                .append(showMenu());
        assertThat(new String(out.toString()), is(result.toString()));
    }
}


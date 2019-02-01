package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 01.02.2019
 */

public class StartUIConsoleTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);
        @Override
        public void accept(String string) {
            stdout.println(string);
        }

        @Override
        public String toString() {
            return out.toString();
        }
    };

    private String showMenu() {
        return "Меню" + System.lineSeparator()
                + "0. Добавить новую заявку" + System.lineSeparator()
                + "1. Показать все заявки" + System.lineSeparator()
                + "2. Редактировать заявку" + System.lineSeparator()
                + "3. Удалить заявку" + System.lineSeparator()
                + "4. Найти заявку по идентификатору" + System.lineSeparator()
                + "5. Найти заявки по имени" + System.lineSeparator()
                + "6. Выйти" + System.lineSeparator();
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
        new StartUI(input, tracker, output).init();
        String expect = showMenu()
                + "------------ Вывод всех заявок --------------" + System.lineSeparator()
                + items[0] + System.lineSeparator()
                + items[1] + System.lineSeparator()
                + items[2] + System.lineSeparator()
                + showMenu();
        assertThat(this.output.toString(), is(expect));
    }

    @Test
    public void whenUpdateThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker, output).init();
        String expect = showMenu()
                + "------------ Обновление существующей заявки --------------" + System.lineSeparator()
                + "------------ Результат обновления --------------" + System.lineSeparator()
                + "------------ Существующая заявка с номером: " + item.getId() + " обновлена! -----------" + System.lineSeparator()
                + showMenu();
        assertThat(this.output.toString(), is(expect));
    }

    @Test
    public void whenDeleteItemThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item first = new Item("test name", "desc");
        tracker.add(first);
        Item second = new Item("test name", "desc");
        tracker.add(second);
        Input input = new StubInput(new String[]{"3", first.getId(), "6"});
        new StartUI(input, tracker, output).init();
        String expect = showMenu()
                + "------------ Удаление заявки --------------" + System.lineSeparator()
                + "------------ Заявка успешно удалена --------------" + System.lineSeparator()
                + showMenu();
        assertThat(this.output.toString(), is(expect));
    }

    @Test
    public void whenIdThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker, output).init();
        String expect = showMenu() + "------------ Поиск по идентификатору --------------"
                + System.lineSeparator() + "------------ Результат поиска --------------"
                + System.lineSeparator() + item + System.lineSeparator()
                + showMenu();
        assertThat(this.output.toString(), is(expect));
    }

    @Test
    public void whenNameThenAdminHadSeenThat() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", "test name", "6"});
        new StartUI(input, tracker, output).init();
        String expect = showMenu() + "------------ Поиск по имени --------------" + System.lineSeparator()
                + "------------ Результат поиска --------------" + System.lineSeparator()
                + "Заявка с наименованием test name №1: " + item
                + System.lineSeparator() + showMenu();
        assertThat(this.output.toString(), is(expect));
    }
}


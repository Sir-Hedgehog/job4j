package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 28.09.2018
 */

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input).init();
        assertThat(menu.new ShowItems(1, "Показать все заявки").execute(input, tracker), is("test name"));
    }

    @Test
    public void whenUserSearchAllItemsThenTrackerShowsItems() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test name", "desc"),
                new Item("test name1", "desc1"),
                new Item("test name2", "desc2")};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        Input input = new StubInput(new String[]{"1", "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input).init();
        assertThat(tracker.findAll(), is(items));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteItemThenTrackerHasNotDeletedItem() {
        Tracker tracker = new Tracker();
        Item first = new Item("test name", "desc");
        tracker.add(first);
        Item second = new Item("test name", "desc");
        tracker.add(second);
        Input input = new StubInput(new String[]{"3", first.getId(), "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input).init();
        assertThat(tracker.findAll()[0], is(second));
    }

    @Test
    public void whenIdThenTrackerHasExistingItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input).init();
        assertThat(tracker.findAll()[0], is(item));
    }


    @Test
    public void whenNameThenTrackerHasExistingItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", "test name", "6"});
        MenuTracker menu = new MenuTracker(input, tracker);
        new StartUI(input).init();
        assertThat(tracker.findAll()[0], is(item));
    }
}
package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.12.2018
 */

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUserSearchAllItemsThenTrackerShowsItems() {
        Tracker tracker = new Tracker();
        List<Item> list = new ArrayList<>();
        list.add(new Item("test name", "desc"));
        list.add(new Item("test name1", "desc1"));
        list.add(new Item("test name2", "desc2"));
        tracker.add(list.get(0));
        tracker.add(list.get(1));
        tracker.add(list.get(2));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(list));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
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
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0), is(second));
    }

    @Test
    public void whenIdThenTrackerHasExistingItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0), is(item));
    }


    @Test
    public void whenNameThenTrackerHasExistingItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", "test name", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0), is(item));
    }
}
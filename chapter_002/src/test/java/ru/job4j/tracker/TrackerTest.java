package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 18.09.2018
 */

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemOfBaseThenReturnBase() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription");
        tracker.add(first);
        Item second = new Item("test2", "testDescription2");
        tracker.add(second);
        tracker.delete(first.getId());
        assertThat(tracker.findAll()[0], is(second));
    }

    @Test
    public void whenTrackerHasItemsThenShowItems() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test1", "testDescription"),
        new Item("test2", "testDescription1"),
        new Item("test3", "testDescription2")};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        assertThat(tracker.findAll(), is(items));

    }

    @Test
    public void whenInputNameThenTrackerGivesSameItem() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test1", "testDescription"),
        new Item("test2", "testDescription2"),
        new Item("test1", "testDescription3")};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        Item[] expect = {items[0], items[2]};
        assertThat(tracker.findByName("test1"), is(expect));
    }

    @Test
    public void whenInputIdThenExistingItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        tracker.findById(item.getId());
        assertThat(tracker.findAll()[0], is(item));
    }
}

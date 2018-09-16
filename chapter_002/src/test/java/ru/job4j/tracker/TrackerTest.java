package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 16.09.2018
 */

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemOfBaseThenReturnBase() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        tracker.delete(first.getId());
        assertThat(tracker.findAll()[0], is(second));
    }

    @Test
    public void whenTrackerHasItemsThenShowItems() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription1", 1234L);
        tracker.add(second);
        Item third = new Item("test3", "testDescription2", 12345L);
        tracker.add(third);
        assertThat(tracker.findAll()[0], is(first));
        assertThat(tracker.findAll()[1], is(second));
        assertThat(tracker.findAll()[2], is(third));
    }

    @Test
    public void whenInputNameThenTrackerGivesSameItem() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        Item third = new Item("test3", "testDescription3", 12345L);
        tracker.add(third);
        assertThat(tracker.findByName("test1"), is(third));
    }

    @Test
    public void whenInputIdThenExistingItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        tracker.findById(item.getId());
        assertThat(tracker.findAll()[0], is(item));
    }
}

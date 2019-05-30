package ru.job4j.trackerSQL;

import org.junit.Test;
import ru.job4j.tracker.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 30.05.2019
 */

public class TrackerSQLTest {
    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        TrackerSQL sql = new TrackerSQL();
        Item item = new Item("Frank", "Blue screen");
        sql.add(item);
        assertThat(sql.findAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        TrackerSQL tracker = new TrackerSQL();
        Item previous = new Item("test1", "testDescription");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemOfBaseThenReturnBase() {
        TrackerSQL tracker = new TrackerSQL();
        Item first = new Item("test1", "testDescription");
        tracker.add(first);
        Item second = new Item("test2", "testDescription2");
        tracker.add(second);
        tracker.delete(first.getId());
        assertThat(tracker.findAll().get(0), is(second));
    }

    @Test
    public void whenTrackerHasItemsThenShowItems() {
        TrackerSQL tracker = new TrackerSQL();
        List<Item> list = new ArrayList<>();
        list.add(new Item("test1", "testDescription"));
        list.add(new Item("test2", "testDescription1"));
        list.add(new Item("test3", "testDescription2"));
        tracker.add(list.get(0));
        tracker.add(list.get(1));
        tracker.add(list.get(2));
        assertThat(tracker.findAll(), is(list));
    }

    @Test
    public void whenInputNameThenTrackerGivesSameItem() {
        TrackerSQL tracker = new TrackerSQL();
        List<Item> list = new ArrayList<>();
        list.add(new Item("test1", "testDescription"));
        list.add(new Item("test2", "testDescription2"));
        list.add(new Item("test1", "testDescription3"));
        tracker.add(list.get(0));
        tracker.add(list.get(1));
        tracker.add(list.get(2));
        List<Item> expect = Arrays.asList(list.get(0), list.get(2));
        assertThat(tracker.findByName("test1"), is(expect));
    }

    @Test
    public void whenInputIdThenExistingItem() {
        TrackerSQL tracker = new TrackerSQL();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        tracker.findById(item.getId());
        assertThat(tracker.findAll().get(0), is(item));
    }
}
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
        if (sql.init()) {
            Item item = new Item("Frank", "Blue screen");
            sql.add(item);
            assertThat(sql.findAll().get(0), is(item));
        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        TrackerSQL sql = new TrackerSQL();
        if (sql.init()) {
            Item previous = new Item("test1", "testDescription");
            sql.add(previous);
            Item next = new Item("test2", "testDescription2");
            next.setId(previous.getId());
            sql.replace(previous.getId(), next);
            assertThat(sql.findById(previous.getId()).getName(), is("test2"));
        }
    }

    @Test
    public void whenDeleteItemOfBaseThenReturnBase() {
        TrackerSQL sql = new TrackerSQL();
        if (sql.init()) {
            Item first = new Item("test1", "testDescription");
            sql.add(first);
            Item second = new Item("test2", "testDescription2");
            sql.add(second);
            sql.delete(first.getId());
            assertThat(sql.findAll().get(0), is(second));
        }
    }

    @Test
    public void whenTrackerHasItemsThenShowItems() {
        TrackerSQL sql = new TrackerSQL();
        if (sql.init()) {
            List<Item> list = new ArrayList<>();
            list.add(new Item("test1", "testDescription"));
            list.add(new Item("test2", "testDescription1"));
            list.add(new Item("test3", "testDescription2"));
            sql.add(list.get(0));
            sql.add(list.get(1));
            sql.add(list.get(2));
            assertThat(sql.findAll(), is(list));
        }
    }

    @Test
    public void whenInputNameThenTrackerGivesSameItem() {
        TrackerSQL sql = new TrackerSQL();
        if (sql.init()) {
            List<Item> list = new ArrayList<>();
            list.add(new Item("test1", "testDescription"));
            list.add(new Item("test2", "testDescription2"));
            list.add(new Item("test1", "testDescription3"));
            sql.add(list.get(0));
            sql.add(list.get(1));
            sql.add(list.get(2));
            List<Item> expect = Arrays.asList(list.get(0), list.get(2));
            assertThat(sql.findByName("test1"), is(expect));
        }
    }

    @Test
    public void whenInputIdThenExistingItem() {
        TrackerSQL sql = new TrackerSQL();
        if (sql.init()) {
            Item item = new Item("test1", "testDescription");
            sql.add(item);
            sql.findById(item.getId());
            assertThat(sql.findAll().get(0), is(item));
        }
    }
}
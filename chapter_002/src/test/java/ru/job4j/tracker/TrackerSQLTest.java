package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.Item;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 10.08.2019
 */

public class TrackerSQLTest {

    private Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkFindByName() throws Exception {
        Connection connection = ConnectionRollback.create(this.init());
        TrackerSQL tracker = new TrackerSQL(connection);
        tracker.createTable();
        Item item = new Item("Хабиб", "Нурмагомедов");
        tracker.add(item);
        assertThat(tracker.findByName("Хабиб").size(), is(1));
    }

    @Test
    public void checkFindById() throws Exception {
        Connection connection = ConnectionRollback.create(this.init());
        TrackerSQL tracker = new TrackerSQL(connection);
        tracker.createTable();
        Item item = new Item("Брайн", "Ортега");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()).getName(), is(tracker.findByName("Брайн").get(0).getName()));
    }

    @Test
    public void checkFindAll() throws Exception {
        Connection connection = ConnectionRollback.create(this.init());
        TrackerSQL tracker = new TrackerSQL(connection);
        tracker.createTable();
        List<Item> list = List.of(
                new Item("Даниэль", "Кормье"),
                new Item("Джон", "Джонс"),
                new Item("Кейн", "Веласкес"));
        for (Item fighter : list) {
            tracker.add(fighter);
        }
        assertThat(tracker.findAll().size(), is(3));
    }

    @Test
    public void checkDelete() throws Exception {
        Connection connection = ConnectionRollback.create(this.init());
        TrackerSQL tracker = new TrackerSQL(connection);
        tracker.createTable();
        List<Item> list = List.of(
                new Item("Забит", "Магомедшарипов"),
                new Item("Макс", "Холлоуэй"),
                new Item("Жозе", "Альдо"));
        for (Item fighter : list) {
            tracker.add(fighter);
        }
        tracker.delete(list.get(0).getId());
        assertThat(tracker.findAll().size(), is(2));
    }

    @Test
    public void checkReplace() throws Exception {
        Connection connection = ConnectionRollback.create(this.init());
        TrackerSQL tracker = new TrackerSQL(connection);
        tracker.createTable();
        Item item = new Item("Волкан", "Оздемир");
        tracker.add(item);
        Item newItem = new Item("Энтони", "Смит");
        tracker.replace(item.getId(), newItem);
        assertThat(tracker.findById(item.getId()).getName(), is(newItem.getName()));
    }
}
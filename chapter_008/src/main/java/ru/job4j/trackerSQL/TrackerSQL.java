package ru.job4j.trackerSQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 30.05.2019
 */

public class TrackerSQL implements ITracker {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);
    private Connection connection;
    private static final Random RANDOM = new Random();

    private String generateId() {
        return String.valueOf(RANDOM.nextInt());
    }

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            try (Statement st = connection.createStatement()) {
                st.executeUpdate("CREATE TABLE IF NOT EXISTS tracker (id serial primary key, name varchar(2000), description varchar(2000))");
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Метод реализует добавление заявки в базу данных
     * @param item новая заявка
     * @return заявка
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO item(name, description) values(?, ?)")) {
            item.setId(this.generateId());
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Метод обновляет заявку
     * @param item существующая заявка
     * @param id идентификатор
     */
    @Override
    public boolean replace(String id, Item item) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE item SET name = ?, desc = ? WHERE = ?")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setString(3, id);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("Заявка с идентификатором %s не существует", id));
    }

    /**
     * Метод удаляет заявку
     * @param id идентификатор заявки
     */
    @Override
    public boolean delete(String id) {
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM item WHERE id = ?")) {
            st.setString(1, id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("Заявка с идентификатором %s не существует", id));
    }

    /**
     * Метод выдает список всех заявок
     * @return список заявок
     */
    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM item")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getString(1));
                item.setName(rs.getString(2));
                item.setDesc(rs.getString(3));
                list.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Метод выдает список по имени
     * @param key вводится имя
     * @return существующая заявка по введенному имени
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM item WHERE name = ?")) {
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Item(rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("Заявка с именем %s не существует", key));
    }

    /**
     * Метод выдает список по имени
     * @param id вводится идентификатор
     * @return существующая заявка по введенному идентификатору
     */
    @Override
    public Item findById(String id) {
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM item WHERE id = ?")) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Item(rs.getString("id"));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("Заявка с идентификатором %s не существует", id));
    }
}

package ru.job4j.trackersql;

import org.slf4j.*;
import ru.job4j.tracker.*;
import java.sql.*;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 10.08.2019
 */

public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);
    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try (Statement st = this.connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS tracker (id serial primary key, name varchar(2000), description varchar(2000))");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод реализует добавление заявки в базу данных
     * @param item новая заявка
     * @return заявка
     */
    @Override
    public Item add(Item item) {
        try (final PreparedStatement ps = this.connection.prepareStatement("INSERT INTO tracker(name, description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getString(1));
                }
            }
        } catch (Exception e) {
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
        try (PreparedStatement st = connection.prepareStatement("UPDATE tracker SET name = ?, description = ? WHERE id = ? ")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setInt(3, Integer.parseInt(id));
            st.executeUpdate();
            return true;
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
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM tracker WHERE id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            st.executeUpdate();
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
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM tracker")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(String.valueOf(rs.getInt("id")));
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
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM tracker WHERE name = ?")) {
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(String.valueOf(rs.getInt("id")));
                list.add(item);
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
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM tracker WHERE id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(String.valueOf(rs.getInt(1)));
                if (id.equals(item.getId())) {
                    return item;
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("Заявка с идентификатором %s не существует", id));
    }

    @Override

    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

package ru.job4j.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 23.02.2020
 */

public class DatabaseStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DatabaseStore INSTANCE = new DatabaseStore();
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseStore.class);

    /**
     * В конструкторе происходит инициализация пула соединений с базой данных
     */

    public DatabaseStore() {
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/Users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    /**
     * Метод дает право создать единственный экзепляр класса для взаимосвязи с логическим (валидационным) блоком проекта
     * @return - экзепляр класса DatabaseStore
     */

    public static DatabaseStore getInstance() {
        return INSTANCE;
    }

    /**
     * Метод добавляет нового пользователя в базу данных
     * @param user - новый пользователь
     * @return - успешность операции
     */

    @Override
    public boolean add(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection(); PreparedStatement ps = connection.prepareStatement("INSERT INTO users(id, name, login, email, date_of_creation) VALUES (?, ?, ?, ?, ?)")) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getCreateDate());
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод обновляет данные существующего пользователя в базе данных
     * @param id - идентификационный номер существующего пользователя
     * @param recent - новые данные
     * @return - успешность операции
     */

    @Override
    public boolean update(int id, User recent) {
        try (Connection connection = SOURCE.getConnection(); PreparedStatement ps = connection.prepareStatement("UPDATE users SET name = ?, login = ?, email = ? WHERE id = ?")) {
            ps.setString(1, recent.getName());
            ps.setString(2, recent.getLogin());
            ps.setString(3, recent.getEmail());
            ps.setInt(4, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("Such id %s of user is not exist", id));
    }

    /**
     * Метод удаляет пользователя из базы данных
     * @param id - идентификационный номер существующего пользователя
     * @return - успешность операции
     */

    @Override
    public boolean delete(int id) {
        try (Connection connection = SOURCE.getConnection(); PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("Such id %s of user is not exist", id));
    }

    /**
     * Метод показывает список существующих пользователей
     * @return - список существующих пользователей
     */

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        CopyOnWriteArrayList<User> list = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"));
                user.setId(rs.getInt("id"));
                user.setCreateDate(rs.getString("date_of_creation"));
                list.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Метод получает данные существующего пользователя по id
     * @param id - идентификатор существующего пользователя
     * @return - данные существующего пользователя
     */

    @Override
    public User findById(int id) {
        try (Connection connection = SOURCE.getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"));
                user.setId(rs.getInt("id"));
                user.setCreateDate(rs.getString("date_of_creation"));
                if (id == user.getId()) {
                    return user;
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException(String.format("Such id %s of user is not exist", id));
    }
}

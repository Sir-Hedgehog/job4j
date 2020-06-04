package ru.job4j.models;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 6.0
 * @since 04.06.2020
 */

public class CinemaDatabase implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(CinemaDatabase.class);
    private final BasicDataSource source = new BasicDataSource();

    private static final class Lazy {
        private static final Store INSTANCE = new CinemaDatabase();
    }

    /**
     * Метод дает право создать единственный экземпляр класса для взаимосвязи с логическим (валидационным) блоком проекта
     * @return - экземпляр класса CinemaDatabase
     */

    public static Store instanceOf() {
        return Lazy.INSTANCE;
    }

    /**
     * В конструкторе происходит инициализация пула соединений с базой данных
     */

    public CinemaDatabase() {
        Properties configuration = new Properties();
        try (InputStream is = CinemaDatabase.class.getClassLoader().getResourceAsStream("db.properties")) {
            configuration.load(is);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(configuration.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        source.setUrl(configuration.getProperty("jdbc.url"));
        source.setUsername(configuration.getProperty("jdbc.username"));
        source.setPassword(configuration.getProperty("jdbc.password"));
        source.setDriverClassName(configuration.getProperty("jdbc.driver"));
        source.setMinIdle(5);
        source.setMaxIdle(10);
        source.setMaxOpenPreparedStatements(100);
    }

    /**
     * Метод сохраняет выбранное место в кинозале
     * @param hall - кинозал
     * @return - успешность операции
     */

    @Override
    public boolean takePlace(Hall hall) throws SQLException {
        boolean result = false;
        source.getConnection().setAutoCommit(false);
        try (PreparedStatement ps = source.getConnection().prepareStatement("INSERT INTO hall(row, place) VALUES (?, ?)")) {
            ps.setInt(1, hall.getRow());
            ps.setInt(2, hall.getPlace());
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод сохраняет аккаунт пользователя
     * @param account - аккаунт пользователя
     * @return - успешность операции
     */

    @Override
    public boolean addAccount(Account account) throws SQLException {
        boolean result = false;
        try (PreparedStatement ps = source.getConnection().prepareStatement("INSERT INTO accounts(name, phone) VALUES (?, ?)")) {
            ps.setString(1, account.getName());
            ps.setString(2, account.getPhone());
            ps.executeUpdate();
            source.getConnection().commit();
            result = true;
            source.getConnection().close();
        } catch (SQLException e) {
            source.getConnection().rollback();
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод показывает список существующих пользователей
     * @return - список существующих пользователей
     */

    @Override
    public CopyOnWriteArrayList<Hall> findTakenPlaces() {
        CopyOnWriteArrayList<Hall> list = new CopyOnWriteArrayList<>();
        try (PreparedStatement ps = source.getConnection().prepareStatement("SELECT * FROM hall")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hall hall = new Hall(rs.getInt("row"), rs.getInt("place"));
                list.add(hall);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }
}

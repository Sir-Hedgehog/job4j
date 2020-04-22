package ru.job4j.models;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 22.04.2020
 */

public class CinemaDatabase implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final CinemaDatabase INSTANCE = new CinemaDatabase();
    private static final Logger LOG = LoggerFactory.getLogger(CinemaDatabase.class);
    private final Connection connection;

    /**
     * В конструкторе происходит инициализация пула соединений с базой данных
     */

    public CinemaDatabase() {
        SOURCE.setUrl("jdbc:sqlserver://localhost:5454;databaseName=Cinema;integratedSecurity=true;");
        SOURCE.setUsername("sa");
        SOURCE.setPassword("password555");
        SOURCE.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try {
            connection = SOURCE.getConnection();
        } catch(SQLException ex) {
            throw new RuntimeException();
        }
    }

    /**
     * Метод дает право создать единственный экзепляр класса для взаимосвязи с логическим (валидационным) блоком проекта
     * @return - экземпляр класса CinemaDatabase
     */

    public static CinemaDatabase getInstance() {
        return INSTANCE;
    }

    /**
     * Метод сохраняет выбранное место в кинозале
     * @param hall - кинозал
     * @return - успешность операции
     */

    @Override
    public boolean takePlace(Hall hall) {
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO hall(row, place) VALUES (?, ?)");
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
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO accounts(name, phone) VALUES (?, ?)")) {
            ps.setString(1, account.getName());
            ps.setString(2, account.getPhone());
            ps.executeUpdate();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            connection.rollback();
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
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM hall")) {
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

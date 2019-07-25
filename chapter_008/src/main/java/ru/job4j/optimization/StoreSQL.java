package ru.job4j.optimization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.07.2019
 */

public class StoreSQL implements AutoCloseable {
    private final Config config;
    //private ConnectionSQLLite sql = new ConnectionSQLLite();
    private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
    private Connection connection;

    public StoreSQL(Config config) {
        this.config = config;
        create();
    }

    private void create() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(config.get("url"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        String table = "CREATE TABLE IF NOT EXISTS Entry (number integer)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(table);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void dropTable() {
        String table = "DROP TABLE IF EXISTS Entry";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(table);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void generate(int size) {
        dropTable();
        createTable();
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Entry(number) VALUES(?)")) {
                for (int index = 0; index < size; index++) {
                    ps.setInt(1, index + 1);
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<Entry> load() {
        List<Entry> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String str = "SELECT number FROM Entry";
            ResultSet rs = statement.executeQuery(str);
            while (rs.next()) {
                list.add(new Entry(rs.getInt("number")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

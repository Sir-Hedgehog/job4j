package ru.job4j.optimization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.07.2019
 */

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private ConnectionSQLLite sql = new ConnectionSQLLite();
    private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
    private static final Random RANDOM = new Random();
    private Connection connection = null;

    private int generateId() {
        return RANDOM.nextInt();
    }

    public StoreSQL(Config config) {
        this.config = config;
    }

    private void createTable() {
        String table = "CREATE TABLE IF NOT EXISTS Entry (field integer)";
        try {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.executeQuery(table);
                connection.commit();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTable() {
        String table = "DROP TABLE IF EXISTS Entry";
        try {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                statement.executeQuery(table);
                connection.commit();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generate(int size) {
        createTable();
        dropTable();
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = sql.getConnection().prepareStatement("INSERT INTO Entry(field) VALUES(?)")) {
                for (int index = 0; index < size; index++) {
                    ps.setInt(index, this.generateId());
                }
                connection.commit();
            }
            catch(SQLException e) {
                LOG.error(e.getMessage(), e);
                connection.rollback();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entry> load() {
        List<Entry> list = new ArrayList<>();
        try (Statement statement = sql.getConnection().createStatement()) {
            String s = "SELECT field FROM Entry";
            connection.setAutoCommit(false);
            try {
                ResultSet rs = statement.executeQuery(s);
                int index = 1;
                while (rs.next()) {
                    list.add(new Entry(rs.getInt(index)));
                    ++index;
                }
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (sql.getConnection() != null) {
            sql.getConnection().close();
        }
    }
}

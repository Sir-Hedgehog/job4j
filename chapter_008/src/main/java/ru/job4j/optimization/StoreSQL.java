package ru.job4j.optimization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;
    private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
    private static final Random RANDOM = new Random();

    private int generateId() {
        return RANDOM.nextInt();
    }

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void generate(int size) {
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS store (field integer)");
            for (int index = 0; index < size; index++) {
                try (PreparedStatement ps = connect.prepareStatement("INSERT INTO store (field) values(?)")) {
                    ps.setInt(index, this.generateId());
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<Integer> load() {
        List<Integer> list = new ArrayList<>();
        try (Statement statement = connect.createStatement()) {
            String s = "SELECT * FROM store";
            try {
                ResultSet rs = statement.executeQuery(s);
                while (rs.next()) {
                    int index = 1;
                    list.add(rs.getInt(index));
                    ++index;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}

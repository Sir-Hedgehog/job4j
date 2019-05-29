package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 27.05.2019
 */

import java.sql.*;

public class SQLExample {
    private static final Logger LOG = LoggerFactory.getLogger(SQLExample.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/Query";
        String userName = "postgres";
        String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            //PreparedStatement st = conn.prepareStatement("insert into type(name) values(?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement st = conn.prepareStatement("delete from type where id = ?");
            st.setInt(1, 6);
            st.executeUpdate();
            /*ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
            }
            PreparedStatement st = conn.prepareStatement("select * from car as c where c.id in (?, ?)");
            st.setInt (1, 1);
            st.setInt (2, 3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%s", rs.getString("name")));
            }
            rs.close();
            st.close();*/
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}

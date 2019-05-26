package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import jdk.internal.net.http.common.Log;

import java.sql.*;

public class SQLExample {
    private static final Logger log = LoggerFactory.getLogger(SQLExample.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/Query";
        String userName = "postgres";
        String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement st = conn.prepareStatement("insert into type(name) values(?)");
            st.setString (1, "ПЕЧЕНЬЕ");
            st.executeUpdate();
            /*PreparedStatement st = conn.prepareStatement("select * from car as c where c.id in (?, ?)");
            st.setInt (1, 1);
            st.setInt (2, 3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%s", rs.getString("name")));
            }
            rs.close();
            st.close();*/
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}

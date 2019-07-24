package ru.job4j.optimization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.07.2019
 */

public class ConnectionSQLLite {
    private Connection connection;

   /* public Connection getConnection() {
        getConnect();
        return connection;
    }

    private void getConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:magnit.db");
            if (connection != null) {
                System.out.println("Соединение установлено");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
}

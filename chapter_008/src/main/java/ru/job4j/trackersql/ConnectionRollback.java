package ru.job4j.trackersql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection, which rollback all commits.
 * It is used for integration test.
 */

public class ConnectionRollback {

    /**
     * Create connection with autocommit=false mode and rollback call, when connection is closed.
     * @param connection connection.
     * @return Connection object.
     * @throws SQLException possible exception.
     */
    public static Connection create(Connection connection) throws Exception {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollback.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    Object rsl = null;
                    if ("close".equals(method.getName())) {
                        connection.rollback();
                        connection.close();
                    } else {
                        try {
                            rsl = method.invoke(connection, args);
                        } catch (InvocationTargetException ite) {
                            throw ite.getCause();
                        }
                    }
                    return rsl;
                }
        );
    }
}

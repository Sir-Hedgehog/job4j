package ru.job4j.vacancies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 18.08.2019
 */

public class ManagerSQL implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(ManagerSQL.class);
    private Connection connection;

    public ManagerSQL(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод создает таблицу в БД с необходимыми ячейками
     */
    private void createTable() {
        String table = "CREATE TABLE IF NOT EXISTS vacancy (id serial primary key, name varchar(2000), text varchar(50000), link varchar(2000))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(table);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод удаляет таблицу в БД с целью обновления
     */
    private void dropTable() {
        String table = "DROP TABLE IF EXISTS vacancy";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(table);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод вносит необходимые изменения в существующую таблицу БД
     * @param list - список вакансий
     * @return - выводит первую вакансию текущего года для тестирования данного метода
     */
    public String edit(List<ParserOfVacancies.Template> list) {
        dropTable();
        createTable();
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO vacancy(name, text, link) VALUES(?, ?, ?)")) {
                for (ParserOfVacancies.Template template : list) {
                    ps.setString(1, template.getName());
                    ps.setString(2, template.getText());
                    ps.setString(3, template.getLink());
                    ps.executeUpdate();
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            template.setId(generatedKeys.getString(1));
                        }
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list.get(list.size() - 1).getName();
    }

    /**
     * Метод реалирует функционал интерфейса AutoCloseable по закрытию connection
     */
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}

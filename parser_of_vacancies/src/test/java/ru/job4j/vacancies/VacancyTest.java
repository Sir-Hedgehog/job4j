package ru.job4j.vacancies;

import org.junit.Test;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 18.08.2019
 */

public class VacancyTest {
    private Connection init() {
        try (InputStream in = ManagerSQL.class.getClassLoader().getResourceAsStream("vacancy.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    @Test
    public void whenTestStoreSQLThenOK() throws Exception {
        ParserOfVacancies parser = new ParserOfVacancies();
        List<ParserOfVacancies.Template> list = parser.saveVacancies();
        Connection connection = ConnectionRollback.create(this.init());
        ManagerSQL manager = new ManagerSQL(connection);
        String template = manager.edit(list);
        assertThat(template, is("Senior Java Ee Developer"));
    }
}

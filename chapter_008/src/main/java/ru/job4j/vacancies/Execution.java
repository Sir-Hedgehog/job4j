package ru.job4j.vacancies;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.trackersql.ConnectionRollback;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

public class Execution implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(ParserOfVacancies.class);
    private ParserOfVacancies parser = new ParserOfVacancies();

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

    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            List<ParserOfVacancies.Template> list = this.parser.saveVacancies();
            Connection connection = ConnectionRollback.create(this.init());
            ManagerSQL manager = new ManagerSQL(connection);
            manager.edit(list);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

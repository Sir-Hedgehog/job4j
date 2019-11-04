package ru.job4j.vacancies;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.10.2019
 */

public class Execution implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(ParserOfVacancies.class);
    private ParserOfVacancies parser = new ParserOfVacancies();

    /**
     * Метод создает подключение к базе данных
     * @param properties - настройки подключения
     * @return - подключение
     */
    private Connection init(Properties properties) {
         try {
            Class.forName(properties.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * При получении подключения к БД парсит сайт и выдает результат парсинга
     * @param jobExecutionContext - контекст, необходимый для создания ограничения
     */
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            LOG.info("START: " + new Date());
            JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            Properties properties = (Properties) dataMap.get("properties");
            List<ParserOfVacancies.Template> list = this.parser.saveVacancies();
            Connection connection = ConnectionRollback.create(this.init(properties));
            ManagerSQL manager = new ManagerSQL(connection);
            manager.edit(list);
            LOG.info("END: " + new Date());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

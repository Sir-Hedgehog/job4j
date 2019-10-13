package ru.job4j.vacancies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 12.10.2019
 */

public class SqlRuParser {
    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());
    private static final boolean WAY = true;

    /**
     * Метод запускает работу парсера в зависимости от ситуации:
     * - при запуске приложения используем файл по умолчанию
     * - при запуске приложения передаем свой файл с параметрами конфигурации
     */
    public static void main(String[] args) {
        Properties properties = new Properties();
        SqlRuParser sqlRuParser = new SqlRuParser();
        if (WAY) {
            try {
                properties = sqlRuParser.loadConfig();
            } catch (IOException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        } else {
            try (FileInputStream fis = new FileInputStream(args[0])) {
                properties.load(fis);
            }  catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
        CronTrigger cronTrigger = new CronTrigger(properties, Execution.class);
        try {
            cronTrigger.startTimer();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод загружает файл конфигурации по умолчанию
     */
    public Properties loadConfig() throws IOException {
        Properties config;
        try (InputStream in = SqlRuParser.class.getClassLoader().getResourceAsStream("vacancy.properties");
             InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            config = new Properties();
            config.load(isr);
        }
        return config;
    }
}

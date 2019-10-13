package ru.job4j.vacancies;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Properties;
import static org.quartz.JobBuilder.newJob;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.10.2019
 */

public class CronTrigger {
    private Properties properties;
    private Class<? extends Job> execution;

    public CronTrigger(Properties properties, Class<? extends Job> execution) {
        this.properties = properties;
        this.execution = execution;
    }

    /**
     * С учетом настроек файла конфигурации метод создает таймер, по которому будет работать парсер
     */
    public void startTimer() throws SchedulerException {
        JobDetail jobDetail = newJob(execution).withIdentity("jobDetail").build();
        jobDetail.getJobDataMap().put("properties", properties);
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("CronTrigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(properties.getProperty("cron.time")))
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}

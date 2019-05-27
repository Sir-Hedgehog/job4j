package ru.job4j.example2;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 27.05.2019
 */

public class UsageLog4j2New {

    private static final Logger LOG = LogManager.getLogger(ru.job4j.example2.UsageLog4j2New.class.getName());

    public static void main(String[] args) {
        int version = 1;
        LOG.trace("trace message {}", version);
        LOG.debug("debug message {}", version);
        LOG.info("info message {}", version);
        LOG.warn("warn message {}", version);
        LOG.error("error message {}", version);
    }
}

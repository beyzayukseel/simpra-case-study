package com.beyzanur.simpracasestudy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtil {

    private final Logger loggerUtil = LogManager.getLogger(LoggerUtil.class);

    public void logInformation(String message) {
        loggerUtil.info(message);
    }

    public void logWarn(String message) {
        loggerUtil.warn(message);
    }

    public void logError(String message,Throwable exception, String extras) {
            loggerUtil.error(message, exception, extras);
    }

}

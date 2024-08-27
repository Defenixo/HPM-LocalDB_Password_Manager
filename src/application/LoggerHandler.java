package application;

import java.lang.System.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class LoggerHandler extends Controller {

	public static void Log(String message, char level) {
		if(advancedlogging) {
			if(level == 'I') {
				logger.info(message);
			} else if(level == 'E') {
				logger.error(message);
			} else if(level == 'F') {
				logger.fatal(message);
			}
		}
	}
	
	public static void ConfigureLogger() {
		
	}
}

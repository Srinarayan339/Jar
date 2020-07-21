package com.sri.jartest;
import java.io.File;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class JarStatusCheck {
	//static Logger logger = Logger.getLogger(JarStatusCheck.class);
	private static LoggerUtil logger = LoggerUtil.getInstance();
	
	public static void main(String[] args) {
		String log4jConfigFile = System.getProperty("user.dir")
				+ File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		logger.info("Started Main Method.!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Timer timer = new Timer();
		timer.schedule(new StatusCheck(), 0, 60*10*1000);//10 Min
		logger.info("Ending Main Method.!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

}
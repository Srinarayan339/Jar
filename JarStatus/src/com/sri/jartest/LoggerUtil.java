package com.sri.jartest;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtil
{

	private static LoggerUtil instance = null;
	private static Logger logger =
			Logger.getLogger(
				LoggerUtil.class.getPackage()
					.getName()
			);
	public static void error(Exception e)
	{
		logger.error("Exception : ", e);
	}

	
	public static void error(String msg)
	{
		
		logger.error(msg.toString());
	}

	private static String getClassName(String classWithPackageName)
	{
		String[] str = classWithPackageName.split("\\.");
		return str[str.length - 1];
	}

	public static LoggerUtil getInstance()
	{
		if (instance == null)
		{
			instance = new LoggerUtil();
		}
		return instance;
	}

	public static void info(String message)
	{
		
		logger.info(message);
	}

	private AsyncAppender asyncAppender = null;

	private LoggerUtil()
	{
		try
		{
			
			FileReader reader=new FileReader("D:\\TEST\\Practice_Workspace\\JarStatus\\src\\prop1.properties");  
	  	      
    	    Properties p=new Properties();  
    	    p.load(reader); 
			
			
			
    	    PropertyConfigurator.configure(p.getProperty("logger"));
			logger.setAdditivity(false);
			asyncAppender =
					(AsyncAppender) Logger.getRootLogger()
						.getAppender("ASYNC");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}

	
}

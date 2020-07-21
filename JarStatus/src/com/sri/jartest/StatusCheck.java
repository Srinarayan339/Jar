package com.sri.jartest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.TimerTask;

import org.apache.log4j.PropertyConfigurator;

import com.sun.istack.internal.logging.Logger;

public class StatusCheck extends TimerTask {

	private static LoggerUtil logger = LoggerUtil.getInstance();
	
	//public static  Logger logger = Logger.getLogger(Hello.class);
	SendEmail smUtil = new SendEmail();
	
    //static Logger logger = Logger.getLogger(Hello.class);
	public static long[] fileSizes = {0L, 0L, 0L};
	
	public void run() {

		logger.info("Started run Method.!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

	 Process proc = null;
		try {
			proc = Runtime.getRuntime().exec("wmic process where \"name like '%java%'\" get commandline,processid");
		  //proc = Runtime.getRuntime().exec("ps -ef|grep java"); //for Linux
			InputStream in = proc.getInputStream();
			BufferedReader inputFile = new BufferedReader(new InputStreamReader(in));
			StringBuilder allInstance = new StringBuilder();
			
			String currInputLine = null;
	        try {
				while((currInputLine = inputFile.readLine()) != null) {
				  allInstance.append(currInputLine);
				  allInstance.append("|");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        logger.info("Properties Loaded .!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        FileReader reader=new FileReader("D:\\TEST\\Practice_Workspace\\JarStatus\\src\\prop1.properties");  
	  	      
    	    Properties p=new Properties();  
    	    p.load(reader); 
    	    String s = p.getProperty("jars");
    	    String m = p.getProperty("logs");
    	    
    	    
    	    String [] jarStatus = s.split(",");
    	    String [] logPath = m.split(",");
    	    
    	    
    	    
    	    
    	
    	    /*String[] jarStatus = {"newjar", "newjarb", "newjara"};
    	    String[] logPath = {"newjarbLog", "newjaraLog", "newjarLog"};*/
    	    int i=0;
    	    while(i<jarStatus.length)
    	    {
		        if(allInstance.toString().contains(jarStatus[i].trim())) {
		        	logger.info("jar is Up!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +jarStatus[i] );
		        	System.out.print(jarStatus[i]);
		        	System.out.println(" is running");
		        	
		        	Path filePath  = Paths.get(logPath[i]);
		        	long currFileSize =  Files.size(filePath);
		        	System.out.println("curr File Size : " + currFileSize);
		        	System.out.println("File Size  : " + fileSizes[i]);
		        	if(currFileSize ==  fileSizes[i]) {
		        		System.out.println("Log file writing is stopped ");
		        		smUtil.sendMail();
		        	}
		    		 fileSizes[i] =currFileSize;
		        }
		        else {
		        	logger.info("jar is down!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +jarStatus[i] );
		        	System.out.print(jarStatus[i]);
		        	System.out.println(" is down!!!!!!!!!!!!!\n");
		        	smUtil.sendMail();
		        	if(i==jarStatus.length-1) {
			        	System.exit(0);
			        	}
		        }
		        i++;
    	    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	 } 


}

package listeners;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import utils.TimeUtil;


public class ReportLogger {
	
	private static final Logger logger = LogManager.getLogger(ReportLogger.class);
	
    public static void stepPass(String message) {
    	try {
    		TestListener.getTest().pass(message + " | " + TimeUtil.getCurrentDateTime());
    		logger.info(message +" | "+ TimeUtil.getCurrentDateTime());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }

    public static void stepFail(String message) {
    	try {
    		TestListener.getTest().fail(message + " | " + TimeUtil.getCurrentDateTime());
    		logger.info(message +" | "+ TimeUtil.getCurrentDateTime());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }

    public static void stepInfo(String message) {
    	try {
    		TestListener.getTest().info(message + " | " + TimeUtil.getCurrentDateTime());
    		logger.info(message +" | "+ TimeUtil.getCurrentDateTime());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
}

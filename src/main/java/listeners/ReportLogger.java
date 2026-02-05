package listeners;

import listeners.TestListener;
import utils.TimeUtil;

public class ReportLogger {

    public static void stepPass(String message) {
    	try {
    		TestListener.getTest().pass(message + " | " + TimeUtil.getCurrentDateTime());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }

    public static void stepFail(String message) {
    	try {
    		TestListener.getTest().fail(message + " | " + TimeUtil.getCurrentDateTime());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }

    public static void stepInfo(String message) {
    	try {
    		TestListener.getTest().info(message + " | " + TimeUtil.getCurrentDateTime());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
}

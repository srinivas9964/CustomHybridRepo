package listeners;

import listeners.TestListener;
import utils.TimeUtil;

public class ReportLogger {

    public static void stepPass(String message) {
    	TestListener.getTest().pass(message + " | " + TimeUtil.getCurrentTime());
    }

    public static void stepFail(String message) {
    	TestListener.getTest().fail(message + " | " + TimeUtil.getCurrentTime());
    }

    public static void stepInfo(String message) {
    	TestListener.getTest().info(message + " | " + TimeUtil.getCurrentTime());
    }
}

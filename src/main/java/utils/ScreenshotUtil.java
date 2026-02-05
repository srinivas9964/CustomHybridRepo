package utils;

import org.openqa.selenium.*;

import com.aventstack.extentreports.MediaEntityBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import core.DriverFactory;
import listeners.ReportLogger;
import listeners.TestListener;

public class ScreenshotUtil {

    public static String capture(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);

            //String path = "reports/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            String screenshotDir = System.getProperty("user.dir")+ "/reports/screenshots";
            File dir = new File(screenshotDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = testName + "_" + System.currentTimeMillis() + ".png";
            File dest = new File(dir, fileName);
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return "screenshots/" + fileName;

        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }
    
    public static String getBase64Screenshot(WebDriver driver) {
        try {
        	return ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BASE64);
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        	return null;
        }
    }
    
    public static boolean getScreenShot() {
    	boolean bStatus=false;
    	try {
    		String screenshot = ScreenshotUtil.getBase64Screenshot(DriverFactory.getDriver());
    		TestListener.getTest().pass("",MediaEntityBuilder
          			 .createScreenCaptureFromBase64String(screenshot, "Screenshot").build());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    		ReportLogger.stepFail("Failed to Append the Screenshot");
    	}
    	return bStatus;
    }
}

package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import core.DriverFactory;

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
}

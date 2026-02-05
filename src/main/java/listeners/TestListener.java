package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import core.DriverFactory;
import utils.SuiteTimeTracker;

import java.util.HashMap;
import java.util.Map;
import org.testng.*;
import com.aventstack.extentreports.*;
import reports.ExtentManager;
import retryAnalyzer.FailRetryAnalyzer;
import utils.ConfigReader;
import utils.ScreenshotUtil;
import utils.TimeUtil;

public class TestListener implements ITestListener {
	
	private static ExtentReports extent = ExtentManager.getExtent();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    private Map<String, Long> startTimeMap = new HashMap<>();
      
    
    @Override
    public void onStart(ITestContext context) {
        try {
        	SuiteTimeTracker.startSuite();
            String suiteName = context.getSuite().getName();
            ExtentManager.getExtent().setSystemInfo("Suite Name", suiteName);  
            ExtentManager.getExtent().setSystemInfo("Browser", ConfigReader.get("browser"));  
            ExtentManager.getExtent().setSystemInfo("Execution Type ChromeHeadless : ", ConfigReader.get("chromeHeadless")); 
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }
    
    
    @Override
    public void onTestStart(ITestResult result) {
    	try {
    		String suiteName = result.getTestContext().getSuite().getName();
       	 ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//           ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
           test.set(extentTest);
           startTimeMap.put(result.getName(), System.currentTimeMillis());
           extentTest.assignCategory(suiteName);
//           extentTest.assignCategory(result.getTestClass().getName());
           extentTest.info("Test Started at: " + TimeUtil.getCurrentDateTime());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
       try {
    	   test.get().pass("Test Passed");
           logExecutionTime(result);
       }
       catch(Exception e) {
    	   System.out.println(e.getMessage());
       }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
    	try {
    		//Appending Screenshots to the Report using Base64 to avoid screenshot dependency while report sharing
       	 
       	 String base64Screenshot = ScreenshotUtil.getBase64Screenshot(DriverFactory.getDriver());
       	 test.get().fail("Test Failed",MediaEntityBuilder
       			 .createScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot").build()); 
       	 logExecutionTime(result);
//      	 Appending Screenshots to the Report in Local Machine Only ( While Sharing to Someone Screenshots won't be available )
//           String screenshotPath = ScreenshotUtil.capture(result.getName());
//           test.get().fail("Test Failed",
//                   MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//           	logExecutionTime(result);
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
       try {
    	   test.get().skip("Test Skipped");
       }
       catch(Exception e) {
    	   System.out.println(e.getMessage());
       }
    }
    
    @Override
    public void onFinish(ITestContext context) {
    	try {
    		SuiteTimeTracker.endSuite();
        	ExtentManager.getExtent().setSystemInfo("Total Suite Execution Time:",SuiteTimeTracker.getSuiteExecutionTime());
            extent.flush();
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    
    private void logExecutionTime(ITestResult result) {
       try {
    	   long start = startTimeMap.get(result.getName());
           long end = System.currentTimeMillis();
           long duration = (end - start) / 1000;
           test.get().info("End Time: " + TimeUtil.getCurrentDateTime());
           test.get().info("Execution Time: " + duration + " seconds");
       }
       catch(Exception e) {
    	   System.out.println(e.getMessage());
       }
    }
    
    public static ExtentTest getTest() {
        try {
        	 return test.get();
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        	return null;
        }
    }
    
}

package core;
import manager.PageObjectManager;
import utils.ConfigReader;
import listeners.TestListener;

import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import java.lang.reflect.Method;



public class Base {
	
	 protected PageObjectManager pages;
	 protected Logger logger;
	 
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) {
		try {
			String browser=null;
			if (browser == null || browser.isEmpty()) browser = ConfigReader.get("browser");
			DriverFactory.initDriver(browser);
			pages = new PageObjectManager();
			
			 // Create a unique test log name per invocation
	        String testName = method.getName();

			// Set ThreadContext variable for this test
	        ThreadContext.put("testName", method.getName());
	        
			// Initialize logger for each test method
	        logger = LogManager.getLogger(method.getDeclaringClass());
	        logger.info("=== Test Started: " + method.getName() + " ===");
	        
			//DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@AfterMethod
	public void tearDown(Method method) {
		try {
			logger.info("=== Test Finished: " + method.getName() + " ===");
			 // Clear ThreadContext
	        ThreadContext.clearAll();
			DriverFactory.quitDriver();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	}	
	}



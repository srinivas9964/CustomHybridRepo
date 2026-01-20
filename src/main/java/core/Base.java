package core;
import manager.PageObjectManager;
import utils.ConfigReader;
import listeners.TestListener;

import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class Base {
	
	 protected PageObjectManager pages;
	 
	@BeforeMethod
	public void setUp() {
		try {
			String browser=null;
			if (browser == null || browser.isEmpty()) browser = ConfigReader.get("browser");
			DriverFactory.initDriver(browser);
			pages = new PageObjectManager();
			//DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));
			//ExtentManager.initReports();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
	}

	@AfterMethod
	public void tearDown() {
	DriverFactory.quitDriver();
	//ExtentManager.flushReports();
	}	
	}



package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

public static WebDriver getDriver() {
return driver.get();
}


public static void initDriver(String browser) {
		try {
			if (driver.get() == null) {
				if (browser == null || browser.equalsIgnoreCase("chrome")) 
				{
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					if(ConfigReader.get("chromeHeadless").equalsIgnoreCase("true")) {
						options.addArguments("--headless");
					}
					options.addArguments("--start-maximized");
					driver.set(new ChromeDriver(options));
					WebDriver webDriver = driver.get();
		            // Defining the wait to load the broswer
		            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		            // Optional Implicit wait
		            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					
				} 
				else {
				// add firefox/edge later
				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver());
				}
				}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}



public static void quitDriver() {
	try {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
			}
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		}
	}
}
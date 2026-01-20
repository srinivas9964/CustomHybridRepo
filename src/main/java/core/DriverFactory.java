package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

public static WebDriver getDriver() {
return driver.get();
}


public static void initDriver(String browser) {
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
} 
else {
// add firefox/edge later
WebDriverManager.chromedriver().setup();
driver.set(new ChromeDriver());
}
}
}



public static void quitDriver() {
if (driver.get() != null) {
driver.get().quit();
driver.remove();
}
}
}
package pages;
import core.DriverFactory;
import listeners.ReportLogger;
import utils.WaitUtils;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
	 private WebDriver driver;
	 private WaitUtils wait;
	 
	private By username = By.xpath("//input[@name='username']");
	private By password = By.xpath("//input[@name='password']");
	private By loginBtn = By.xpath("//button[@type='submit']");
	private By dashborad = By.xpath("//h6[text()='Dashboard']");


	public LoginPage() {
		this.driver = DriverFactory.getDriver();
		this.wait = new WaitUtils(driver);
	}


	public void enterUsername(String user) {
	driver.findElement(username).clear();
	driver.findElement(username).sendKeys(user);
	}


	public void enterPassword(String pass) {
	driver.findElement(password).clear();
	driver.findElement(password).sendKeys(pass);
	}


	public void clickLogin() {
	driver.findElement(loginBtn).click();
	}
	
	 public void login(String user, String pass) {
	        wait.waitForVisibility(username).sendKeys(user);
	        ReportLogger.stepPass("Enter the Username");
	        wait.waitForVisibility(password).sendKeys(pass);
	        ReportLogger.stepPass("Enter the Password");
	        wait.waitForClickable(loginBtn).click();
	        ReportLogger.stepPass("Click on Submit");
	        Assert.assertTrue( wait.waitForText(dashborad,"Dashboard"));
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("dashboard")));
	        ReportLogger.stepPass("Application launched successfully");
	 }

	public boolean isLoggedIn() {
	// confirmation logic
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.urlContains("dashboard"));
	}
}

package pages;
import core.DriverFactory;
import listeners.ReportLogger;
import utils.ScreenshotUtil;
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
	
	private By barndLogo = By.xpath("//img[@alt='company-branding']");
	private By username = By.xpath("//input[@name='username']");
	private By password = By.xpath("//input[@name='password']");
	private By loginBtn = By.xpath("//button[@type='submit']");
	private By dashborad = By.xpath("//h6[text()='Dashboard']");



	public LoginPage() {
		this.driver = DriverFactory.getDriver();
		this.wait = new WaitUtils(driver);
	}


	public void enterUsername(String user) {
		try {
			driver.findElement(username).clear();
			driver.findElement(username).sendKeys(user);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void enterPassword(String pass) {
			driver.findElement(password).clear();
			driver.findElement(password).sendKeys(pass);
		}

	public void clickLogin() {
			driver.findElement(loginBtn).click();
	}
	
	 public void verifylogin(String user, String pass) {
		 		
	      		wait.waitForVisibility(barndLogo).isDisplayed();
	      		ReportLogger.stepPass("Login Page Loaded");
	    	    wait.waitForVisibility(username).sendKeys(user);
		        ReportLogger.stepPass("Enter the Username");
		        wait.waitForVisibility(password).sendKeys(pass);
		        ReportLogger.stepPass("Enter the Password");
		        wait.waitForClickable(loginBtn).click();
		        ReportLogger.stepPass("Click on Submit");
		        Assert.assertTrue( wait.waitForUrlContains("dashboard"));
		        Assert.assertTrue( wait.waitForVisibility(dashborad).isDisplayed());
		        ReportLogger.stepPass("Application launched successfully");
		        ScreenshotUtil.getScreenShot();
	 }

	public boolean isLoggedIn() {
		try {
			// confirmation logic
			return wait.waitForVisibility(dashborad).isDisplayed();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}

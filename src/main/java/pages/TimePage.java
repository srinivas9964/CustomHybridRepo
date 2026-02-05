package pages;
import core.DriverFactory;
import listeners.ReportLogger;
import utils.WaitUtils;
import utils.GeneralActions;
import utils.ScreenshotUtil;
import utils.TimeUtil;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TimePage {
	private WebDriver driver;
	private WaitUtils wait;
	private GeneralActions actions;
	
	private By loadSnipper = By.xpath("(//div[@class='oxd-loading-spinner'])[last()]");
	private By attendanceHeader = By.xpath("//span[text()='Attendance ']");
	private By puncInPunchoutOption = By.xpath("//a[text()='Punch In/Out']");
	private By puncInHeader = By.xpath("//h6[text()='Punch In']");
	private By date = By.xpath("//label[text()='Date']/following::input[@placeholder='yyyy-dd-mm']");
	private By time = By.xpath("//label[text()='Time']/following::input[@placeholder='hh:mm']");
	private By note = By.xpath("//textarea[contains(@class,'oxd-textarea')]");
	private By inSubmitButton = By.xpath("//button[text()=' In ']");
	private By punchOutHeader = By.xpath("//h6[text()='Punch Out']");
	private By outSubmitButton = By.xpath("//button[text()=' Out ']");

	
	public TimePage() {
		try {
			this.driver = DriverFactory.getDriver();
			this.wait = new WaitUtils(driver);
			this.actions = new GeneralActions();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
public void navigateToPuncInSection() {
		wait.waitForClickable(attendanceHeader).click();
		ReportLogger.stepPass("Click on Attendance");
		wait.waitForClickable(puncInPunchoutOption).click();
		ReportLogger.stepPass("Click on PunchIn/PunchOut Option");
		wait.waitForVisibility(puncInHeader).isDisplayed();	
		ReportLogger.stepPass("Punch In Section Exist");
	}

	public void punchIn() {
		wait.waitForNotVisibility(loadSnipper);
		wait.waitForVisibility(date).isDisplayed();
		String currentDate =TimeUtil.getCurrentDateWithFormate("yyyy-dd-MM");
		var dateInput = wait.waitForClickable(date);
		dateInput.click();
		dateInput.sendKeys(Keys.CONTROL + "a");
	    dateInput.sendKeys(Keys.DELETE);
	    dateInput.sendKeys(currentDate);
//		wait.waitForClickable(date).click();
//		wait.waitForVisibility(date).clear();
//		wait.waitForVisibility(date).sendKeys("2026-02-04");
		ReportLogger.stepPass("Enter the Punch In Date");
		 var timeInput = wait.waitForClickable(time);
		 String currentTime = TimeUtil.getCurrentTimeWithFormate("hh:mm a");
		 timeInput.click();
		 timeInput.sendKeys(Keys.CONTROL + "a");
		 timeInput.sendKeys(Keys.DELETE);
		 timeInput.sendKeys(currentTime);
//		wait.waitForVisibility(time).clear();
//		wait.waitForVisibility(time).sendKeys("09:00 AM");
		ReportLogger.stepPass("Enter the Punch In Time");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
		  "arguments[0].value='Logged In'; arguments[0].dispatchEvent(new Event('input'));",
		  wait.waitForVisibility(note)
		);
//		wait.waitForClickable(note).click();
//		wait.waitForVisibility(note).sendKeys("Logged In");
		ReportLogger.stepPass("Enter the Note");
		wait.waitForClickable(inSubmitButton).click();
		ReportLogger.stepPass("Click Punch In");
		wait.waitForNotVisibility(loadSnipper);	
		ScreenshotUtil.getScreenShot();
	}
	
	public void punchOut() {
		var dateInput = wait.waitForClickable(date);
		String currentDate =TimeUtil.getCurrentDateWithFormate("yyyy-dd-MM");
		dateInput.click();
		dateInput.sendKeys(Keys.CONTROL + "a");
	    dateInput.sendKeys(Keys.DELETE);
	    dateInput.sendKeys(currentDate);
//		wait.waitForClickable(date).click();
//		wait.waitForVisibility(date).clear();
//		wait.waitForVisibility(date).sendKeys("2026-02-05");
		ReportLogger.stepPass("Enter the Punch out Date");
		
		 var timeInput = wait.waitForClickable(time);
		 String currentTime = TimeUtil.getCurrentTimeWithFormate("hh:mm a");
		 String logoutTime = TimeUtil.getAddHoursToCurrentTime(currentTime, "3", "hh:mm a");
		 timeInput.click();
		 timeInput.sendKeys(Keys.CONTROL + "a");
		 timeInput.sendKeys(Keys.DELETE);
		 timeInput.sendKeys(logoutTime);
		 
//		wait.waitForVisibility(time).clear();
//		wait.waitForVisibility(time).sendKeys("10:00 AM");
		ReportLogger.stepPass("Enter the Punch Out Time");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
		  "arguments[0].value='Log Out'; arguments[0].dispatchEvent(new Event('input'));",
		  wait.waitForVisibility(note)
		);
		//wait.waitForVisibility(note).sendKeys("Log Out");
		wait.waitForClickable(outSubmitButton).click();
		ReportLogger.stepPass("Click on Punch Out");
		wait.waitForNotVisibility(loadSnipper);	
		ScreenshotUtil.getScreenShot();
	}

}

package pages;

import core.DriverFactory;
import listeners.ReportLogger;
import utils.GeneralActions;
import utils.WaitUtils;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
	private WebDriver driver;
	private WaitUtils wait;
	private GeneralActions actions;
	
	private By dashboardLogo = By.xpath("//h6[text()='Dashboard']");
	private By loadSnipper = By.xpath("(//div[@class='oxd-loading-spinner'])[last()]");
	private By profileIcon = By.xpath("//p[@class='oxd-userdropdown-name']");
	private By logout = By.xpath("//a[text()='Logout']");
	private By searchSection = By.xpath("//input[@placeholder='Search']");
	private By adminSection = By.xpath("//span[text()='Admin']");
	private By pIMSection = By.xpath("//span[text()='PIM']");
	private By leaveSection = By.xpath("//span[text()='Leave']");
	private By timeSection = By.xpath("//span[text()='Time']");
	private By requirementSection = By.xpath("//span[text()='Recruitment']");
	private By myInfoSection = By.xpath("//span[text()='My Info']");
	private By performanceSection = By.xpath("//span[text()='Performance']");
	private By dashboardSection = By.xpath("//span[text()='Dashboard']");
	private By directorySection = By.xpath("//span[text()='Directory']");
	
	public HomePage() {
		try {
			this.driver = DriverFactory.getDriver();
			this.wait = new WaitUtils(driver);
			this.actions = new GeneralActions();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void logOut() {
		wait.waitForClickable(profileIcon).click();
		wait.waitForClickable(logout);
		ReportLogger.stepPass("Log out successfull");
		
}
	
	public void clickPIMSection() {
			wait.waitForClickable(pIMSection).click();
			ReportLogger.stepPass("Click on PIM Section");
	}
	
	public void clickTimeSection() {
		wait.waitForClickable(timeSection).click();
		ReportLogger.stepPass("Click on Time Section");
}
	
	public boolean isLoadSnipperDisappear() {
		return wait.waitForNotVisibility(loadSnipper);
}
	
	public boolean isHomePageDisplayed() {
		return wait.waitForVisibility(dashboardLogo).isDisplayed();
	}
	
}

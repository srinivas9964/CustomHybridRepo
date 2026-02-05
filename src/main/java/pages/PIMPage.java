package pages;
import core.DriverFactory;
import listeners.ReportLogger;
import utils.WaitUtils;
import utils.GeneralActions;
import utils.ScreenshotUtil;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PIMPage {

	private WebDriver driver;
	private WaitUtils wait;
	private GeneralActions actions;
	
	private By loadSnipper = By.xpath("(//div[@class='oxd-loading-spinner'])[last()]");
	private By pimHeader = By.xpath("//h6[text()='PIM']");
	private By addEmployee = By.xpath("//a[text()='Add Employee']");
	private By addEmployeeHeader = By.xpath("//h6[text()='Add Employee']");
	private By addEmpEmployeeFullNameLabel = By.xpath("//label[text()='Employee Full Name']");
	private By addEmpfirstName = By.xpath("//input[@name='firstName']");
	private By addEmpMiddleName = By.xpath("//input[@name='middleName']");
	private By addEmpLastName = By.xpath("//input[@name='lastName']");
	private By addEmpEmployeeID = By.xpath("//label[text()='Employee Id']/following::div/input[@class='oxd-input oxd-input--active']");
	private By addEmpCreateLoginCheckbox = By.xpath("//p[text()='Create Login Details']/following::input[@type='checkbox']");
	private By addEmpSaveBtn = By.xpath("//button[@type='submit' and text()=' Save ']");
	private By addEmpCanceBtn = By.xpath("//button[@type='button' and text()=' Cancel ']");
	
	private By employeeList = By.xpath("//a[text()='Employee List']");
	private By empListPersonalDetailsHeader = By.xpath("//h6[text()='Personal Details']");
	private By empListContactDetails = By.xpath("//a[text()='Contact Details']");
	private By employeeLiseHeader = By.xpath("//h5[text()='Employee Information']");
	private By empListEmployeeName = By.xpath("(//label[text()='Employee Name']/following::div[@class='oxd-autocomplete-text-input--before']/following-sibling::input[@placeholder='Type for hints...'])[1]");
	private By empListEmployeeID = By.xpath("//label[text()='Employee Id']/following::div/input[@class=\"oxd-input oxd-input--active\"]");
	private By empListSearchBtn = By.xpath("//button[@type='submit']");
	private By empListResetBtn = By.xpath("//button[@type='reset']");
	private By recordFoundTxt = By.xpath("//span[contains(normalize-space(.), 'Record Found') or contains(normalize-space(.), 'Records Found')]");
	
	public PIMPage() {
		try {
			this.driver = DriverFactory.getDriver();
			this.wait = new WaitUtils(driver);
			this.actions = new GeneralActions();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	public void addEmployeeRecord(String firstName, String middleName, String lastName) {

			 wait.waitForNotVisibility(loadSnipper);
			 wait.waitForVisibility(pimHeader).isDisplayed();
			 ReportLogger.stepPass("PIM Header Present");
			 wait.waitForClickable(addEmployee).click();
			 ReportLogger.stepPass("Click on Add Employee");
			 wait.waitForNotVisibility(loadSnipper);
			 wait.waitForVisibility(addEmployeeHeader);
			 ReportLogger.stepPass("Add Employee Section Present");
			 wait.waitForVisibility(addEmpEmployeeFullNameLabel).isDisplayed();
			 wait.waitForVisibility(addEmpfirstName).sendKeys(firstName);
			 ReportLogger.stepPass("Enter the First");
			 wait.waitForVisibility(addEmpMiddleName).sendKeys(middleName);
			 ReportLogger.stepPass("Enter the Middle Name");
			 wait.waitForVisibility(addEmpLastName).sendKeys(lastName);
			 ReportLogger.stepPass("Enter the Last Name");
			//wait.waitForVisibility(addEmpEmployeeID).sendKeys("53");
			 //ReportLogger.stepPass("Enter the Employee ID");
			 wait.waitForVisibility(addEmpSaveBtn).click();
			 ReportLogger.stepPass("Click on Save Button");
			 ScreenshotUtil.getScreenShot();
			 wait.waitForVisibility(empListPersonalDetailsHeader).isDisplayed();
			 wait.waitForVisibility(employeeList).click();
			 ReportLogger.stepPass("Click on Employee List");
		 	 wait.waitForVisibility(employeeLiseHeader).isDisplayed();
			 ReportLogger.stepPass("Employee List Section Present");
			 wait.waitForVisibility(empListEmployeeName).clear();
			 wait.waitForVisibility(empListEmployeeName).sendKeys(firstName);
//			 wait.waitForVisibility(empListEmployeeID).click();
//			wait.waitForVisibility(empListEmployeeID).clear();
//			wait.waitForVisibility(empListEmployeeID).sendKeys("53");
			//ReportLogger.stepPass("Enter the Employee ID to Search");
			 wait.waitForVisibility(empListSearchBtn).click();
			 ReportLogger.stepPass("Click on Search");
			 wait.waitForVisibility(recordFoundTxt).isDisplayed();
			 actions.scrollIntoViewByXpath(recordFoundTxt);
			 Assert.assertTrue(actions.dynamicValidateExist("(//div[contains(text(),'#Replace')])[1]", firstName));
			 ReportLogger.stepPass(firstName+":Employee Record Exist");
			 ScreenshotUtil.getScreenShot();
	}
}

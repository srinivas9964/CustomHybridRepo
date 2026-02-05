package tests;

import core.Base;
import core.DriverFactory;
import listeners.ReportLogger;
import pages.LoginPage;
import pages.HomePage;
import pages.PIMPage;
import utils.ExcelUtils;
import utils.GeneralActions;
import utils.WaitUtils;
import utils.ConfigReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends Base {
	private WaitUtils wait;
	private GeneralActions actions;
	
	@DataProvider(name="pimAddEmployeeData")
	public Object[][] pimAddEmployeeData() throws Exception {
		List<Map<String, String>> rowData = ExcelUtils.readPIMAddEmployeeData(ConfigReader.get("excelTestData"), ConfigReader.get("pimAddEmployeeSheetName"));
		Object[][] data = new Object[rowData.size()][3];
		for(int i=0; i<rowData.size(); i++) {
			data[i][0] = rowData.get(i).get("FirstName");
			data[i][1] = rowData.get(i).get("MiddleName");
			data[i][2] = rowData.get(i).get("LastName");			
		}
		return data;
	}
	@Test(dataProvider = "pimAddEmployeeData", priority=2 )
	public void addEmployee(String firstName, String middleName, String lastName)
	{			 	
			DriverFactory.getDriver().get(ConfigReader.get("url"));
			ReportLogger.stepPass("Launch application");
			pages.getLoginPage().verifylogin(ConfigReader.get("username"), ConfigReader.get("password"));
			pages.getHomePage().isLoadSnipperDisappear();
			pages.getHomePage().clickPIMSection();
			pages.getPIMPage().addEmployeeRecord(firstName, middleName, lastName);
			pages.getHomePage().logOut();
	}

}

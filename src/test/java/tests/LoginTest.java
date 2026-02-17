package tests;

import core.Base;
import core.DriverFactory;
import listeners.ReportLogger;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ScreenshotUtil;
import utils.ConfigReader;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.List;
import java.util.Map;
public class LoginTest extends Base{
	static WebDriverWait wait;
			
	@DataProvider(name = "loginData")
	public Object[][] loginData() throws Exception {
	List<Map<String, String>> rows = ExcelUtils.readLoginSheetSheetData(ConfigReader.get("excelTestData"),ConfigReader.get("loginDataSheetName"));
	Object[][] data = new Object[rows.size()][2];
	for (int i = 0; i < rows.size(); i++) {
	data[i][0] = rows.get(i).get("username");
	data[i][1] = rows.get(i).get("password");
	}
	return data;
	}


	@Test(dataProvider = "loginData", priority=1)
	public void testLogin(String user, String pass) {
			DriverFactory.getDriver().get(ConfigReader.get("url"));
			ReportLogger.stepPass("Launch application");
			pages.getLoginPage().verifylogin(user, pass); 
			pages.getHomePage().logOut();
		//Login Test Completed
//		wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
//		pages.getLoginPage().enterUsername(user);
//		pages.getLoginPage().enterPassword(pass);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
//		pages.getLoginPage().clickLogin();
	//Assert.assertTrue(pages.getLoginPage().isLoggedIn(), "Login failed for user: " + user);
	}
}

package tests;
import core.Base;
import core.DriverFactory;
import listeners.ReportLogger;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.TimeUtil;
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

public class PunchTimeTest extends Base {
	@Test
	public void verifyPunchInFlow() {
		DriverFactory.getDriver().get(ConfigReader.get("url"));
		ReportLogger.stepPass("Browser Launched Successfully");
		pages.getLoginPage().verifylogin(ConfigReader.get("username"), ConfigReader.get("password"));
		pages.getHomePage().isLoadSnipperDisappear();
		pages.getHomePage().clickTimeSection();
		pages.getTimePage().navigateToPuncInSection();
		pages.getTimePage().punchIn();
		pages.getTimePage().punchOut();
		pages.getHomePage().logOut();
	}

}

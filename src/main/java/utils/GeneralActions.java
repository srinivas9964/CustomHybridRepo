package utils;

import utils.WaitUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.DriverFactory;

public class GeneralActions {
	private WebDriver driver;
	 private WaitUtils wait;
	 
	 private By username = By.xpath("//input[@name='username']");
	 
	 public GeneralActions() {
			try {
				this.driver = DriverFactory.getDriver();
				this.wait = new WaitUtils(driver);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	 
	public boolean dynamicValidateExist(String xpath, String value) {
		boolean bStatus = false;
		try {
			By latestXpath = By.xpath(xpath.replaceAll("#Replace", value));
		    wait.waitForVisibility(latestXpath).isDisplayed();
		    bStatus=true;
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	    return bStatus;
	}
	
	public boolean scrollIntoViewByXpath(By xpath) {
		boolean bStatus=false;
		try {
			WebElement element = driver.findElement(xpath);
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			bStatus=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return bStatus;
	}
	
}

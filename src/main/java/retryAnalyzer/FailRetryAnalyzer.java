package retryAnalyzer;

import utils.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailRetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	int retryLimit = ConfigReader.getInt("retryCount");
	
	@Override
	public boolean retry(ITestResult result)
	{
		if(counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}
	

}

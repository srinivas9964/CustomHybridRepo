package reports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	 private static ExtentReports extent;
	 
	 public static ExtentReports getExtent() {
	        if (extent == null) {

	            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport_" + timeStamp + ".html");
	            spark.config().setDocumentTitle("Automation Execution Report");
	            spark.config().setReportName("Automation Execution Report");

	            extent = new ExtentReports();
	            extent.attachReporter(spark);
	            extent.setSystemInfo("Executed By", "Srinivas Raj");
	            extent.setSystemInfo("Region", "Generel");

	        }
	        return extent;
	    }
}

package tests;
import org.testng.TestNG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestEngine {
	public static void main(String[] args) {
		TestNG runner = new TestNG();
		List<String> suites = new ArrayList<>();
		File suiteFolder = new File("suites");
		if (!suiteFolder.exists() || !suiteFolder.isDirectory()) {
            System.out.println("Suite folder not found!");
            return;
		}
		 // loop through all .xml files in the folder
        File[] files = suiteFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
        if (files != null) {
            for (File f : files) {
                suites.add(f.getAbsolutePath());
            }
        }
		// if user passed xml path via args[0] use it; otherwise default to testng.xml
		//String suite = (args != null && args.length > 0) ? args[0] : "C:\\Users\\rajas\\eclipse-workspace\\HybridFramework\\testng.xml";
		//suites.add(suites);
		runner.setTestSuites(suites);
		runner.run();
		}
}

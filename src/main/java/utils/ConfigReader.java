package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties props = new Properties();

	static {
	try {
	FileInputStream fis = new FileInputStream("C:\\Users\\rajas\\eclipse-workspace\\HybridFramework\\config\\config.properties");
	props.load(fis);
	} catch (Exception e) {
	System.out.println("Could not load config.properties: " + e.getMessage());
	}
	}

	public static String get(String key) {
	return props.getProperty(key);
	}
	
	public static int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
	}
}

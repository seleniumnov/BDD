package com.framework.library;

public class GlobalVariables {
	
	//Application default Sync time
	public static long SynchronizationTime = 30;
	
		
	// Drivers System Set Property
	public static String CHROME = "webdriver.chrome.driver";
	public static String FIREFOX = "webdriver.gecko.driver";
	public static String IE = "webdriver.ie.driver";

	//Drivers
	
	public static String CHROMEDRIVERPATH = System.getProperty("user.dir")+"//drivers//chromedriver_win//chromedriver.exe";
	public static String CHROMEDRIVERPATH_LINUX = "src/drivers/chromedriver_linux/chromedriver";
	public static String CHROMEDRIVERPATH_MAC = "src\\drivers\\chromedriver_mac\\chromedriver.exe";

	
	public static String IEDRIVERPATH = "src\\drivers\\IEDriver\\IEDriverServer.exe";
	
	
	public static String FIREFOXDRIVERPATH= "src\\drivers\\geckodriver-win\\geckodriver.exe";
	public static String FIREFOXDRIVERPATH_LINUX = "src/drivers/geckodriver-linux/geckodriver.exe";

	//Application URLs
	
	public static String QA = "http://selenium4testing.com/hms/";
	public static String FB = "https://www.facebook.com/";
	
	// Browser
	public static final String BROWSER = getBrowser();
	public static final String APPURL = getApplicationUrl();

	// TestData
	public static final String TESTDATAPATH = getTestDataPath();
	

	
	
	/*Functions to retrieve environment variable*/
	public static String getBrowser() {
		
		String browser = System.getProperty("browser");		
		if (null == browser || browser.isEmpty()) {
			browser = "chrome";
		}
		return browser;
	}

	public static String getTestDataPath() {

		
		String envName = System.getProperty("env");

		if (null == envName || envName.isEmpty()) {
			return System.getProperty("user.dir")+"//TestData//QA//";
		}

		return System.getProperty("user.dir")+"//TestData//"+envName+"//";
	}

	public static String getApplicationUrl() {
		String envName = System.getProperty("env");
	
		return envName;

	}

}
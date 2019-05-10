package com.framework.library;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.io.Files;
import com.cucumber.listener.Reporter;
import com.framework.utility.*;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static WebDriver driver;
	public static String OS = null;
	private static boolean beforeall = false;
	public Scenario scenario;

	@Before
	public void beforeScenario(Scenario scenario) {
		this.scenario = scenario;
		CommonFunctions.soft = new SoftAssertions();
		CommonFunctions.soft.assertThat(true);
		if (GlobalVariables.BROWSER.equalsIgnoreCase("Chrome")) {

			System.setProperty(GlobalVariables.CHROME, getChromeDriverPath());
			System.out.println("Executing Scenario ------"+scenario.getName());
			driver = new ChromeDriver(addChromeOptions());
			maximizeBrowserWindow();

		} else if (GlobalVariables.BROWSER.equalsIgnoreCase("Firefox")) {

			System.setProperty(GlobalVariables.FIREFOX, getFirefoxDriverPath());
			driver = new FirefoxDriver(addFirefoxOptions());
			maximizeBrowserWindow();

		} else if (GlobalVariables.BROWSER.equalsIgnoreCase("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver", GlobalVariables.IEDRIVERPATH);
			driver = new InternetExplorerDriver();
			maximizeBrowserWindow();

		} else if (GlobalVariables.BROWSER.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else if (GlobalVariables.BROWSER.equalsIgnoreCase("Galaxy S5")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("Nexus 5X")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("Nexus 6P")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("iPhone 6")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("iPhone 6 Plus")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("iPad")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("iPad Pro")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("Microsoft Lumia 950")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("Nexus 10")
				|| GlobalVariables.BROWSER.equalsIgnoreCase("Nexus 7")) {
			Map<String, String> mobileEmulation = new HashMap<>();

			mobileEmulation.put("deviceName", GlobalVariables.BROWSER);

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			Proxy proxy = new Proxy();
			proxy.setProxyType(ProxyType.AUTODETECT);
			chromeOptions.setCapability("proxy", proxy);
			chromeOptions.setAcceptInsecureCerts(true);

			System.setProperty(GlobalVariables.CHROME, getChromeDriverPath());
			driver = new ChromeDriver(chromeOptions);

			maximizeBrowserWindow();
		}
		

		if (!beforeall) {
			/* Clear screenshots folder */
			try {
				File dir = new File("./Reports/Screenshots/");
				for (File file : dir.listFiles()) {
					if (!file.isDirectory()) {
						if (file.getName().equals("changeEmail.txt")) {
							continue;
						}
					}
					file.delete();
				}

				// create logger
				File logdir = new File("./Reports/");
				for (File file : logdir.listFiles()) {
					if (!file.isDirectory()) {
						if (file.getName().equals("logInfo.txt")) {
							file.delete();
							break;
						}
					}
				}
				// Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Logger.initializeLogger("./Reports/", "logInfo.txt");

			} catch (Exception e) {
				e.getMessage();
			}
			beforeall = true;
		}
		Logger.logTestInfo(
				"**************************************************************************************************************");
		Logger.logTestInfo("Start of Scenario : " + scenario.getName());
	}

	public static WebDriver getDriver() {
		return driver;
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) throws Exception {

		scenario.write(scenario.getName()+" is Completed Execution");
		if (scenario.isFailed()) {
			
			//scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
			  Logger.logTestInfo("<-------FAILED------->Refer Report"); String
			  screenshotName = scenario.getName().replaceAll(" ", "_"); try {
			  
			  File sourcePath = ((TakesScreenshot)
			  driver).getScreenshotAs(OutputType.FILE); File destinationPath = new File(
			  System.getProperty("user.dir") + "/Reports/screenshots/" + screenshotName +
			  ".png"); Files.copy(sourcePath, destinationPath);
			  
			  // This attach the specified screenshot to the test
			  Reporter.addScreenCaptureFromPath(destinationPath.toString()); } catch
			  (IOException e) { e.printStackTrace(); }
			 
			} 
			else {
			Logger.logTestInfo("<----- PASSED ------>");
		}
		Logger.logTestInfo("End of Scenario : " + scenario.getName());
		Logger.logTestInfo(
				"**************************************************************************************************************");
	}

	@After(order = 0)
	public void AfterSteps() {
		System.out.println("Close Webdriver instance");
		driver.quit();
	}

	public static String getChromeDriverPath() {

		if (OSFinder.isWindows()) {
			return GlobalVariables.CHROMEDRIVERPATH;
		} else if (OSFinder.isUnix()) {
			return GlobalVariables.CHROMEDRIVERPATH_LINUX;
		} else {
			return GlobalVariables.CHROMEDRIVERPATH_LINUX;
		}
	}

	public static String getFirefoxDriverPath() {
		if (OSFinder.isWindows()) {
			return GlobalVariables.FIREFOXDRIVERPATH;
		} else if (OSFinder.isUnix()) {
			return GlobalVariables.FIREFOXDRIVERPATH_LINUX;
		} else {
			return GlobalVariables.FIREFOXDRIVERPATH_LINUX;
		}
	}

	public static FirefoxOptions addFirefoxOptions() {

		FirefoxOptions option = new FirefoxOptions();
		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.AUTODETECT);
		option.setProxy(proxy);
		option.setCapability("marionette", true);
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.cache.disk.enable", false);
		return option;
	}

	public static ChromeOptions addChromeOptions() {

		ChromeOptions chromeOptions = new ChromeOptions();
		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.AUTODETECT);
		chromeOptions.setCapability("proxy", proxy);
		chromeOptions.setAcceptInsecureCerts(true);

		return chromeOptions;
	}

	public static void maximizeBrowserWindow() {

		if (OSFinder.isWindows()) {
			driver.manage().window().maximize();
		} else {
			driver.manage().window().setSize(new Dimension(1600, 900));
		}
	}

}
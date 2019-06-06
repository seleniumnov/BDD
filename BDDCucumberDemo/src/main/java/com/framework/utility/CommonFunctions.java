package com.framework.utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.asserts.SoftAssert;
import org.assertj.core.api.SoftAssertions;

import com.framework.library.GlobalVariables;
import com.framework.library.Hooks;
import cucumber.api.DataTable;

public class CommonFunctions {
	public static final String MM_DD_YYYY = "MM-dd-yyyy";
	public static final String MMM_YYYY = "MMM-yyyy";
	public static final String MM_YYYY = "MM-yyyy";

	public static SoftAssertions soft = new SoftAssertions();

	static WebDriver driver = Hooks.getDriver();

	public static LinkedHashMap<String, String> testdata = new LinkedHashMap<String, String>();

	// envVariable to store and reuse
	public static LinkedHashMap<String, String> envVariable = new LinkedHashMap<String, String>();

	public void readData(DataTable dt) {
		List<List<String>> list = dt.asLists(String.class);
		for (int i = 0; i < list.size(); i++) {
			String string = list.get(i).get(1).trim();
			String[] parts = string.split("\\.");
			if (parts[0].equals("@envVariable")) {
				testdata.put(list.get(i).get(0), envVariable.get(parts[1]));
			} else {
				testdata.put(list.get(i).get(0), JSONUtility.getTCData(parts[1], parts[2], parts[3]));
			}
		}
	}

	public static void switchToNewTab() {
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
	}

	

	public static void switchToParentTab() {
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
	}

	

	// Hover On the Particular Element
	public static void hover(WebDriver driver, WebElement element) {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitForBrowserToLoad(WebDriver driver) {
		try {
			Boolean readyStateComplete = false;
			for (int i = 0; i < GlobalVariables.SynchronizationTime; i++) {
				if (!readyStateComplete) {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("window.scrollTo(0, document.body.offsetHeight)");

					readyStateComplete = (Boolean) executor.executeScript("return document.readyState")
							.equals("complete");
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void input(WebElement element, String value, String fieldName) {
		defaultWait(element);
		
		try {
			if (element.isDisplayed() || element.isEnabled()) {
				element.click();
				defaultWait(element);
				element.clear();
				defaultWait(element);
				element.sendKeys(value);
				waitForJStoLoad();
				Logger.logTestInfo("Entered Text into " + fieldName + " : " + value);
			}
		} catch (Exception e) {
			Logger.logTestInfo("Fail to Enter " + value + " in " + fieldName);
			soft.fail("Fail to Enter " + value + " in " + fieldName);
			// Assert.fail("Fail to Enter " + value + " in " + fieldName);
		}

	}

	public static String getText(WebElement element) {
		String text = null;
		defaultWait(element);
		if (element.isDisplayed()) {
			text = element.getText().trim();
		}

		return text;
	}

		
	public static WebElement explicitWait(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.getMessage();
		}
		return element;
	}

	public static WebElement waitElementToBeClick(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public static void defaultWait(WebElement element) {

		try {
			explicitWait(element);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	


	public static void selectByVisibleText(WebElement ele, String testData, String fieldName) {
		try {
			new Select(ele).selectByVisibleText(testData);
			Logger.logTestInfo("Selected " + testData + " from " + fieldName);
		} catch (Exception e) {
			Assert.fail("Fail to Select " + testData + " from " + fieldName + "Error " + e.getMessage());
		}
	}

	public static void selectByValue(WebElement ele, String testData, String fieldName) {
		try {
			new Select(ele).selectByValue(testData);
			Logger.logTestInfo("Selected " + testData + " from " + fieldName);
		} catch (Exception e) {
			Assert.fail("Fail to Select " + testData + " from " + fieldName + "Error " + e.getMessage());
		}
	}

	public static void selectByIndex(WebElement ele, int testData, String fieldName) {
		try {
			new Select(ele).selectByIndex(testData);
			Logger.logTestInfo("Selected " + testData + " from " + fieldName);
		} catch (Exception e) {
			Assert.fail("Fail to Select " + testData + " from " + fieldName + "Error " + e.getMessage());
		}
	}

	public static void jScriptClick(WebElement element, String fieldName) {

		try {
			driver.switchTo().defaultContent();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Logger.logTestInfo("Clicked On:  " + fieldName);
			waitForPageToComplete();
			waitForJStoLoad();
		} catch (Exception e) {
			Logger.logTestInfo("Fail to Click on " + fieldName);
			Assert.fail("Fail to Click on " + fieldName + " Error: " + e.getMessage());
		}
	}

	public static void jScriptInput(WebElement element, String value, String fieldName) {

		try {
			driver.switchTo().defaultContent();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
			waitForPageToComplete();
			waitForJStoLoad();
		} catch (Exception e) {

			Assert.fail("Fail to input " + fieldName + " Error: " + e.getMessage());
		}
	}

	public static void waitForSomeTime(long waitTime) throws Exception {
		Thread.sleep(waitTime);
	}

	public static void waitForPageToComplete() {

		ExpectedCondition<Boolean> pageLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(pageLoad);
		} catch (Exception e) {
			Assert.fail("Timeout Waiting for Page Load Request to Complete");
		}
	}

	public static boolean waitForJStoLoad() {
		boolean jsLoad = false;
		try {

			for (int i = 0; i < 120; i++) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;

				jsLoad = (Boolean) executor.executeScript("return jQuery.active == 0");
				
				Thread.sleep(1000);

				if (jsLoad)
					break;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return jsLoad;
	}

	public static void click(WebElement element, String buttonName) {
		
		try {
			element.click();
			Logger.logTestInfo("Clicked on " + " : " + buttonName);
			waitForJStoLoad();
		}catch(Exception e) {
			Logger.logTestInfo("Not able to click on  " + buttonName);
			Assert.fail("Not able to click on  " + buttonName);
		}
	}

	
	public static int getRandomInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

		public static void isElementPresent(WebElement element, String ObjName) {
		try {
			if (element.isDisplayed() || element.isEnabled()) {
				soft.assertThat("Element is present: " + ObjName);
				Logger.logTestInfo("Element is present: " + ObjName);
			} else {
				soft.fail("Element is not present: " + ObjName);
				Logger.logTestInfo("Element is not present: " + ObjName);
			}
		} catch (NoSuchElementException e) {
			Logger.logTestInfo("Element is not present: " + ObjName);
			soft.fail("Element is not present: " + ObjName);
			e.printStackTrace();
		}

	}

	public static void isElementLink(WebElement element, String ObjName) {
		try {
			
			if (element.getAttribute("innerHTML").contains("href")) {
				Logger.logTestInfo("Element is a link : " + ObjName);
			} else {
				Logger.logTestInfo("Element is not a link : " + ObjName);
				soft.fail("Element is not a link: " + ObjName);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public static void isElementNotLink(WebElement element, String ObjName) {
		try {
			
			if (!element.getAttribute("innerHTML").contains("href")) {
				Logger.logTestInfo("Element is a not link : " + ObjName);
			} else {
				Logger.logTestInfo("Element is a link : " + ObjName);
				soft.fail("Element is a link: " + ObjName);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


}
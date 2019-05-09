package com.framework.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
//import com.bgs.pageObjects.Registration;
import com.cucumber.listener.Reporter;
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

	public static void scrollIntoView(WebElement element) {

		/*
		 * try {
		 * 
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].scrollIntoView(true);", element);
		 * waitForJStoLoad();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	public static void switchToParentTab() {
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
	}

	public static void waitForpageToLoad(WebDriver driver, String strbrowserType) {
		try {

			if (strbrowserType.equalsIgnoreCase("firefox"))
				Thread.sleep(60000);
			else
				driver.manage().timeouts().implicitlyWait(GlobalVariables.SynchronizationTime, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	// Highlight the Particular Element
	public static void highlight(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", element);
			Thread.sleep(200);
			js.executeScript("arguments[0].style.border=''", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Scroll To And Highlight the Particular Element
	public static void ScrollToAndHighlight(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			js.executeScript("arguments[0].style.border='3px solid red'", element);
			Thread.sleep(200);
			js.executeScript("arguments[0].style.border=''", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Page Load wait
	public static void waitForpageToLoad(WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(GlobalVariables.SynchronizationTime, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public List<String> captureAccountNums() { List<String> accounts = null; try
	 * { Map<String, String> data = getJsonData("registration",
	 * "existingPersonalAccount"); accounts = new ArrayList<>();
	 * accounts.add(data.get("account1")); accounts.add(data.get("account2"));
	 * accounts.add(data.get("account3")); accounts.add(data.get("account4"));
	 * accounts.add(data.get("account5")); accounts.add(data.get("account6"));
	 * accounts.add(data.get("account7")); accounts.add(data.get("account8"));
	 * accounts.add(data.get("account9")); accounts.add(data.get("account10")); }
	 * catch (Exception e) {
	 * 
	 * } return accounts; }
	 * 
	 * @SuppressWarnings({ "rawtypes", "unchecked" }) public Map<String, String>
	 * getJsonData(String fileName, String value) {
	 * 
	 * Map<String, String> testData = new HashMap<>(); try { if (fileName.length() >
	 * 1 && !fileName.isEmpty() && !fileName.equals(null)) { Object obj = new
	 * JSONParser() .parse(new FileReader(System.getProperty("user.dir") +
	 * "/TestData/QA/" + fileName + ".json"));
	 * 
	 * org.json.simple.JSONObject jobj = (org.json.simple.JSONObject) obj;
	 * 
	 * Map data = (Map) jobj.get(value);
	 * 
	 * Iterator<Map.Entry> loop = data.entrySet().iterator();
	 * 
	 * while (loop.hasNext()) {
	 * 
	 * Map.Entry pair = loop.next();
	 * 
	 * String key = pair.getKey().toString(); String value1 =
	 * pair.getValue().toString(); testData.put(key, value1);
	 * 
	 * }
	 * 
	 * } else { Assert.fail(); } } catch (Exception e) { e.getStackTrace(); } return
	 * testData; }
	 */
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
		// waitForJStoLoad();
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

	public String emailIDReader() {
		String line = null;
		try {
			String fileNmae = "\\Reports\\changeEmail.txt";
			File file = new File(fileNmae);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				// process the line
				System.out.println(line);
				break;
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return line;
	}


		public void emailIDWriter(String emailID) {
			try {
				String fileNmae = "C:\\BGSTestAutomation\\bgsAutomation\\Reports\\changeEmail.txt";
				FileWriter fw = new FileWriter(fileNmae);
				fw.write(emailID);
				fw.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("Success...");
			

		}

	


	public static String getAttribute(WebElement element, String attribute) {
		String attributeText = null;
		try {
			defaultWait(element);
			if (element.isDisplayed()) {
				attributeText = element.getAttribute(attribute).trim();
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		return attributeText;
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

	public static boolean selectValue(WebElement element, String selType, String value) {
		boolean isSelected = false;
		try {
			Select objSelect = new Select(element);

			switch (selType.toUpperCase()) {
			case "INDEX":
				objSelect.selectByIndex(Integer.parseInt(value));
				isSelected = true;
				break;

			case "VALUE":
				objSelect.selectByValue(value);
				isSelected = true;
				break;

			case "VISIBLETEXT":
				defaultWait(element);
				objSelect.selectByVisibleText(value);
				isSelected = true;
				break;

			default:
				Assert.fail("Unable to Select " + value);
				break;
			}

			if (!isSelected)
				Assert.fail("Unable to select the value");

		} catch (Exception e) {
			Assert.fail("Failed to select value " + e.getMessage());
		}
		return isSelected;
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
//				if(!(executor.executeScript("return jQuery.active == 0")==null))
				jsLoad = (Boolean) executor.executeScript("return jQuery.active == 0");
				
				Thread.sleep(1000);

				if (jsLoad)
					break;
			}
		} catch (Exception e) {
			e.getMessage();
//			e.printStackTrace();
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

	/**
	 * get current date in the "dd.MM.yyyy" pattern
	 */
	public static String getCurrentDate() {
		return getCurrentDate("dd.MM.yyyy");
	}

	/**
	 * get current date in the custom pattern
	 */
	public static String getCurrentDate(String pattern) {
		return formatDate(new Date(), pattern);
	}

	public static String getDayFromNow(String dateFromNow, String pattern) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then;
		if (dateFromNow.contains("-")) {
			then = now.minusDays(Long.parseLong(dateFromNow.replace("-", "")));
		} else {
			then = now.plusDays(Long.parseLong(dateFromNow));
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return then.format(formatter);
	}

	/**
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateEnLocale(String pattern) {
		return new SimpleDateFormat(pattern, new Locale("en", "EN")).format(new Date());
	}

	/**
	 * {@link #getCurrentDateEnLocale(String)} with arg "MM-dd-yyyy"
	 *
	 * @return
	 */
	public static String getCurrentDateEnLocale() {
		return getCurrentDateEnLocale(MM_DD_YYYY);
	}

	/**
	 * Get the unic suffix based on the current date
	 */
	public static String getTimestamp() {
		return getCurrentDate("yyyyMMddHHmmssSSS");
	}

	/**
	 * Parse string to the Calendar entity
	 *
	 * @param s
	 *            - String to be converted
	 */
	public static Calendar dateString2Calendar(String s) throws ParseException {
		Calendar cal = Calendar.getInstance();
		Date d1 = new SimpleDateFormat("dd.mm.yyyy").parse(s);
		cal.setTime(d1);
		return cal;
	}

	/**
	 * Format date to string using custom pattern
	 *
	 * @param date
	 *            - date to be formatted
	 * @param pattern
	 *            - custom pattern of the date
	 */
	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * Format date to string using 'en' locale
	 *
	 * @param date
	 *            - date to be formatted
	 * @param pattern
	 *            - custom pattern of the date
	 */
	public static String formatDateEnLocation(Date date, String pattern) {
		return new SimpleDateFormat(pattern, new Locale("en", "EN")).format(date);
	}

	/**
	 * Format date in the "dd.MM.yyyy" pattern
	 *
	 * @param date
	 *            - date to be formatted
	 */
	public static Date parseDate(String date) {
		return parseDate(date, "dd.mm.yyyy");
	}

	/**
	 * Parse string to the Date entity
	 *
	 * @param date
	 *            - string to be parsed
	 * @param pattern
	 *            custom pattern of the date, according to which string should be
	 *            parsed
	 */
	public static Date parseDate(String date, String pattern) {
		Date result = null;
		try {
			result = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.getMessage();
		}
		return result;
	}

	/**
	 * Increase date to the N days
	 *
	 * @param date
	 *            date to be increased
	 * @param days
	 *            casesCount of the days
	 * @return date increased by N days
	 */
	public static Date increaseDateByXDays(final Date date, final int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	public static int getRandomInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

	public static int getRandomInt(int leftBound, int rightBound) {
		return ThreadLocalRandom.current().nextInt(leftBound, rightBound);
	}

	/*
	 * public static String getRandomEmail(String nameLength) { return
	 * String.format("%s@%s.%s",
	 * RandomStringUtils.randomAlphabetic(Integer.parseInt(nameLength)),
	 * RandomStringUtils.randomAlphabetic(3),
	 * RandomStringUtils.randomAlphabetic(2)).toLowerCase(); }
	 */
	/**
	 * This method creates a RegExp pattern.
	 *
	 * @param regex
	 *            pattern in a string
	 * @param matchCase
	 *            should be matching case sensitive?
	 */
	private static Pattern regexGetPattern(String regex, boolean matchCase) {
		int flags;
		if (matchCase) {
			flags = 0;
		} else {
			flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
		}
		return Pattern.compile(regex, flags);
	}

	/**
	 * Get first match in the string
	 */
	public static String regexGetMatch(String text, String regex) {
		return regexGetMatch(text, regex, false);
	}

	/**
	 * Get first match in the string
	 */
	public static String regexGetMatch(String text, String regex, boolean matchCase) {
		return regexGetMatchGroup(text, regex, 1, matchCase);
	}

	/**
	 * validate, that string corresponds a pattern
	 */
	public static boolean regexIsMatch(String text, String pattern) {
		return regexIsMatch(text, pattern, false);
	}

	/**
	 * validate, that string corresponds a pattern
	 */
	public static boolean regexIsMatch(String text, String regex, boolean matchCase) {
		Pattern p = regexGetPattern(regex, matchCase);
		Matcher m = p.matcher(text);
		return m.find();
	}

	/**
	 * Get the N-th matching group
	 *
	 * @param text
	 *            - String where we are looking for
	 * @param regex
	 *            - pattern for which we are looking for
	 * @param groupIndex
	 *            -Number of matching group we want to find
	 */
	public static String regexGetMatchGroup(String text, String regex, int groupIndex) {
		return regexGetMatchGroup(text, regex, groupIndex, false);
	}

	/**
	 * Get the N-th matching group
	 *
	 * @param text
	 *            - String where we are looking for
	 * @param regex
	 *            - pattern for which we are looking for
	 * @param groupIndex
	 *            - Number of matching group we want to find
	 * @param matchCase
	 *            - Is search case sensitive?
	 */
	public static String regexGetMatchGroup(String text, String regex, int groupIndex, boolean matchCase) {
		Pattern p = regexGetPattern(regex, matchCase);
		Matcher m = p.matcher(text);
		if (m.find()) {
			return m.group(groupIndex);
		} else {
			return null;
		}
	}

	/**
	 * Get the casesCount of groups has been found
	 *
	 * @param text
	 *            - String where we are looking for
	 * @param regex
	 *            - pattern for which we are looking for * @param matchCase - Is
	 *            search case sensitive?
	 */
	public static int regexGetNumberMatchGroup(String text, String regex) {
		return regexGetNumberMatchGroup(text, regex, false);
	}

	/**
	 * Get the casesCount of groups has been found
	 *
	 * @param text
	 *            - String where we are looking for
	 * @param regex
	 *            - pattern for which we are looking for
	 */
	public static int regexGetNumberMatchGroup(String text, String regex, boolean matchCase) {
		int number = 0;
		Pattern p = regexGetPattern(regex, matchCase);
		Matcher m = p.matcher(text);
		while (m.find()) {
			m.group();
			number++;
		}
		return number;
	}

	/**
	 * Escape () and backslash
	 *
	 * @param text
	 *            - text with special symbols
	 * @return escaped text
	 */
	public static String escapeMetaCharacters(final String text) {
		return text.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
	}

	/**
	 * Escape backslash and quote
	 *
	 * @param text
	 *            - text with special symbols
	 * @return text without backslashes
	 */
	public static String escapeSeleniumCharacters(final String text) {
		return text.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\\\"");
	}

	/**
	 * Adding \s to the numbers in the string (useful in validation, if there is a
	 * space between digits ("99 678" for example))
	 *
	 * @param text
	 *            - text with numbers
	 * @return escapeSpacesFromNumbers text
	 */
	public static String escapeSpacesFromNumbers(final String text) {
		return text.replaceAll("(\\d)", "$1\\\\s*");
	}

	public static Double parseDouble(String value) {
		if (value != null) {
			return Double.parseDouble(value.trim());
		} else {
			return null;
		}
	}

	public static void verifyContainsUsingSoft(String actual, String expected) {

		// SoftAssert soft = new SoftAssert();
		// soft.assertFalse(actual.contains(expected));
		// soft.assertAll();
	}

	public static boolean isStringNotEmpty(String value) {
		return (value != null) && (value.trim().length() != 0);
	}

	public static boolean isStringStartsWith(String first, String second) {
		first = replaceDoubleSpacesWithSingle(first);
		second = replaceDoubleSpacesWithSingle(second);
		int minLength = Math.min(first.length(), second.length());
		if (minLength == 0) {
			return false;
		}
		return first.substring(0, minLength - 1).equals(second.substring(0, minLength - 1));
	}

	public static boolean isStringEndsWith(String first, String second) {
		if (first.length() < second.length()) {
			return second.endsWith(first);
		} else {
			return first.endsWith(second);
		}
	}

	public static String replaceDoubleSpacesWithSingle(String value) {
		return value.replaceAll("\\ \\ ", " ");
	}

	/*
	 * public static void screenCapture(WebDriver driver) { String screenshotName =
	 * "bgs"; Timestamp timestamp = new Timestamp(System.currentTimeMillis()); try {
	 * File sourcePath = ((TakesScreenshot)
	 * driver).getScreenshotAs(OutputType.FILE); File destinationPath = new
	 * File(System.getProperty("user.dir") + "/Reports/screenshots/" +
	 * screenshotName + timestamp.getTime() + ".png"); Files.copy(sourcePath,
	 * destinationPath);
	 * 
	 * Reporter.addScreenCaptureFromPath(destinationPath.toString()); } catch
	 * (IOException e) { e.printStackTrace(); } }
	 */

	public static void isElementPresent(WebElement element, String ObjName) {
		try {
			scrollIntoView(element);
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
			scrollIntoView(element);
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
			scrollIntoView(element);
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
	public static void verifygetText(WebElement element, String texttoverify, String ObjName) {

		try {
			String elementtext = element.getText();
			elementtext = elementtext.replaceAll("\\r\\n|\\r|\\n", " ");

			if (!elementtext.equals(texttoverify)) {
				soft.fail("Element Text is not present: " + ObjName);
				Logger.logTestInfo("Text is not as Expected: " + ObjName + " Expected: " + texttoverify + " Actual:" + elementtext );
			}else {
				Logger.logTestInfo("Text is as Expected : " + ObjName + " Expected: " + texttoverify);
			}
		} catch (Exception e) {
			Logger.logTestInfo("Element Text is not present: " + ObjName);
			soft.fail("Element Text is not present: " + ObjName);
		}
	}

	public static void implicitWait(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public Double getAccountThroughAPI(String acntType) throws IOException {
		Random rand = new Random();
		int rand_int = rand.nextInt(10000);

		String strURL = null;
		String POST_PARAMS = null;
		if (acntType.toUpperCase() == "PERSONAL") {
			strURL = testdata.get("perAccount");
			POST_PARAMS = "{\n" + "\"XXARCustomerIface\":{\r\n" + "    \"OrigSystemCustomerRef\": \"AA-ref-test-"
					+ rand_int + "\",\r\n" + "    \"InsertUpdateFlag\": \"I\",\r\n"
					+ "    \"WebEnabled\": \"JEPPDIRECT\",\r\n" + "    \"MarketSegment\": \"GA-CC\",\r\n"
					+ "    \"SalesChannelCode\": \"GA PILOT\",\r\n" + "    \"PersonFlag\": \"Y\",\r\n"
					+ "    \"PersonFirstName\": \"John\",\r\n" + "    \"PersonLastName\": \"Doe\",\r\n"
					+ "    \"IssuingCountry\": \"US\",\r\n" + "    \"SourceSystem\": \"BDA\",\r\n"
					+ "    \"SourceTransactionKey\": \"asfasfwerh" + rand_int + "\"" + "	 }" + "\n}";
		} else if (acntType == "COMPANY") {
			strURL = testdata.get("comAccount");
			POST_PARAMS = "{\n" + "\"XXARCustomerIface\":{\r\n" + "    \"OrigSystemCustomerRef\": \"AA-ref-test-"
					+ rand_int + "\",\r\n" + "    \"InsertUpdateFlag\": \"I\",\r\n"
					+ "    \"WebEnabled\": \"JEPPDIRECT\",\r\n" + "    \"MarketSegment\": \"GA-CC\",\r\n"
					+ "    \"SalesChannelCode\": \"GA PILOT\",\r\n" + "    \"CustomerName\": \"TEST AIRLINES\",\r\n"
					+ "    \"PersonFlag\": \"N\",\r\n" + "    \"IssuingCountry\": \"US\",\r\n"
					+ "    \"SourceSystem\": \"BDA\",\r\n" + "    \"SourceTransactionKey\": \"asfasfwerh" + rand_int
					+ "\"" + "	 }" + "\n}";
		}

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		}

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		String encoding = Base64.getEncoder().encodeToString(("HybrisID:Hybris#test").getBytes("UTF-8"));
		// Registration reg = new Registration(driver);

		URL obj = new URL(strURL);
		// URL("https://linf-dev-1.aviallinc.com:7443/process-engine/rt/CreateCustomerAsPersonDevAgent");
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);
		postConnection.setRequestProperty("Authorization", "Basic " + encoding);

		OutputStream os = postConnection.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = postConnection.getResponseCode();

		if (responseCode == 200) { // success HttpURLConnection.HTTP_CREATED
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			// System.out.println(response.toString());
			String[] Accntnum = response.toString().split("\"CustomerNumber\":\"");
			String[] Accntnum1 = Accntnum[1].split("\"}");

			return (double) Integer.parseInt(Accntnum1[0].toString());
		} else {
			Logger.logTestInfo("POST NOT WORKED");
			return null;
		}
	}

}
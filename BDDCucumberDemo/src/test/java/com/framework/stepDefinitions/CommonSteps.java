package com.framework.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framework.library.GlobalVariables;
import com.framework.library.Hooks;
import com.framework.pageObjects.HomePage;
import com.framework.pageObjects.LoginPage;
import com.framework.utility.CommonFunctions;
import com.framework.utility.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CommonSteps extends CommonFunctions {

	WebDriver driver = Hooks.getDriver();
	HomePage home = new HomePage(driver);
	LoginPage login = new LoginPage(driver);
	
	@Given("^Navigate to HMS Application$")
	public void i_navigate_to_aviall() throws Throwable {
		try {
			
			driver.manage().deleteAllCookies();
			driver.navigate().to(GlobalVariables.QA);
			waitForJStoLoad();
			Logger.logTestInfo("Navigated to URL :" + GlobalVariables.QA);
		}catch(Exception e) {
			Logger.logTestInfo("Failed to navigate URL :" + GlobalVariables.QA);
			e.getMessage();
		}
	}
	
	@Given("^Navigate to Facebook Application$")
	public void i_navigate_to_facebook() throws Throwable {
		try {
			
			driver.manage().deleteAllCookies();
			driver.navigate().to(GlobalVariables.FB);
			waitForJStoLoad();
			Logger.logTestInfo("Navigated to URL :" + GlobalVariables.FB);
		}catch(Exception e) {
			Logger.logTestInfo("Failed to navigate URL :" + GlobalVariables.FB);
			e.getMessage();
		}
	}

	@Then("^Validate the Scenario$")
	public void verify_result() throws Throwable {
		try {
			CommonFunctions.soft.assertAll();
		}catch(Exception e) {
			Logger.logTestInfo("Failed to AsserAll");
			e.getMessage();
		}
	}

	@Then("^(.+) Page is Opened$")
	public void page_is_opened(String pagename) throws Throwable {
		WebElement currentpage = null;
		String page = pagename.replaceAll("\'", "");
		waitForJStoLoad();
		switch (page) {
		case "HOME":
			currentpage = home.getHomepage();
			break;
		case "LOGIN":
			currentpage = login.getLoginPage();
			break;
		
		default:
			// Statements
		}

		try {
			if(currentpage.isDisplayed()) {
				Logger.logTestInfo(page + " - Page is displayed");
				soft.assertThat(page + " - Page is displayed");
			}
			else{
				soft.fail(page + " - Page is not displayed");
				Logger.logTestInfo(page + " - Page is not displayed");
			}
		} catch (Exception e) {
			Logger.logTestInfo(page + " - Page is not displayed");
			soft.fail(page + " - Page is not displayed");
			e.printStackTrace();
		}

	}

}

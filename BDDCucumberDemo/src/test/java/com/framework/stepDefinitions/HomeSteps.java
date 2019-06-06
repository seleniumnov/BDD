package com.framework.stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import com.framework.library.Hooks;
import com.framework.pageObjects.HomePage;
import com.framework.utility.CommonFunctions;
import com.framework.utility.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class HomeSteps extends CommonFunctions {
	WebDriver driver = Hooks.getDriver();
	HomePage home = new HomePage(driver);

	@When("^I Click on Login$")
	public void i_click_on_login_button() throws Throwable {
		try {
			click(home.getLoginLink(), "LOGIN");
		} catch (Exception e) {
			Assert.fail("click on login link");
		}
	}

	@And("^I Click on LogOut$")
	public void i_click_on_logout_link() throws Throwable {
		try {
			waitForJStoLoad();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", home.getLogOutLink());
			Logger.logTestInfo("Clicked on LogOut");
		} catch (Exception e) {
			Logger.logTestInfo("Not able to Click on LogOut");
			Assert.fail("Logout is not successfull");
		}
	}
	
	

	
}





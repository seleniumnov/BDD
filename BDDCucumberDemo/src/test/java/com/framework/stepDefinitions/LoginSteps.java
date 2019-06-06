package com.framework.stepDefinitions;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import com.framework.library.Hooks;
import com.framework.pageObjects.HomePage;
import com.framework.pageObjects.LoginPage;
import com.framework.utility.CommonFunctions;
import com.framework.utility.Logger;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class LoginSteps extends CommonFunctions{
	WebDriver driver = Hooks.getDriver();
	LoginPage login = new LoginPage(driver);
	HomePage home = new HomePage(driver);

	@When("^I Login to HMS Application Using Below Credentials:$")
	public void i_login_to_bgs_application_username_password(DataTable dt) throws Throwable {
		readData(dt);
		try {
			
			input(login.getUserName(),testdata.get("username"),"user name");
			input(login.getPassword(),testdata.get("password"),"password");
			
			click(login.getLogIn(),"Login");
					
			if(!home.getHomepage().isDisplayed()) {
				Assert.fail("Login failed");
				Logger.logTestInfo("Not able to Login to Application");
			}
			else {
				Logger.logTestInfo("Successfully Logged into the Application");
			}
			
		} catch (Exception e) {
			Logger.logTestInfo("Not able to Login to Application");
			Assert.fail("Login failed");
		}
	}
	
	@When("^I Logout from HMS Application$")
	public void userLogout() {
		click(home.getHomepage(), "Logout");
	}

	@And("^I Login to BGS Application Using Below Invalid Credentials:$")
	public void i_login_to_bgs_application_invalid_credentials(DataTable dt) throws Throwable {
		readData(dt);
		try {
			input(login.getUserName(),testdata.get("username"),"user name");
			input(login.getPassword(),testdata.get("password"),"password");
			click(login.getTermsCheckbox(),"Terms CheckBox");
			click(login.getLogIn(),"Login");
		} catch (Exception e) {
			Logger.logTestInfo("Not able to Login to Application");
			Assert.fail("Login failed");
			
		}
	}

	@Then("^Following Error Message Should be Displayed:$")
	public void error_displayed(DataTable dt) {
		readData(dt);
		if (!login.getLoginError().getText().contains(testdata.get("errormsg"))) {
			soft.fail("Error message is not displayed as expected");
			Logger.logTestInfo("Error message is not displayed as expected: Expected :" + testdata.get("errormsg") + " Actual: "+ login.getLoginError().getText() );
			}
		else {
			Logger.logTestInfo("Error message is displayed as expected: " + testdata.get("errormsg"));
		}
	}


	@Then("^Verify the Display AnonymousUser Message Top of Login Page:$")
	public void MessageTopLoginPage(DataTable dt) {
		readData(dt);
		if (!login.getLoginError().getText().contains(testdata.get("AnonmousUserMsg"))) {
			soft.fail("Error message is not displayed as expected");
			Logger.logTestInfo("Error message is not displayed as expected: Expected :" + testdata.get("AnonmousUserMsg") + " Actual: "+ login.getLoginError().getText() );
		}
		else {
			Logger.logTestInfo("Error message is displayed as expected: " + testdata.get("AnonmousUserMsg"));
		}
	}

	@Then("^Verify the text Read and Agreed to Jeppesen Terms of Use$")
	public void VerifyTextAgreedTermsUse() {

		try {
			isElementPresent(login.getTermsCheckbox(),"Term CheckBox");
			if(login.getTermUseMessage().getText().contains("I have read and agreed to")) {
				Logger.logTestInfo("Terms and Conditions text is: " + login.getTermUseMessage().getText());
			}else {
				Logger.logTestInfo("Terms and Conditions text is not as expected: " + login.getTermUseMessage().getText());
			}
				
			
		} catch (Exception e) {
			Logger.logTestInfo("Term of Use not Available");
			Assert.fail("Term and Use Message not Available");			
		}
	}

	@Then("^Try to Login Without Checking TermUse Checkbox:$")
	public void LoginWithoutCheckingCheckbox(DataTable dt) {
		readData(dt);
		try {
			input(login.getUserName(),testdata.get("username"),"user name");
			input(login.getPassword(),testdata.get("password"),"password");
			if(login.getLogIn().isEnabled()){				
				Logger.logTestInfo("Login is not disabled");
				soft.fail("Login is not disabled");
			}else {
				Logger.logTestInfo("Login is disabled as expected");
			}
		} catch (Exception e) {
			Assert.fail("Error in validation");
		}
	}

	@Then("^I Click on Live Chat$")
	public void ClickLiveChart() {
		try {
			driver.switchTo().frame(1);
			click(login.getLiveChat(), "Live Chat");
			driver.switchTo().defaultContent();
		/*	if(login.getLiveChatHeader().getText().contains("Live Chat"));*/
					
		} catch (Exception e) {
			Assert.fail("Live Chat window not available");
		}
	}


	@And ("^I Input the Values Displayed in the Live Chart Popup window:$")
	public void InputValuesDisplayedLiveChart (DataTable dt) {
		readData(dt);
		try {
			input(login.getLiveChatName(),testdata.get("Name")," Name");
			input(login.getLiveChatEmail(),testdata.get("Email")," Email");
			input(login.getLiveChatCustomerNum(),testdata.get("CustomerNo")," Customer Number");
			input(login.getLiveCartMessage(),testdata.get("message")," Message");
			click(login.getLiveCartSubmit(),"Submit");		
		} catch (Exception e) {
			Assert.fail("Error in Input the Values Displayed in the Live Chart Popup window");
		}
	}

	@Then("^User should Able to View Success Message$")
	public void SuccessMessage() {
		try {
			if(login.getLiveCartSubmitConfirmation().getText().contains("Message has been submitted"));
			click(login.getLiveDropdown(),"Live Chat Drop down");
			if(!login.getLiveChat().isDisplayed()) {
				Logger.logTestInfo("Not Redirected to Live chat window");
				soft.fail("Not Redirected to Live chat window");
			}else {
				Logger.logTestInfo("Redirected to Live chat window");
			}

		} catch (Exception e) {
			Assert.fail("Live Chat not submitted Successfully");
		}
	}
}

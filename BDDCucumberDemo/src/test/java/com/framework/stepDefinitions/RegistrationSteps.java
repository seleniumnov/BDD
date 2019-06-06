package com.framework.stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.framework.library.Hooks;
import com.framework.pageObjects.HomePage;
import com.framework.pageObjects.RegistrationPage;
import com.framework.utility.CommonFunctions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class RegistrationSteps extends CommonFunctions{
	
	WebDriver driver = Hooks.getDriver();
	HomePage home = new HomePage(driver);
	RegistrationPage register = new RegistrationPage(driver);
	
	
	@Then("^I Click on Registration from Left Panel$")
	public void i_Click_on_Registration_from_Left_Panel()  {
	    
	    click(home.getRegistration(), "Registration");
	}

	@Then("^I Click on Emergency Registration$")
	public void i_Click_on_Emergency_Registration()  {
	    
	    click(home.getERegistration(), "Emergency Registration");
	}

	@And("^I Input and Submit All Mandatory Fields in Form$")
	public void i_Input_and_Submit_All_Mandatory_Fields_in_Form()  {
	   
		selectByVisibleText(register.getPatientCategory(), "Staff", "");
	}

	@And("^I Logout From Application$")
	public void i_Logout_From_Application()  {
	    
	    
	}

}

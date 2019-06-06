package com.framework.stepDefinitions;

import com.framework.utility.CommonFunctions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class FBCreationSteps  extends CommonFunctions{
	
	
	@Given("^I Entered All Mandatory Details$")
	public void i_Entered_All_Mandatory_Details()  {
	    // Write code here that turns the phrase above into concrete actions
		
		input(element, value, fieldName);
		input(element, value, fieldName);
		input(element, value, fieldName);
		input(element, value, fieldName);
		selectByVisibleText(ele, testData, fieldName);
		selectByVisibleText(ele, testData, fieldName);
		selectByVisibleText(ele, testData, fieldName);
		click(element, buttonName);
	    
	}

	@And("^I Click on SignUp$")
	public void i_Click_on_SignUp()  {
	    // Write code here for filling all details in registration
		click(element, buttonName);
	}

	@When("^I Validate the Sceanrio$")
	public void i_Validate_the_Sceanrio()  {
	    
		// Write Code for click on signup button
	    
	}

}

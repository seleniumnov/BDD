package com.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.utility.CommonFunctions;

public class RegistrationPage extends CommonFunctions{
	
	
	WebDriver driver;
	public RegistrationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.NAME, using = "PATIENT_CAT")
	private WebElement PATIENT_CAT;

	public WebElement getPatientCategory() {
		return explicitWait(PATIENT_CAT);
	}

}

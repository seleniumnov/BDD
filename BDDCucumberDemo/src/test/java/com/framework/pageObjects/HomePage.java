package com.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.utility.CommonFunctions;

public class HomePage extends CommonFunctions {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[text()='Logout']")
	private WebElement homepage;

	public WebElement getHomepage() {
		return explicitWait(homepage);
	}

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Login')]")
	private WebElement loginlink;

	public WebElement getLoginLink() {
		return loginlink;
	}
	
	@FindBy(how = How.XPATH, using = "//ul[@id='navigation']/li[1]/a")
	private WebElement registration;

	public WebElement getRegistration() {
		return explicitWait(registration);
	}

	@FindBy(how = How.XPATH, using = "//a[text()='Emergency Registration']")
	private WebElement eRegistration;

	public WebElement getERegistration() {
		return eRegistration;
	}

	

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign Out')]")
	private WebElement logout;

	public WebElement getLogOutLink() {
		return logout;
	}
}

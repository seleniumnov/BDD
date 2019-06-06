package com.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//li[@class='active'][contains(text(),'Login')]")
	private WebElement loginpage;
	public WebElement getLoginPage() {
		return loginpage;
	}

	@FindBy(how = How.NAME, using = "username")
	private WebElement username;
	public WebElement getUserName() {
		return username;
	}

	@FindBy(how = How.NAME, using = "password")
	private WebElement password;
	public WebElement getPassword() {
		return password;
	}

	@FindBy(how = How.XPATH, using = "//span[@class='checkmark stay']")
	private WebElement termsCheckbox;
	public WebElement getTermsCheckbox() {
		return termsCheckbox;
	}
	@FindBy(how = How.NAME, using = "submit")
	private WebElement loginbtn;
	public WebElement getLogIn() {
		return loginbtn;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='has-error error']")
	private WebElement loginerror;
	public WebElement getLoginError() {
		return loginerror;
	}

	@FindBy(how = How.XPATH, using = "//input[@name='username']")
	private WebElement asmLogin;
	public WebElement getASMLoginUserID() {
		return asmLogin;
	}

	@FindBy(how = How.XPATH, using = "//input[@name='password']")
	private WebElement asmPassword;
	public WebElement getASMPassword() {
		return asmPassword;
	}

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Sign In')]")
	private WebElement asmSignIn;
	public WebElement getASMSignIn() {
		return asmSignIn;
	}

	@FindBy(how = How.XPATH, using = "//input[@name='customerName']")
	private WebElement asmCustomerNameEmailID;
	public WebElement getASMCustomerNameEmailID() {
		return asmCustomerNameEmailID;
	}

	@FindBy(how = How.XPATH, using = "	//input[@name='cartId']")
	private WebElement asmCardIDOrderNum;
	public WebElement getASMCardIDOrderNum() {
		return asmCardIDOrderNum;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='ASM_logo']")
	private WebElement asmLogo;
	public WebElement getASMLogo() {
		return asmLogo;
	}

	@FindBy(how = How.XPATH, using = "	//span[@class='hidden-sm'][contains(text(),'Session')]")
	private WebElement asmStartSession;
	public WebElement getASMStartSession() {
		return asmStartSession;
	}

	@FindBy(how = How.XPATH, using = "//span[@class='terms']")
	private WebElement termandUseMessage;
	public WebElement getTermUseMessage() {
		return termandUseMessage;
	}

	@FindBy(how = How.XPATH, using = "//button[@disabled ='disabled']")
	private WebElement logindisabled;
	public WebElement getLoginDisabled() {
		return logindisabled;
	}

	@FindBy(how = How.XPATH, using = "//span[@class='icon']")
	private WebElement liveChat;
	public WebElement getLiveChat() {
		return liveChat;
	}

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'velaro-header-title')]")
	private WebElement liveChatHeader;
	public WebElement getLiveChatHeader() {
		return liveChatHeader;
	}

	@FindBy(how = How.XPATH, using = "(//div[@class='velaro-form-group']//input[@type='text'])[1]")
	private WebElement liveChatName;
	public WebElement getLiveChatName() {
		return liveChatName;
	}

	@FindBy(how = How.XPATH, using = "//input[contains(@type,'email')]")
	private WebElement liveChatEmail;
	public WebElement getLiveChatEmail() {
		return liveChatEmail;
	}

	@FindBy(how = How.XPATH, using = "(//div[@class='velaro-form-group']//input[@type='text'])[2]")
	private WebElement liveChatCustomerNumber;
	public WebElement getLiveChatCustomerNum() {
		return liveChatCustomerNumber;
	}

	@FindBy(how = How.XPATH, using = "	(//div[@class='velaro-form-group'])[7]//textarea")
	private WebElement liveChatMessage;
	public WebElement getLiveCartMessage() {
		return liveChatMessage;
	}

	@FindBy(how = How.XPATH, using = "//button[contains(@type,'submit')]")
	private WebElement liveChatSubmit;
	public WebElement getLiveCartSubmit() {
		return liveChatSubmit;
	}
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'velaro-confirmation-text')]")
	private WebElement liveChatSubmitConfirmation;
	public WebElement getLiveCartSubmitConfirmation() {
		return liveChatSubmitConfirmation;
	}
		
	@FindBy(how = How.XPATH, using = "//div[@class='velaro-header-top-right']")
	private WebElement liveChatrightdrop;
	public WebElement getLiveDropdown() {
		return liveChatrightdrop;
	}
	
}

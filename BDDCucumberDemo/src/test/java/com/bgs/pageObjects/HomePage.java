package com.bgs.pageObjects;

import java.util.List;

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

	@FindBy(how = How.XPATH, using = "//input[@id='js-site-search-input']")
	private WebElement searchbar;

	public WebElement getSearchBar() {
		return searchbar;
	}

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement searchSubmit;

	public WebElement getSearchSubmit() {
		return searchSubmit;
	}

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign Out')]")
	private WebElement logout;

	public WebElement getLogOutLink() {
		return logout;
	}

	@FindBy(how = How.XPATH, using = "//span[@class='glyphicon glyphicon-search']")
	private WebElement searchclk;

	public WebElement getSearchclick() {
		return searchclk;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='yCmsComponent yComponentWrapper']/div/a/img")
	private WebElement companyLogo;

	public WebElement getCompanyLogo() {
		return companyLogo;
	}

	@FindBy(how = How.XPATH, using = "//a/i[@id='cart-icon']")
	private WebElement cartIcon;

	public WebElement getCartIcon() {
		return cartIcon;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='desktop-nav']/li")
	private List<WebElement> globalBar;

	public List<WebElement> getGlobalBar() {
		return globalBar;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='desktop-nav']")
	private WebElement gnb;

	public WebElement getGlobalNavigationBar() {
		return gnb;
	}
	@FindBy(how = How.XPATH, using = "//div[@class='homepage-banner']//img")
	private WebElement heroBanner;

	public WebElement getHeroBanner() {
		return heroBanner;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='main-category']")
	private WebElement maincategory;

	public WebElement getMainCategory() {
		return maincategory;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='panel-recommeded']")
	private WebElement recommendedcategory;

	public WebElement getRecommendedCategory() {
		return recommendedcategory;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='popular-products']")
	private WebElement popularcategory;

	public WebElement getPopularCategory() {
		return popularcategory;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='menu-subcategory']")
	private WebElement subcategory;

	public WebElement getShopCategory() {
		return subcategory;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='family-brands']")
	private WebElement familybrands;

	public WebElement getFamilyBrands() {
		return familybrands;
	}
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='main-category']/div[@class='category-container']/div/a/span")
	private List<WebElement> simpleBanner;

	public List<WebElement> getSimpleBanner() {
		return simpleBanner;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='menu-subcategory']/div[@class='category-container']/div/a/span")
	private List<WebElement> simpleBanner2;

	public List<WebElement> getSimpleBanner2() {
		return simpleBanner2;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='general-training']")
	private WebElement imageMapcomponent;

	public WebElement getImageMapComponent() {
		return imageMapcomponent;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='general-training']/div/div/h3")
	private WebElement imageMapcomponentText;

	public WebElement getImageMapComponentText() {
		return imageMapcomponentText;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='general-training']/div/div/a")
	private WebElement imageMapcomponentSeecourses;

	public WebElement getImageMapComponentSeeCourses() {
		return imageMapcomponentSeecourses;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='family-brands']/div/div/div/img")
	private List<WebElement> simpleBanner3;

	public List<WebElement> getSimpleBanner3() {
		return simpleBanner3;
	}

	@FindBy(how = How.XPATH, using = "//footer[@id='page-footer']/div/div/div/ul/li")
	private List<WebElement> footerBanner;

	public List<WebElement> getFooterBanner() {
		return footerBanner;
	}

	@FindBy(how = How.XPATH, using = "//select[@id='sortOptions1']")
	private WebElement Selectdrop;

	public WebElement getDropdown() {
		return Selectdrop;
	}
		
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'0 items found for keyword')]")
	private WebElement zeroItemMessage;

	public WebElement getzeroItemMessage() {
		return zeroItemMessage;
	}	
}

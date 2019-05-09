package com.framework.stepDefinitions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

import com.bgs.pageObjects.HomePage;
import com.framework.library.Hooks;
import com.framework.utility.CommonFunctions;
import com.framework.utility.Logger;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebElement;

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
	@Then("^User Should be Able to View the Below Header Components in Home Page:$")
	public void header_components(DataTable dt) throws Throwable {
		try {

			List<List<String>> list = dt.asLists(String.class);
			for (int i = 0; i < list.size(); i++) {
				String string = list.get(i).get(0).trim();

				switch (string) {
				case "CompanyLogo":
					isElementPresent(home.getCompanyLogo(),string);
					break;
				case "CartIcon":
					isElementPresent(home.getCartIcon(),string);
					break;
				case "GlobalNavigationBar": 
					isElementPresent(home.getGlobalNavigationBar(),string);
					break;
				
				}
			}

		} catch (Exception e) {
		}

		
	}
	@Then("^Navigate to Cart Page$")
	public void cart_navigation() {		
		click(home.getCartIcon(), "On Cart");
	}
	
	@Then("^User Should be Able to View the Below Components in Home Page:$")
	public void verify_components_homepage(DataTable dt) throws Throwable {
		try {

			List<List<String>> list = dt.asLists(String.class);
			for (int i = 0; i < list.size(); i++) {
				String string = list.get(i).get(0).trim();

				switch (string) {
				case "CompanyLogo":
					isElementPresent(home.getCompanyLogo(),string);
					break;
				case "CartIcon":
					isElementPresent(home.getCartIcon(),string);
					break;
				case "GlobalNavigationBar": 
					isElementPresent(home.getGlobalNavigationBar(),string);
					break;
				case "HeroBanner":
					isElementPresent(home.getHeroBanner(),string);
					break;
				case "MainCategory": 
					isElementPresent(home.getMainCategory(),string);
					break;
				case "RecommendedProducts": 
					isElementPresent(home.getRecommendedCategory(),string);
					break;
				case "PopularProducts":
					isElementPresent(home.getPopularCategory(),string);
					break;
				case "ShopCategory":
					isElementPresent(home.getShopCategory(),string);
					break;
				case "FamilyBrands":
					isElementPresent(home.getFamilyBrands(),string);
					break;
				}
			}

		} catch (Exception e) {
		}
	}

	@Then("^User Should be Able to View the Below Shop by Category and Family Brands in Home Page:$")
	public void verify_Shop_By_Category_Family_Brands_homepage(DataTable dt) throws Throwable {
		try {

			List<List<String>> list = dt.asLists(String.class);
			for (int i = 0; i < list.size(); i++) {
				String string = list.get(i).get(0).trim();

				switch (string) {
				case "ShopCategory":
					isElementPresent(home.getShopCategory(),string);
					break;
				case "FamilyBrands":
					isElementPresent(home.getFamilyBrands(),string);
					break;
				}
			}

		} catch (Exception e) {
		}
	}

	@Then("^User Should be Able to View the Below Popular and Recommended Products in Home Page:$")
	public void verify_Recomended_Popular_homepage(DataTable dt) throws Throwable {
		try {

			List<List<String>> list = dt.asLists(String.class);
			for (int i = 0; i < list.size(); i++) {
				String string = list.get(i).get(0).trim();

				switch (string) {
				case "RecommendedProducts": 
					isElementPresent(home.getRecommendedCategory(),string);
					break;
				case "PopularProducts":
					isElementPresent(home.getPopularCategory(),string);
					break;
				}
			}

		} catch (Exception e) {
		}
	}

	@Then("^User Should be Able to View the Below Six image Banners and Hero Banner in Home Page:$")
	public void verify_Six_Banners_homepage(DataTable dt) throws Throwable {
		try {

			List<List<String>> list = dt.asLists(String.class);
			for (int i = 0; i < list.size(); i++) {
				String string = list.get(i).get(0).trim();

				switch (string) {
				case "MainCategory": 
					isElementPresent(home.getMainCategory(),string);
					break;
				case "HeroBanner":
					isElementPresent(home.getHeroBanner(),string);
					break;
				
				}
			}

		} catch (Exception e) {
		}
	}

	@Then("^I Search for the Product:$")
	public void search_functionality(DataTable dt) {
		readData(dt);
		try {
//		if (home.getSearchBar().isDisplayed()) {
			scrollIntoView(home.getSearchBar());
			click(home.getSearchBar(),"Search Field");
			input(home.getSearchBar(), testdata.get("searchProduct"), "Search Field");
			waitForSomeTime(2000);
			click(home.getSearchSubmit(), "Search Icon");
			waitForJStoLoad();
//		}
		}catch(Exception e) {
			Assert.fail("Not able to search product");
		}
	}

	@And("^It Should Display Message Zero Results Found$")
	public void zeroResult() {
		if (!home.getzeroItemMessage().isDisplayed()) {
			Logger.logTestInfo("Message Zero Results Found is not Displayed");
			soft.fail("Search Result not Working");
		}else {
			Logger.logTestInfo("Message Zero Results Found is Displayed");
		}
	}
}





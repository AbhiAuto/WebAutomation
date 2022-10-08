package com.test.stepdef;

import org.openqa.selenium.support.PageFactory;

import com.scripted.webutil.BrowserDriver;
import com.scripted.webutil.WaitHelper;
import com.test.pages.CartPage;
import com.test.pages.HomePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	HomePage homepage;
	CartPage cartpage;

	@Before
	public void initializeBrowser() {
		BrowserDriver.funcGetBrowserDriver();
		BrowserDriver.launchUrl("https://cms.demo.katalon.com");
		System.out.println("Successfully launched the browser and navigated to the URL");
		homepage = PageFactory.initElements(BrowserDriver.getDriver(), HomePage.class);
		cartpage = PageFactory.initElements(BrowserDriver.getDriver(), CartPage.class);

	}

	@Given("I add four random items to my cart")
	public void i_add_four_random_items_to_my_cart() {
		homepage.additems();
	}

	@When("I view my cart")
	public void i_view_my_cart() {
		homepage.clickOnViewCart();
	}

	@Then("I find four items listed in my cart")
	public void i_find_four_items_listed_in_my_cart() {
		if (cartpage.productCount() == 4) {
			System.out.println("Four Products added to the cart successfully");
		}
	}

	@When("I search for lowest price item")
	public void i_search_for_lowest_price_item() {
		cartpage.searchLowestPriceItem();
	}

	@When("I am able to remove the lowest price item from my cart")
	public void i_am_able_to_remove_the_lowest_price_item_from_my_cart() {
		cartpage.removeLowestPriceItem();
	}

	@Then("I am able to verify three items in my cart")
	public void i_am_able_to_verify_three_items_in_my_cart() {
		if (cartpage.productCount() == 3) {
			System.out.println("Verification success, only three products available in the cart");
		}
	}
	
	@After
	public void ClearBrowserInstance() {
		BrowserDriver.ClearInstance();
	}
}

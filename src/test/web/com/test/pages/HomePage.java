package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.scripted.webutil.WebHandlers;

public class HomePage {

	WebDriver driver;

	@FindBy(css = "img[src*='wp-content/uploads/2019/08/T_5_front-300x300.jpg']")
	private WebElement flyingninjaimage;
	@FindBy(css = "a[aria-label='Add “Flying Ninja” to your cart']")
	private WebElement flyingninja;

	@FindBy(css = "img[src*='wp-content/uploads/2019/08/T_7_front-300x300.jpg']")
	private WebElement happynija1image;
	@FindBy(xpath = "(//a[@aria-label='Add “Happy Ninja” to your cart'])[1]")
	private WebElement happynija1;

	@FindBy(css = "img[src*='wp-content/uploads/2019/08/hoodie_4_front-300x300.jpg']")
	private WebElement happynija2image;
	@FindBy(xpath = "(//a[@aria-label='Add “Happy Ninja” to your cart'])[2]")
	private WebElement happynija2;

	@FindBy(css = "img[src*='wp-content/uploads/2019/08/T_5_front-300x300.jpg']")
	private WebElement ninjasilhouetteimage;
	@FindBy(css = "a[aria-label='Add “Ninja Silhouette” to your cart']")
	private WebElement ninjasilhouette;

	@FindBy(xpath = "(//a[contains(text(),'View cart')])[1]")
	private WebElement viewcart;

	public HomePage(WebDriver cDriver) {
		this.driver = cDriver;
	}

	public void additems() {

		// WebHandlers.scrollToElement(flyingninjaimage);
		WebHandlers.mouseOver(flyingninjaimage);
		WebHandlers.Click(flyingninja);
		WebHandlers.mouseOver(happynija1image);
		WebHandlers.Click(happynija1);
		WebHandlers.mouseOver(happynija2image);
		WebHandlers.Click(happynija2);
		WebHandlers.mouseOver(ninjasilhouetteimage);
		WebHandlers.Click(ninjasilhouette);
	}

	public void clickOnViewCart() {
		WebHandlers.Click(viewcart);
	}
}

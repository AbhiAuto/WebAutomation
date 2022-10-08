package com.test.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.scripted.webutil.BrowserDriver;
import com.scripted.webutil.WaitHelper;
import com.scripted.webutil.WebHandlers;

public class CartPage {

	WebDriver driver;
	Integer minPriceObjectCol;

	public CartPage(WebDriver cDriver) {
		this.driver = cDriver;
	}

	@FindBy(xpath = "//*[@id='post-8']/div/div/form/table")
	private WebElement productTable;

	@FindBy(xpath = "div[class*='woocommerce-message']")
	private WebElement message;

	public int productCount() {
		List<WebElement> productList = productTable.findElements(By.xpath("//tr/td[@data-title='Product']"));
		int productCount = productList.size();
		return productCount;
	}

	public void searchLowestPriceItem() {
		try {

			List<WebElement> priceList = productTable.findElements(By.xpath("//tr/td[@data-title='Price']/span"));
			HashMap<Double, Integer> priceMap = new HashMap<Double, Integer>();
			int i = 1;
			for (WebElement ele : priceList) {

				String productPrice = ele.getAttribute("innerText");
				productPrice = productPrice.replace("$", "");
				// System.out.println(productPrice);
				double price = Double.parseDouble(productPrice);
				priceMap.put(price, i++);
			}

			ArrayList<Double> doublePrice = new ArrayList<>();

			for (Entry<Double, Integer> entry : priceMap.entrySet()) {
				doublePrice.add(entry.getKey());
			}

			Collections.sort(doublePrice);

			Object minPrice = doublePrice.get(0);
			System.out.println("Lowest item price is :" + minPrice);
			minPriceObjectCol = priceMap.get(minPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeLowestPriceItem() {

		try {

			WebElement Ele = driver.findElement(By.xpath(
					"//tr[@class='woocommerce-cart-form__cart-item cart_item'][" + minPriceObjectCol + "]/td[1]/a"));
			WebHandlers.Click(Ele);
			System.out.println("Lowest price item removed successfully");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.scripted.webutil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebHandlers {

	private static Logger LOGGER = LoggerFactory.getLogger(WebHandlers.class);
	private static WebDriver vDriver = BrowserDriver.getDriver();

	public static By webElementToBy(WebElement webEle) {
		try {
			String webEleString = webEle.toString();
			String flag = webEle.toString();
			flag = flag.substring(1, flag.length() - 1);
			String[] data = null;
			if (flag.contains("DefaultElementLocator"))
				data = flag.split("By.")[1].split(": ");
			else
				data = flag.split(" -> ")[1].split(": ");

			String locator = data[0];
			String term = data[1];

			switch (locator) {
			case "xpath":
				return By.xpath(term);
			case "css selector":
				return By.cssSelector(term);
			case "id":
				return By.id(term);
			case "tag name":
				return By.tagName(term);
			case "name":
				return By.name(term);
			case "link text":
				return By.linkText(term);
			case "class name":
				return By.className(term);

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error  while converting by type to webelement" + e);
		}
		return (By) webEle;
	}

	public static void scrollToElement(WebElement locator) {
		try {
			By elelocator = webElementToBy(locator);
			WebElement element = BrowserDriver.getDriver().findElement(elelocator);
			Coordinates coordinate = ((Locatable) element).getCoordinates();
			coordinate.onPage();
			coordinate.inViewPort();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while performing  scrollToElement action for  locator :" + locator
					+ "Exception :" + e);
		}
	}
	
	

	public static void Click(WebElement objLocator) {
		WaitHelper.waitForElement(objLocator);
		objLocator.click();
		BrowserDriver.pageWait();
	}

	public static void mouseOver(WebElement flyingninjaimage) {
		 Actions actions = new Actions(BrowserDriver.getDriver());
		 actions.moveToElement(flyingninjaimage).perform();
	}

}

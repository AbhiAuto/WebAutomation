package com.scripted.webutil;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitHelper {

	private static Logger LOGGER = LoggerFactory.getLogger(WaitHelper.class);

	protected static int getElementTimeout() {
		return 10;
	}

	private static long getPollingTimeoutInMilliSeconds() {
		return 30;
	}

	public static void waitForElement(WebElement objLocator) {

		new FluentWait<WebDriver>(BrowserDriver.getDriver()).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class)
				.until(new Function<WebDriver, Boolean>() {
					boolean flag = false;

					public Boolean apply(WebDriver t) {
						try {
							By byEle = WebHandlers.webElementToBy(objLocator);
							waitForPresence(byEle, getElementTimeout());
							waitForNotStale(byEle, getElementTimeout());
							scrollToElement(byEle);
							waitForVisibleble(byEle, getElementTimeout());
							waitForClickable(byEle, getElementTimeout());
							flag = true;
						} catch (Exception e) {
							e.printStackTrace();
							LOGGER.error("Error occurred while waiting for loading the locator :" + objLocator
									+ "Exception :" + e);

						}
						return flag;
					}
				});

	}

	public static void waitForPresence(By objLocator, int time) {
		try {
			WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(), time, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(objLocator));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void waitForClickable(By byEle, int time) {
		try {
			WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(), time, getPollingTimeoutInMilliSeconds());
			wait.until(ExpectedConditions.elementToBeClickable(byEle));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitForVisibleble(By byEle, int time) {
		try {
			WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(), time, getPollingTimeoutInMilliSeconds());
			wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitForNotStale(By byEle, int time) {
		try {
			WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(), time, getPollingTimeoutInMilliSeconds());
			wait.until(stalenessOf(byEle));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void scrollToElement(By locator) {
		try {
			WebElement element = BrowserDriver.getDriver().findElement(locator);
			Coordinates coordinate = ((Locatable) element).getCoordinates();
			coordinate.onPage();
			coordinate.inViewPort();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while performing  scrollToElement action for  locator :" + locator
					+ "Exception :" + e);
		}
	}

	public static ExpectedCondition<Boolean> stalenessOf(By byEle) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver ignored) {
				try {
					// Calling any method forces a staleness check
					ignored.findElement(byEle).isEnabled();
					return true;
				} catch (StaleElementReferenceException expected) {
					return false;
				}
			}

			@Override
			public String toString() {
				return String.format("element (%s) to become stale", byEle);
			}
		};
	}

}

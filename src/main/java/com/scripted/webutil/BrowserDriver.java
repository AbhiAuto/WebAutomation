package com.scripted.webutil;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.impl.SLF4JLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriver {

	private static Logger LOGGER = LoggerFactory.getLogger(BrowserDriver.class);
	public static WebDriver BrowserDriver = null;

	// To fetch the browser driver
	public static WebDriver funcGetBrowserDriver() {
		try {
			WebDriverManager.chromedriver().setup();
			BrowserDriver = new ChromeDriver();

			BrowserDriver.manage().deleteAllCookies();
			BrowserDriver.manage().window().maximize();
			BrowserDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			BrowserDriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
			BrowserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LOGGER.info("Browser launched sucessfully");
		} catch (Exception e) {
			LOGGER.error("Failure in Browser launch" + e);

		}
		return BrowserDriver;

	}

	// Launch URL and wait until the page loads completely
	public static void launchUrl(String strUrl) {
		try {
			BrowserDriver.get(strUrl);

			BrowserDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			BrowserDriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
			BrowserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			LOGGER.info("Navigated to the URL successfully");
		} catch (Exception e) {
			LOGGER.error("Launch URL unsucessful" + e);
		}
	}

	public static WebDriver getDriver() {

		return BrowserDriver;
	}

	public static void pageWait() {
		BrowserDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		BrowserDriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		BrowserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public static void ClearInstance() {
		BrowserDriver.quit();
	}

}

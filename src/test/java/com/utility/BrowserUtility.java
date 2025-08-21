package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());


	public WebDriver getDriver() {
		return driver.get();
	}
	
	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); //initialize the instance variable driver
	}
	
	public BrowserUtility(String browserName) {
		if(browserName.equalsIgnoreCase("Chrome")) {
			logger.info("Launching the browser" + browserName);
			driver.set(new ChromeDriver());
		}
		else if (browserName.equalsIgnoreCase("Firefox")) {
			logger.info("Launching the browser" + browserName);
			driver.set(new FirefoxDriver());
		}
		else {
			logger.info("Invalid Browser Name.. Please select Chrome or Firefox only");
			System.out.println("Invalid Browser Name.. Please select Chrome or Firefox only");
		}
	}
	
	public BrowserUtility(Browser browserName) {
		if(browserName == Browser.CHROME) {
			logger.info("Launching the browser" + browserName);
			driver.set( new ChromeDriver());
		}
		else if (browserName ==Browser.FIREFOX) {
			logger.info("Launching the browser" + browserName);
			driver.set(new FirefoxDriver());
		}
		
	}
	
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching the browser" + browserName);
		if(browserName == Browser.CHROME) {
			if(isHeadless) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new"); //headless mode
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--remote-allow-origins=*"); 
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-software-rasterizer");
			options.addArguments("window-size=1920,1080");
			driver.set( new ChromeDriver(options));
		}
			else {
				driver.set( new ChromeDriver());
			}
		}
		else if (browserName ==Browser.FIREFOX) {
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				driver.set(new FirefoxDriver(options));
			}
			else {
				driver.set(new FirefoxDriver());
			}
		}
			
			else if (browserName ==Browser.EDGE) {
				if(isHeadless) {
					EdgeOptions options = new EdgeOptions();
					options.addArguments("--headless=old");
					options.addArguments("diable-gpu");
					driver.set(new EdgeDriver(options));
				}
				else {
					driver.set(new EdgeDriver());
				}
		}
		
	}
	
	public void goToWebsite(String url) {
		logger.info("Navigating to website" + url);
		driver.get().get(url);
	}
	
	public void maximizeWindow() {
		logger.info("Maximizing the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = driver.get().findElement(locator); // find the element
		logger.info("Clicking on the element");
		element.click();
	}
	
	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now enter text" + textToEnter);
		element.sendKeys(textToEnter);
	}
	
	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now returning the visisble text" + element.getText());
		return element.getText();
	}
	
	public String takeScreenshot(String name) {
		TakesScreenshot screenshot =(TakesScreenshot) driver.get();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timestamp = format.format(date);
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		String path = "./screenshots/" + name + " - " + timestamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	public void quit() {
		driver.get().quit();
	}
	
}

package com.ui.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.constants.Browser.*;

import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.utility.BrowserUtility;

public class LoginTest2 {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver(); // launch a browser
		
		HomePage homePage = new HomePage(CHROME, false);
		homePage.maximizeWindow();
		
		LoginPage loginPage = homePage.goToLoginPage();
		loginPage.doLoginWith("heraje7815@blaxion.com", "Mark123");
	
		

}
}

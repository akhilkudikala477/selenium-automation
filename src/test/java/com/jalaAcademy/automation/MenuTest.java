package com.jalaAcademy.automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jalaAcademy.Automation.HomePage;
import com.jalaAcademy.Automation.LoginPage;
import com.jalaAcademy.Automation.MenuPage;
import com.jalaAcademy.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class MenuTest extends BaseTest {
	protected static WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	MenuPage menuPage;

	@Epic("Application Navigation")
	@Feature("Main Menu Functionality")
	@BeforeClass
	@Parameters({ "browser", "siteURL" })
	public void setup(String browser, String siteURL) throws InterruptedException {
		log.info("Starting of setup method");

		driver = createDriver(browser);
		menuPage = new MenuPage(driver);
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		driver.get(siteURL);

		loginPage.enterEmail(testDataProp.getProperty("valid.username"));
		loginPage.enterPassword(testDataProp.getProperty("valid.password"));
		loginPage.clickOnRememberMe();
		loginPage.clickOnLoginButton();
		homePage.clickOnMoreButton(driver);
		Thread.sleep(3000);
		homePage.clickOnMenuButton();
		log.info("Ending of setup method");

	}

	@Test(/* priority = 1, */ description = "Verify that all menu items redirect to the correct modules")
	@Story("As a user, I should be able to navigate to modules using the main menu")
	@Description("Verify that all menu items redirect to the correct modules")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Akhil")
	public synchronized void A_testMenu() {
		log.info("Starting of testMenu method");

		menuPage.clickOnSubMenuButton();
	//	menuPage.MouseHoverOnTestingLabel(driver);
		//menuPage.ClickOnSeleniumButton();
		log.info("Ending of testMenu method");

	}

	@AfterClass
	public synchronized void quit() throws InterruptedException {
		quitDriver();
	}
}

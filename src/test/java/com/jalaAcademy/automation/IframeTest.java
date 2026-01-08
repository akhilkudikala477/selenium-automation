package com.jalaAcademy.automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jalaAcademy.Automation.HomePage;
import com.jalaAcademy.Automation.IframePage;
import com.jalaAcademy.Automation.LoginPage;
import com.jalaAcademy.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("UI Interaction")
@Feature("iFrame Handling")
public class IframeTest extends BaseTest {

	protected static WebDriver driver;
	LoginPage loginPage;
	
	
	HomePage homePage;

	IframePage iframePage;

	@BeforeClass
	@Parameters({"browser","siteURL"})
	public void setup(String browser, String siteURL) throws InterruptedException {
		log.info("Starting of setup method");

		driver =createDriver(browser);

		iframePage = new IframePage(driver);
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
		homePage.clickOnIframeButton();
		log.info("Ending of setup method");

	}

	@Test(/* priority = 1, */ description = "Verify that the application allows switching to iframe and performing actions inside it")
	@Story("As a user, I should be able to interact with elements inside an iframe")
	@Description("Verify that the application allows switching to iframe and performing actions inside it")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Akhil")
	public synchronized void A_testIframe() {
		log.info("Starting of testIframe method");

		String hdrIframeOne = iframePage.getFrameOneText();
		Assert.assertEquals(hdrIframeOne, expectedAssertion.getProperty("iframe.one"));

		String hdrIframeTwo = iframePage.getFrameTwoText();
		Assert.assertEquals(hdrIframeTwo,expectedAssertion.getProperty("iframe.two"));

		String hdrWelcomeToJalaAcademy = iframePage.getWelcomeToJalaAcademyText(driver);
		Assert.assertEquals(hdrWelcomeToJalaAcademy, expectedAssertion.getProperty("header.tittle"));
		log.info("Ending of testIframe method");


	}

	@AfterClass
	public synchronized void quit() throws InterruptedException {
		quitDriver();
	}

}

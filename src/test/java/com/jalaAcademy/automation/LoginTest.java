package com.jalaAcademy.automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jalaAcademy.Automation.LoginPage;
import com.jalaAcademy.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("User Authentication")
@Feature("User Login Validation")
public class LoginTest extends BaseTest {
	private WebDriver driver;

	LoginPage loginPage;

	@BeforeClass
	@Parameters({ "browser", "siteURL" })
	public void setup(String browser, String siteURL) {
		log.info("Starting of setup method");

		driver = createDriver(browser);
		loginPage = new LoginPage(driver);
		driver.get(siteURL);
		log.info("Ending of setup method");

	}

	@Test(/* priority = 1, */ description = "Verify that the user can log in with vaild UserName and Invalid Password")
	@Description("Verify that the user can log in with vaild UserName and Invalid Password")
	@Story("\"As a user, I should  Not be able to log in securely")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("akhil kudikala")
	public void A_TestInvaildLogin() {
		log.info("Starting of TestInvaildLogin method");

		loginPage.enterEmail(testDataProp.getProperty("valid.username"));
		loginPage.enterPassword(testDataProp.getProperty("invalid.password"));
		loginPage.clickOnRememberMe();
		loginPage.clickOnLoginButton();
		log.info("Ending of TestInvaildLogin method");

	}

	@Test(/* priority = 2, */ description = "Verify that the user can log in with Invalid UserName and Password")
	@Description("Verify that the user can log in with Invalid UserName and Password")
	@Story("\"As a user, I should  Not be able to log in securely")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("akhil kudikala")
	public void B_TestInvaildLogin2() {
		log.info("Starting of TestInvaildLogin2 method");

		loginPage.enterEmail(testDataProp.getProperty("invalid.username"));
		loginPage.enterPassword(testDataProp.getProperty("invalid.password"));
		loginPage.clickOnRememberMe();
		loginPage.clickOnLoginButton();
		log.info("Ending of TestInvaildLogin2 method");

	}

	@Test(/* priority = 3, */ description = "Verify that the user can log in without credentials")
	@Description("Verify that the user can log in without credentials")
	@Story("\"As a user, I should  Not be able to log in securely")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("akhil kudikala")
	public void C_TestInvaildLogin3() {
		log.info("Starting of TestInvaildLogin3 method");

		loginPage.enterEmail(testDataProp.getProperty("empty.username"));
		loginPage.enterPassword(testDataProp.getProperty("empty.password"));
		loginPage.clickOnRememberMe();
		loginPage.clickOnLoginButton();
		log.info("Ending of TestInvaildLogin3 method");

	}

	@Test(/* priority = 4, */ description = "Verify that the user can log in with valid credentials")
	@Description("Verify that the user can log in with valid credentials")
	@Story("\"As a user, I should  be able to log in securely")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("akhil kudikala")
	public void D_testVaildLogin4() {
		log.info("Starting of testVaildLogin4 method");

		
		loginPage.enterEmail(testDataProp.getProperty("valid.username"));
		loginPage.enterPassword(testDataProp.getProperty("valid.password"));
		loginPage.clickOnRememberMe();
		loginPage.clickOnLoginButton();
	//
		//Assert.fail();
		log.info("Ending of testVaildLogin4 method");

	}

	@AfterClass
	public synchronized void quit() throws InterruptedException {
		quitDriver();
	}
}

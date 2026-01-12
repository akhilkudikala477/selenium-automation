package com.jalaAcademy.automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jalaAcademy.Automation.EmployeeDetails;
import com.jalaAcademy.Automation.LoginPage;
import com.jalaAcademy.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Employee Management")
@Feature("Add Employee Functionality")
public class EmployeeDetailsTest extends BaseTest {

	private WebDriver driver;
	LoginPage loginPage;
	EmployeeDetails employeeDetails;

	@BeforeClass
	@Parameters({ "browser", "siteURL" })
	public void setup(String browser, String siteURL) {
		log.info("Starting of setup method");

		driver = createDriver(browser);
		employeeDetails = new EmployeeDetails(driver);
		loginPage = new LoginPage(driver);
		driver.get(siteURL);
		loginPage.enterEmail(testDataProp.getProperty("valid.username"));
		loginPage.enterPassword(testDataProp.getProperty("valid.password"));
		loginPage.clickOnRememberMe();
		loginPage.clickOnLoginButton();
		
		log.info("Ending of setup method");
	}

	@Test(/* priority = 1, */description = "Verify that admin can create a new employee with valid mandatory information")
	@Story("As an admin, I should be able to add new employee details successfully")
	@Description("Verify that admin can create a new employee with valid mandatory information")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Akhil")
	public synchronized void A_testVaildEmployeeDetails() throws InterruptedException {
		log.info("Starting of testVaildEmployeeDetails method");

		String txtheader = employeeDetails.getWelcomeToJalaAcademy();

		Assert.assertEquals(txtheader, expectedAssertion.getProperty("home.page.title"));

		employeeDetails.clickOnEmployeeButton();
		employeeDetails.clickOnCreateButton();
		employeeDetails.enterFirstName(testDataProp.getProperty("first.name"));
		employeeDetails.enterFirstName(testDataProp.getProperty("last.name"));
		employeeDetails.enterEmailId(testDataProp.getProperty("email.id"));
		employeeDetails.enterMobileNumber(testDataProp.getProperty("mobile.number"));
		employeeDetails.enterDateOfBirth(testDataProp.getProperty("date.of.birth"));
		employeeDetails.clickOnEmployeeButton();
		employeeDetails.enterAddress(testDataProp.getProperty("address"));
		employeeDetails.clickOnSelectCity();
		employeeDetails.clickOnIndia();
		Thread.sleep(3000);
		employeeDetails.clickOnSelectCity();
		employeeDetails.clickOnHyderabad();
		employeeDetails.clickOnQaAutomation();
		employeeDetails.clickOnIndia();
		
		log.info("Ending of testVaildEmployeeDetails method");
	}

	@AfterClass
	public synchronized void quit() throws InterruptedException {
		quitDriver();
	}
}

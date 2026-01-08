package com.jalaAcademy.automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jalaAcademy.Automation.EmployeeDetails;
import com.jalaAcademy.Automation.LoginPage;
import com.jalaAcademy.Automation.SearchEmployee;
import com.jalaAcademy.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Employee Management")
@Feature("Search Employee Functionality")
public class SearchEmployeeTest extends BaseTest {
	
	protected static WebDriver driver;
	LoginPage loginPage;
	EmployeeDetails employeeDetails;
	SearchEmployee searchEmployee;
	
	@BeforeClass
	@Parameters({"browser","siteURL"})
	public synchronized void setup(String browser, String siteURL) {
		log.info("Starting of setup method");

		driver =createDriver(browser);
		loginPage =new LoginPage(driver);
		employeeDetails = new EmployeeDetails(driver);
		searchEmployee = new SearchEmployee(driver);
		driver.get(siteURL);

		
		loginPage.enterEmail(testDataProp.getProperty("valid.username"));
		loginPage.enterPassword(testDataProp.getProperty("valid.password"));
		loginPage.clickOnRememberMe();
		loginPage.clickOnLoginButton();
		employeeDetails.clickOnEmployeeButton();
		log.info("Ending of setup method");


	}

	@Test(/*priority = 1,*/description="Verify that the search employee feature returns accurate results based on search input")
			@Story("As an admin, I should be able to search employees by name or email")
	@Description("Verify that the search employee feature returns accurate results based on search input")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Akhil")
	
	public synchronized void A_testSearchEmployee() throws Exception {
		log.info("Starting of testSearchEmployee method");

		Thread.sleep(3000);
		searchEmployee.clickOnSearch();
		searchEmployee.enterEmployeeName(testDataProp.getProperty("employee.name"));
		searchEmployee.enterMobileNumber(testDataProp.getProperty("mobile.number2"));
		searchEmployee.clickOnSearch1();
		Thread.sleep(3000);
		//Assert.fail();
		log.info("Ending of testSearchEmployee method");

		
	
	}
	@AfterClass
	public synchronized void quit() throws InterruptedException {
		quitDriver();
	}

}

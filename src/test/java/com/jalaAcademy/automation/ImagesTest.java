package com.jalaAcademy.automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jalaAcademy.Automation.HomePage;
import com.jalaAcademy.Automation.ImagesPage;
import com.jalaAcademy.Automation.LoginPage;
import com.jalaAcademy.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class ImagesTest extends BaseTest {
	protected static WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	ImagesPage imagesPage;
	@BeforeClass
	@Parameters({ "browser", "siteURL" })
	public void setup(String browser, String siteURL) throws Exception {
		log.info("Starting of setup method");
		
		driver = createDriver(browser);
		imagesPage = new ImagesPage(driver);
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		driver.get(siteURL);
		loginPage.enterEmail(testDataProp.getProperty("valid.username"));
		loginPage.enterPassword(testDataProp.getProperty("valid.password"));
		loginPage.clickOnRememberMe();
		loginPage.clickOnLoginButton();
		homePage.clickOnMoreButton(driver);
		Thread.sleep(3000);
		homePage.clickOnImageButton();
		
		log.info("Ending of setup method");
	}

	@Test(/*priority = 1,*/ description = "Verify that the application allows switching to iframe and performing actions inside it")
	@Story("As a user, I should be able to interact with elements inside an iframe")
	@Description("Verify that the application allows switching to iframe and performing actions inside it")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Akhil")
	public synchronized void A_testUploadImages() throws InterruptedException {
		log.info("Starting of testUploadImages method");

		imagesPage.uploadImage(testDataProp.getProperty("upload.image"));
		Thread.sleep(2000);
		imagesPage.clickOnUploadButton();
		String labelUploadedImage = imagesPage.getImageLabel();
//		Assert.assertEquals(labelUploadedImage, expectedAssertion.getProperty("image.page.label"));
	
		log.info("Ending of testUploadImages method");
	}
	@AfterClass
	public synchronized void quit() throws InterruptedException {
		quitDriver();
	}

}




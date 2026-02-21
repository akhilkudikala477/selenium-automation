package com.jalaAcademy.automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jalaAcademy.Automation.LoginPage;
import com.jalaAcademy.base.BaseTest;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Demo")
@Feature("Intentional Failure Demo")
public class IntentionalFailTest extends BaseTest {
	private WebDriver driver;

	@BeforeClass
	@Parameters({ "browser", "siteURL" })
	public void setup(String browser, String siteURL) {
		log.info("Starting of setup method");

		driver = createDriver(browser);
		driver.get(siteURL);
		log.info("Ending of setup method");

	}
    @Test(description = "A purposeful failing test to demo debugging in CI. Do NOT fix — we will show the fix live.")
    @Description("This test will intentionally assert an incorrect title to show Allure + screenshot + logs on CI.")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldFailOnPurpose_showingHowToDebug() {
        LoginPage login = new LoginPage(driver);

        // Use your real site or the demo site you run in CI
       

        // INTENTIONAL incorrect expectation to force a failure
        String expectedTitle = "THIS_TITLE_WILL_NEVER_MATCH - DEMO will FAIL";
        String actualTitle = driver.getTitle();

        // Attach to Allure for better debug context
        Allure.addAttachment("expectedTitle", expectedTitle);
        Allure.addAttachment("actualTitle", actualTitle);

        // The assertion will fail in CI and trigger the TestListener screenshot & stacktrace
        Assert.assertEquals(actualTitle, expectedTitle, "Intentional mismatch to show framework debug flow");
    }
    @AfterClass
	public synchronized void quit() throws InterruptedException {
		quitDriver();
	}
}
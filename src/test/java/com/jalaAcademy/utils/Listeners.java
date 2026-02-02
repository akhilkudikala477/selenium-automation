package com.jalaAcademy.utils;

import java.io.ByteArrayInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jalaAcademy.base.BaseTest;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
public class Listeners extends AllureTestNg implements ITestListener {
//public class Listeners implements ITestListener {
	protected Logger log = LogManager.getLogger(Listeners.class);

	@Override
	public void onTestStart(ITestResult result) {
		log.info("Test Started  : " + result.getName());
	}

	@Override

	public void onTestSuccess(ITestResult result) {
		log.info("Test Passed" + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

		Object testClass = result.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriver();

		if (driver != null) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

				Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestFinish(ITestResult result) {
		log.info("Test Finish " + result.getName());
	}

}

package com.jalaAcademy.Automation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jalaAcademy.page.BasePage;

public class IframePage extends BasePage {

	@FindBy(xpath = "//h3[text()='Frame One']")
	private WebElement hdrFrameOne;

	@FindBy(xpath = "//h3[text()='Frame Two']")
	private WebElement hdrFrameTwo;

	@FindBy(xpath = "//h1[text()='Welcome to JALA Academy']")
	private WebElement hdrWelcomeToJalaAcademy;

	@FindBy(xpath = "//iframe[@id='iframe2']")
	private WebElement iframe2;

	public IframePage(WebDriver driver) {
	    super(driver, Duration.ofSeconds(30));
	    log.info("Starting of HomePage constructor");

	    PageFactory.initElements(driver, this);

	    log.info("Ending of HomePage constructor");
	}

	public String getFrameOneText() {
		log.info("Starting of getFrameOneText method");
		log.info("Ending of getFrameOneText method");

		return getText(hdrFrameOne);
	}

	public String getFrameTwoText() {
		log.info("Starting of getFrameTwoText method");
		log.info("Ending of getFrameTwoText method");

		return getText(hdrFrameTwo);
	}

	public String getWelcomeToJalaAcademyText(WebDriver driver) {
		log.info("Starting of getWelcomeToJalaAcademyText method");
		
		driver.switchTo().frame(1);

		log.info("Ending of getWelcomeToJalaAcademyText method");

		// driver.switchTo().frame(iframe2);
		return getText(hdrWelcomeToJalaAcademy);
	}
}

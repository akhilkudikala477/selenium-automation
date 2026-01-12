package com.jalaAcademy.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class BasePage {
	protected static Logger log = LogManager.getLogger(BasePage.class);

	public void click(WebElement element) {
		log.info("Starting of click method");

		element.click();

		log.info("Ending of click method");
	}

	public void sendKeys(WebElement element, String text) {
		log.info("Starting of sendKeys method");

		element.clear();
		element.sendKeys(text);

		log.info("Ending of sendKeys method");
	}

	public boolean isDisplayed(WebElement element) {
		log.info("Starting of isDisplayed method");
		log.info("Ending of isDisplayed method");

		return element.isDisplayed();
	}

	public String getText(WebElement element) {
		log.info("Starting of getText method");
		log.info("Ending of getText method");

		return element.getText();
	}
}
package com.jalaAcademy.Automation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jalaAcademy.page.BasePage;

public class MenuPage extends BasePage {

	@FindBy(xpath = "(//a[@data-toggle='tab'])[2]")
	private WebElement btnSubMenus;

	@FindBy(xpath = "(//a[@class='dropbtn'])[1]")
	private WebElement lblTesting;

	@FindBy(xpath = "(//a[@id='selbtn'])[1]")
	private WebElement btnSelenium;

	public MenuPage(WebDriver driver) {
	    super(driver, Duration.ofSeconds(30));
	    log.info("Starting of HomePage constructor");

	    PageFactory.initElements(driver, this);

	    log.info("Ending of HomePage constructor");
	}
	
	public void clickOnSubMenuButton() {
		log.info("Starting of clickOnSubMenuButton method");

		click(btnSubMenus);
		
		log.info("Ending of clickOnSubMenuButton method");
	}
	public void mouseHoverOnTestingLabel(WebDriver Driver) {
		log.info("Starting of mouseHoverOnTestingLabel method");

		Actions act = new Actions(Driver);
		act.moveToElement(lblTesting).perform();
		
		log.info("Ending of mouseHoverOnTestingLabel method");
	}
	
	public void clickOnSeleniumButton() {
		log.info("Starting of clickOnSeleniumButton method");

		click(btnSelenium);
		
		log.info("Ending of clickOnSeleniumButton method");
	}
}

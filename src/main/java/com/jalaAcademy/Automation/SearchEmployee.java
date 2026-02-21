package com.jalaAcademy.Automation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jalaAcademy.page.BasePage;

public class SearchEmployee extends BasePage {

	@FindBy(xpath = "//i[@class='fa fa-search']")
	private WebElement btnSearch;

	@FindBy(xpath = "//input[@id='Name']")
	private WebElement inpEmployeeName;

	@FindBy(xpath = "//input[@id='MobileNo']")
	private WebElement inpMobileNumber;

	@FindBy(xpath = "//button[@id='btnSearch']")
	private WebElement btnSearch1;

	public SearchEmployee(WebDriver driver) {
	    super(driver, Duration.ofSeconds(30));
	    log.info("Starting of HomePage constructor");

	    PageFactory.initElements(driver, this);

	    log.info("Ending of HomePage constructor");
	}

	public void clickOnSearch() {
		log.info("Starting of clickOnSearch method");

		click(btnSearch);

		log.info("Ending of clickOnSearch method");
	}

	public void enterEmployeeName(String Employee) {
		log.info("Starting of enterEmployeeName method");

		sendKeys(inpEmployeeName, Employee);

		log.info("Ending of enterEmployeeName method");
	}

	public void enterMobileNumber(String MobileNumber) {
		log.info("Starting of enterPassword method");

		sendKeys(inpMobileNumber, MobileNumber);

		log.info("Ending of enterPassword method");
	}

	public void clickOnSearch1() {
		log.info("Starting of clickOnSearch1 method");

		click(btnSearch1);

		log.info("Ending of setup method");
	}
}
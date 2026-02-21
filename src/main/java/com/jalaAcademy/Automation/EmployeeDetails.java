package com.jalaAcademy.Automation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jalaAcademy.page.BasePage;

public class EmployeeDetails extends BasePage {
	@FindBy(xpath = "//h1[text()='Welcome to JALA Academy']")
	private WebElement txtHeader;

	@FindBy(xpath = "//i[@class='fa fa-users']/..")
	private WebElement btnEmployee;

	@FindBy(xpath = "//a[@href='/Employee/Create']")
	private WebElement btnCreate;

	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement inpFirstName;

	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement inpLastName;

	@FindBy(xpath = "//input[@id='EmailId']")
	private WebElement inpEmailId;

	@FindBy(xpath = "//input[@id='MobileNo']")
	private WebElement inpMobileNumber;

	@FindBy(xpath = "//input[@id='DOB']")
	private WebElement inpDateOfBirth;

	@FindBy(xpath = "//input[@id='rdbMale']")
	private WebElement btnGender;

	@FindBy(xpath = "//textarea[@id='Address']")
	private WebElement inpAddress;

	@FindBy(xpath = "//select[@id='CountryId']")
	private WebElement btnSelectCountry;

	@FindBy(xpath = "//option[@value='1']")
	private WebElement btnIndia;

	@FindBy(xpath = "//select[@id='CityId']")
	private WebElement btnSelectCity;

	@FindBy(xpath = "(//option[@value='7'])[2]")
	private WebElement btnHyderabad;

	@FindBy(xpath = "//input[@id='chkSkill_1']")
	private WebElement btnQaAutomation;

	@FindBy(xpath = "//button[@class='btn btn-success m-r-xs']")
	private WebElement btnSave;

	public EmployeeDetails(WebDriver driver) {
	    super(driver, Duration.ofSeconds(30));
	    log.info("Starting of HomePage constructor");

	    PageFactory.initElements(driver, this);

	    log.info("Ending of HomePage constructor");
	}

	public String getWelcomeToJalaAcademy() {
		log.info("Starting of getWelcomeToJalaAcademy method");
		log.info("Ending of getWelcomeToJalaAcademy method");

		return getText(txtHeader);
	}

	public void clickOnEmployeeButton() {
		log.info("Starting of clickOnEmployeeButton method");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(btnEmployee);
		
		log.info("Ending of clickOnEmployeeButton method");
	}

	public void clickOnCreateButton() {
		log.info("Starting of clickOnCreateButton method");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(btnCreate);
		
		log.info("Ending of clickOnCreateButton method");
	}

	public void enterFirstName(String firstName) {
		log.info("Starting of enterFirstName method");

		sendKeys(inpFirstName, firstName);
		
		log.info("Ending of enterFirstName method");
	}

	public void enterLastName(String lastName) {
		log.info("Starting of enterLastName method");

		sendKeys(inpLastName, lastName);
		
		log.info("Ending of enterLastName method");
	}

	public void enterEmailId(String emailId) {
		log.info("Starting of enterEmailId method");

		sendKeys(inpEmailId, emailId);
		
		log.info("Ending of enterEmailId method");
	}

	public void enterMobileNumber(String mobileNumber) {
		log.info("Starting of enterMobileNumber method");

		sendKeys(inpMobileNumber, mobileNumber);
		
		log.info("Ending of enterMobileNumber method");
	}

	public void enterDateOfBirth(String mobileNumber) {
		log.info("Starting of enterDateOfBirth method");

		sendKeys(inpDateOfBirth, mobileNumber);
		
		log.info("Ending of enterDateOfBirth method");
	}

	public void clickOnGenderButton() {
		log.info("Starting of clickOnGenderButton method");

		click(btnGender);
		
		log.info("Ending of clickOnGenderButton method");
	}

	public void enterAddress(String address) {
		log.info("Starting of enterAddress method");

		sendKeys(inpAddress, address);
		
		log.info("Ending of enterAddress method");
	}

	public void clickOnSelectCountry() {
		log.info("Starting of clickOnSelectCountry method");

		click(btnSelectCountry);
		
		log.info("Ending of clickOnSelectCountry method");
	}

	public void clickOnIndia() {
		log.info("Starting of clickOnIndia method");

		click(btnIndia);
		
		log.info("Ending of clickOnIndia method");
	}

	public void clickOnSelectCity() {
		log.info("Starting of clickOnSelectCity method");

		click(btnSelectCity);
		
		log.info("Ending of clickOnSelectCity method");
	}

	public void clickOnHyderabad() {
		log.info("Starting of clickOnHyderabad method");

		click(btnHyderabad);
		
		log.info("Ending of clickOnHyderabad method");
	}

	public void clickOnQaAutomation() {
		log.info("Starting of clickOnQaAutomation method");

		click(btnQaAutomation);

		log.info("Ending of clickOnQaAutomation method");
	}

	public void clickOnSave() {
		log.info("Starting of clickOnSave method");

		click(btnSave);

		log.info("Ending of clickOnSave method");
	}

}

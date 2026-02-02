package com.jalaAcademy.Automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jalaAcademy.page.BasePage;

public class LoginPage extends BasePage {
	
	@FindBy(xpath="//input[@id='UserName']")
	
	
	
	private WebElement inpEmail;

	@FindBy(xpath="//input[@id='Password']")
	private WebElement inpPassword;
	
	@FindBy(xpath="//span[@class='checkmark']")
	private WebElement btnRememberMeCheckBox;
	
	
	@FindBy(xpath="//button[@id='btnLogin']")
	private WebElement btnLogin;
	
	public LoginPage(WebDriver Driver) {
		super();
		log.info("Starting of LoginPage constructor");

		PageFactory.initElements(Driver,this);
		
		log.info("Ending of LoginPage constructor");
	}
	
	public void enterEmail(String email) {
		log.info("Starting of enterEmail method");

		sendKeys(inpEmail,email);
		
		log.info("Ending of enterEmail method");
	}
	
	public void enterPassword(String passord) {
		log.info("Starting of enterPassword method");

		sendKeys(inpPassword,passord);
		
		log.info("Ending of enterPassword method");
	}
	
	public void clickOnRememberMe() {
		log.info("Starting of clickOnRememberMe method");

		click(btnRememberMeCheckBox);
		
		log.info("Ending of clickOnRememberMe method");
	}
	
	public void clickOnLoginButton() {
		log.info("Starting of clickOnLoginButton method");

		click(btnLogin);
		
		log.info("Ending of clickOnLoginButton method");
	}
	
}
 
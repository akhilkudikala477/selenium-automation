package com.jalaAcademy.Automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jalaAcademy.page.BasePage;

public class HomePage extends BasePage {
	@FindBy(xpath = "//i[@class='fa fa-th-list']")
	private WebElement btnMore;
	
	@FindBy(xpath="(//i[@class='fa fa-hand-o-right'])[2]")
	private WebElement btnMenu;
	
	@FindBy(xpath="//a[text()=' iFrames']")
	private WebElement btniframe;
	
	@FindBy(xpath="//a[text()=' Images']")
	private WebElement btnImage;
	
	public HomePage(WebDriver Driver) {
		super();
		log.info("Starting of HomePage constructor");

		PageFactory.initElements(Driver,this);
		
		log.info("Ending of HomePage constructor");
	}
	
	public void clickOnMoreButton(WebDriver driver) {
		log.info("Starting of clickOnMoreButton method");
		

		//click(btnMore);
		Actions act=new Actions(driver);
		act.click(btnMore).perform();
		
		log.info("Ending of clickOnMoreButton method");
	}
	
	public void clickOnMenuButton() {
		log.info("Starting of clickOnMenuButton method");
		

		click(btnMenu);
		
		log.info("Ending of clickOnMenuButton method");
	}

	public void clickOnIframeButton() {
		log.info("Starting of clickOnIframeButton method");

		click(btniframe);
		
		log.info("Ending of clickOnIframeButton method");
	}
	
	public void clickOnImageButton() {
		log.info("Starting of clickOnImageButton method");

		click(btnImage);
		
		log.info("Ending of clickOnImageButton method");
	}
	
}

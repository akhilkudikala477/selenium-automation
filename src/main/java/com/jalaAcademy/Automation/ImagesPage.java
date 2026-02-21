package com.jalaAcademy.Automation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jalaAcademy.page.BasePage;

public class ImagesPage extends BasePage {

	@FindBy(xpath = "//input[@type='file']")
	private WebElement btnChooseFile;

	@FindBy(xpath = "//button[@type='button']")
	private WebElement btnUpload;

	@FindBy(xpath = "//div[@class='box-header with-border']/span")
	private WebElement labelUploadedImage;

	public ImagesPage(WebDriver driver) {
	    super(driver, Duration.ofSeconds(30));
	    log.info("Starting of HomePage constructor");

	    PageFactory.initElements(driver, this);

	    log.info("Ending of HomePage constructor");
	}

	public String getImageLabel() {
		log.info("Starting of getImageLabel method");
		log.info("Ending of getImageLabel method");

		return getText(labelUploadedImage);

	}

	public void uploadImage(String uploadImage) {
		log.info("Starting of uploadImage method");

		sendKeys(btnChooseFile, uploadImage);

		log.info("Ending of uploadImage method");
	}

	public void clickOnUploadButton() {
		log.info("Starting of clickOnUploadButton method");

		click(btnUpload);

		log.info("Ending of clickOnUploadButton method");
	}
}

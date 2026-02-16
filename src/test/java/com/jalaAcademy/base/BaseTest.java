package com.jalaAcademy.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeSuite;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static Properties testDataProp;
	protected static Properties expectedAssertion;
	protected static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	protected static Logger log = LogManager.getLogger(BaseTest.class);

	@BeforeSuite
	public void loadPropertyFiles() {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/testdata/testdata.properties");
			testDataProp = new Properties();
			testDataProp.load(fis);

			FileInputStream expectedfis = new FileInputStream(
					"src/test/resources/testdata/expectedAssertion.properties");

			expectedAssertion = new Properties();
			expectedAssertion.load(expectedfis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver createDriver(String driverName) {
		WebDriver childDriver = null;
		if (driverName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			
			options.addArguments("--remote-allow-origins=*");
	    options.addArguments("--use-fake-ui-for-media-stream");
	    options.addArguments("--disable-media-stream");
	    options.addArguments("--window-size=1920,1080");

	    // Optional from config
	    if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
	        options.addArguments("--incognito");
	    }

	    // Detect CI environment automatically
	    boolean isCI = System.getenv("CI") != null;

	    // Detect headless from property
	    boolean isHeadlessFromProp = Boolean.parseBoolean(prop.getProperty("headless"));

	    // Apply headless if CI OR property enabled
	    if (isCI || isHeadlessFromProp) {

	        System.out.println("Running in HEADLESS mode");

	        options.addArguments("--headless=new");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--disable-gpu");
	        options.addArguments("--disable-extensions");

	    } else {

	        System.out.println("Running in NORMAL mode");

	    }
			childDriver = new ChromeDriver(options);
			childDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			childDriver.manage().window().maximize();
		} else if (driverName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

			// OPTIONAL: incognito/private mode
			options.addArguments("-private");

			childDriver = new FirefoxDriver(options);

			childDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			childDriver.manage().window().maximize();

		}
		tldriver.set(childDriver);
		return childDriver;
	}
	public WebDriver getDriver() {
        return tldriver.get();
    }
	public static void captureScreenshot() {
		log.info("Starting of captureScreenshot method ");
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS" + "" + "").format(new Date());
		File file = ((TakesScreenshot) tldriver.get()).getScreenshotAs(OutputType.FILE);
		System.out.println(timeStamp);
		try {
			FileUtils.copyFile(file, new File(".//Screenshot//Screenshot_" + timeStamp + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		log.info("Ending of captureScreenshot method ");
	}

	public synchronized void quitDriver() throws InterruptedException {
		if (tldriver.get() != null) {
			tldriver.get().quit();
            tldriver.remove();
		}
	}
}

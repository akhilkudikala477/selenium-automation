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

		boolean isCI = System.getenv("CI") != null;
		boolean isHeadlessFromProp = false;
		boolean runHeadless = isCI || isHeadlessFromProp;

		if (driverName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--use-fake-ui-for-media-stream");
			options.addArguments("--disable-media-stream");
			options.addArguments("--incognito");
			// Always set window size (DO NOT use maximize in CI)
			options.addArguments("--window-size=1920,1080");

			// Incognito
			// if (Boolean.parseBoolean(prop.getProperty("incognito", "true"))) {
			// options.addArguments("--incognito");
			// }

			if (runHeadless) {

				System.out.println("Running in HEADLESS mode (CI detected)");

				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-extensions");
				options.addArguments("--remote-debugging-port=9222");
				options.addArguments("--incognito");
			} else {

				System.out.println("Running in NORMAL mode (Local)");

			}

			childDriver = new ChromeDriver(options);

			childDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// ❌ DO NOT maximize in headless mode
			if (!runHeadless) {
				childDriver.manage().window().maximize();
			}

		}

		else if (driverName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions options = new FirefoxOptions();

			// DO NOT set binary path for CI (Linux auto-detects)
			if (!isCI) {
				options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			}

			options.addArguments("-private");

			if (runHeadless) {
				options.addArguments("-headless");
			}

			childDriver = new FirefoxDriver(options);

			childDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			if (!runHeadless) {
				childDriver.manage().window().maximize();
			}
		}

		else {
			throw new RuntimeException("Unsupported browser: " + driverName);
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

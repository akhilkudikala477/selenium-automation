package com.jalaAcademy.page;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.*;
import java.time.Duration;
import java.util.Set;

public class BasePage {
	private final WebDriver driver;

	protected static Logger log = LogManager.getLogger(BasePage.class);
	private final JavascriptExecutor js;
	private final WebDriverWait wait;
	private final Actions actions;

	public BasePage(WebDriver driver, Duration timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.actions = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
	}

	public void click(WebElement element) {
		log.info("Starting of click method");
		safeClick(element);
		//element.click();

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

	public void safeClick(By locator) {
		safeClickInternal(locator, 5);
	}

	// Overload for WebElement when you already have it
	public void safeClick(WebElement element) {
		// try to obtain a stable locator-free click by wrapping element in a retry
		safeClickInternal(element, 5);
	}

	// INTERNAL: locator-based
	private void safeClickInternal(By locator, int maxAttempts) {
		int attempt = 0;
		long sleep = 300;
// ms, grows
		while (attempt < maxAttempts) {
			attempt++;
			try {
				WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				safeClickInternal(el, 1); // single attempt on WebElement (already wrapped in outer loop)
				return;
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				// retry quickly
			} catch (TimeoutException e) {
				// element not present in time
				if (attempt >= maxAttempts) {
					failWithScreenshot("Element not present: " + locator, e);
				}
			} catch (WebDriverException e) {
				// last resort: retry or bubble up
				if (attempt >= maxAttempts)
					failWithScreenshot("Click failed for locator: " + locator, e);
			}
			sleepMillis(sleep);
			sleep *= 2;
		}
	}

	// INTERNAL: element-based with internal retry attempts
	private void safeClickInternal(WebElement element, int maxAttempts) {
		int attempt = 0;
		long sleep = 300;
		while (attempt < maxAttempts) {
			attempt++;
			try {
				// 1) Ensure visible & enabled
				wait.until(driver -> {
					try {
						return element.isDisplayed() && element.isEnabled();
					} catch (StaleElementReferenceException ex) {
						return false;
					}
				});

				// 2) Scroll into view (center)
				scrollIntoViewCenter(element);

				// 3) Wait until clickable (some pages require additional time)
				try {
					wait.until(ExpectedConditions.elementToBeClickable(element));
				} catch (Exception ignored) {
				}

				// 4) If element is not top-most, try to neuter overlays
				if (!isElementTopMost(element)) {
					neutralizeLikelyOverlays();
					// small wait to let DOM update
					sleepMillis(150);
				}

				// 5) Try normal click
				try {
					element.click();
					return;
				} catch (WebDriverException e1) {
					// try actions click
				}

				// 6) Try Actions move+click
				try {
					actions.moveToElement(element).pause(Duration.ofMillis(100)).click().perform();
					return;
				} catch (WebDriverException e2) {
					// fallback to JS
				}

				// 7) JS click fallback
				jsClick(element);
				return;
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				// element changed - retry
			} catch (Exception e) {
				if (attempt >= maxAttempts) {
					failWithScreenshot("safeClick ultimately failed (attempt " + attempt + ")", e);
				}
			}
			sleepMillis(sleep);
			sleep *= 2;
		}
	}

	/* -------------------- helper methods -------------------- */

	private void scrollIntoViewCenter(WebElement el) {
		js.executeScript("var rect = arguments[0].getBoundingClientRect();"
				+ "var viewHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
				+ "window.scrollBy({top: rect.top - (viewHeight/2) + (rect.height/2), left: 0, behavior: 'instant'});",
				el);
		// tiny pause for layout to settle
		sleepMillis(80);
	}

	private void jsClick(WebElement el) {
		js.executeScript("arguments[0].click();", el);
	}

	/**
	 * Returns true if the element is the top-most element at its center point (or
	 * contains the top-most element). Works in headless and headed.
	 */
	private boolean isElementTopMost(WebElement el) {
		try {
			Object result = js.executeScript("var el = arguments[0];" + "var rect = el.getBoundingClientRect();"
					+ "var cx = rect.left + rect.width/2; var cy = rect.top + rect.height/2;"
					+ "if (cx < 0 || cy < 0) return false;" + "var top = document.elementFromPoint(cx, cy);"
					+ "return (el === top) || el.contains(top);", el);
			return Boolean.TRUE.equals(result);
		} catch (Exception e) {
			return true; // if JS fails, assume true (don't block)
		}
	}

	/**
	 * Try to neutralize common overlay/backdrop/spinner nodes that block clicks.
	 * This removes pointer-events and hides common classes (modal-backdrop,
	 * spinner, overlay, loading) — conservative and reversible.
	 */
	private void neutralizeLikelyOverlays() {
		try {
			js.executeScript(
					"var selectors = ['.modal-backdrop', '.spinner', '.loading', '.overlay', '.blocker', '.ajax-loader', '.loader'];"
							+ "selectors.forEach(function(s){ Array.from(document.querySelectorAll(s)).forEach(function(n){ n.style.pointerEvents='none'; n.style.visibility='hidden'; }); });"
							+
							// also try to remove full-screen invisible overlays that have high z-index
							"Array.from(document.querySelectorAll('body *')).forEach(function(n){ try{ var z=getComputedStyle(n).zIndex; if(z && parseInt(z) > 10000){ n.style.pointerEvents='none'; } }catch(e){} });");
		} catch (Exception ignored) {
		}
	}

	private void failWithScreenshot(String message, Exception cause) {
		try {
			Path screenshots = Paths.get("target", "screenshots");
			Files.createDirectories(screenshots);
			String name = "failure-" + System.currentTimeMillis() + ".png";
			Path dst = screenshots.resolve(name);

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);
			Files.write(dst, bytes);

			throw new RuntimeException(message + " — screenshot: " + dst.toString(), cause);
		} catch (Exception io) {
			throw new RuntimeException(message + " — additionally failed to save screenshot", cause);
		}
	}

	private void sleepMillis(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ignored) {
		}
	}

}

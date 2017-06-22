package org.epam.commonlibrary;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HelperClass {
	
	private static final Logger LOGGER = Logger.getLogger(HelperClass.class);
	private WebDriver driver;

	public HelperClass(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isTextPresent(String textToVerify) {
		String bodyText = driver.findElement(By.tagName("body")).getText();
		return bodyText.contains(textToVerify);
	}

	public void selectOptionByText(WebElement objProperty, String optionToSelect) {

		Select select = new Select(objProperty);
		select.selectByVisibleText(optionToSelect);
		LOGGER.info("Option with '" + optionToSelect + "' is available in Dropdown");

	}

	public WebElement waitForElementVisible(WebElement locator, int timeOut) {

		Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", element);

	}

}

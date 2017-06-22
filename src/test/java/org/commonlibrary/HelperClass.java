package org.commonlibrary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class HelperClass {
	private WebDriver driver;

	public HelperClass(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isTextPresent(String textToVerify) {
		String bodyText;
		bodyText = driver.findElement(By.tagName("body")).getText();

		if (bodyText.contains(textToVerify)) {
			return true;
		} else {
			return false;
		}
	}

	public void selectOptionByText(WebElement objProperty, String optionToSelect) {

		String status = "false";

		Select select = new Select(objProperty);

		List<WebElement> options = select.getOptions();

		int optionsCount = options.size();

		Reporter.log("Options Count" + optionsCount);

		if (optionsCount > 0) {

			for (WebElement option : options) {

				if (option.getText().equals(optionToSelect)) {

					select.selectByVisibleText(optionToSelect);
					Reporter.log("Option with '" + optionToSelect + "' is available in Dropdown");
					status = "true";
					break;
				}
			}
			if (!(status == "true")) {
				Reporter.log("Option with ' " + optionToSelect + " ' is  not available in Dropdown");
			}
		} else {
			Reporter.log("There were no options available in Dropdown");
		}
	}

	public WebElement waitForElementVisible(WebElement locator, int timeOut) {

		Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
		WebElement visiblity = wait.until(ExpectedConditions.visibilityOf(locator));
		return visiblity;

	}
	
	public void jsExecutor(WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", element);
		
	}

}

package org.commonlibrary;

import java.util.Arrays;
import java.util.List;

import org.flight.pages.DateSelectionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DatePickerClass {
	private WebDriver driver;
	WebElement datePicker;
	List<WebElement> noOfColumns;
	List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December");

	// Calendar Month and Year
	String calMonth = null;
	String calYear = null;
	boolean dateNotFound;
	
	private DateSelectionPage datePage;

	public DatePickerClass(WebDriver driver) {
		this.driver = driver;
		this.datePage=new DateSelectionPage(this.driver);
	}

	// Date format should be dd-mm-yyyy
	public void selectDate(String expDate) {
		dateNotFound = true;
		String[] date = splitData(expDate,"/");
		// Expected Date, Month and Year
		int expMonth = Integer.parseInt(date[1]);
		int expYear = Integer.parseInt(date[2]);
		String expDay = date[0];

		// This loop will be executed continuously till dateNotFound Is true.
		while (dateNotFound) {
			// Retrieve current selected month name from date picker popup.
			
			String calMonth =datePage.getLblMonth().getText();
			System.out.println(calMonth+ "TextONe");
			// Retrieve current selected year name from date picker popup.
			String calYear = datePage.getLblYear().getText();
			System.out.println(calYear+"TextTwo");

			// If current selected month and year are same as expected month and
			// year then go Inside this condition.
			if (monthList.indexOf(calMonth) + 1 == expMonth && (expYear == Integer.parseInt(calYear))) {
				// Call selectDate function with date to select and set
				// dateNotFound flag to false.
				pickDate(expDay);
				dateNotFound = false;
			}
			// If current selected month and year are less than expected month
			// and year then go Inside this condition.
			else if (monthList.indexOf(calMonth) + 1 < expMonth && (expYear == Integer.parseInt(calYear))
					|| expYear > Integer.parseInt(calYear)) {
				// Click on next button of date picker.
				datePage.getLnkNext().click();
			}
			// If current selected month and year are greater than expected
			// month and year then go Inside this condition.
			else if (monthList.indexOf(calMonth) + 1 > expMonth && (expYear == Integer.parseInt(calYear))
					|| expYear < Integer.parseInt(calYear)) {
				// Click on previous button of date picker.
				datePage.getLnkPrevious().click();
			}
		}

	}

	private void pickDate(String date) {
		noOfColumns = datePage.getDate().findElements(By.tagName("td"));

		// Loop will rotate till expected date not found.
		for (WebElement cell : noOfColumns) {
			// Select the date from date picker when condition match.
			if (cell.getText().equals(date)) {
				WebElement dateElement=cell.findElement(By.linkText(date));
				Assert.assertTrue(dateElement.isEnabled(),"Date is not enabled for selection so please choose another date");
				dateElement.click();
				break;
			}
		}
	}

	public String[] splitData(String dataToSplit, String delimiter) {
		String[] data = dataToSplit.split(delimiter);
		return data;
	}
}

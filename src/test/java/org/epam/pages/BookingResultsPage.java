package org.epam.pages;

import java.util.Map;
import java.util.TreeMap;

import org.epam.commonlibrary.DatePickerClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingResultsPage {

	@FindBy(xpath = "//li[text()='Choose your flight']")
	private WebElement lblChooseYourflight;

	@FindBy(xpath = "//h3[contains(text(),'Your search:')]/following-sibling::div/p/strong")
	private WebElement txtCityDetails;

	@FindBy(xpath = "//span[@id='inboundDate']/parent::p")
	private WebElement txtPassengers;	

	private DatePickerClass datePicker;

	public BookingResultsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean isDisplayed() {
		return lblChooseYourflight.isDisplayed();
	}

	public String getChooseYourflightTabStatus() {
		return lblChooseYourflight.getAttribute("class");
	}

	public String getFromAndDestination() {
		return txtCityDetails.getText();
	}

	public Map<String, String> getPassangersDetails() {
		Map<String, String> validationData = new TreeMap<String, String>();
		String journeyDetails = txtPassengers.getText();
		String[] splittedData = datePicker.splitData(journeyDetails, "|");

		validationData.put("JourneyDates", splittedData[0]);
		validationData.put("PassengersData", splittedData[1]);

		return validationData;
	}

}

package org.epam.tat;

import java.util.Map;
import java.util.TreeMap;

import org.commonlibrary.WebDriverBaseClass;
import org.flight.pages.BookingResultsPage;
import org.flight.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FlightBookingTest extends WebDriverBaseClass {
	
	private String expTitle = "Cheap flights, hotel deals, rental car | vueling.com";		 
	private String selectedFromCity="Almeria (LEI)";
	private String selectedDestinationCity= "Barcelona (BCN)";
	private String chooseYourflightTabStatusStatus="selected";
	private Map<String, String> data;	

	@Test
	public void flightBookingTest() {
		
		Assert.assertEquals(driver.getTitle(), expTitle);
		
		boolean isFlightSearchComplete=new HomePage(driver).flightSearch(testData()).isDisplayed();
		Assert.assertTrue(isFlightSearchComplete, "User not able to search for flights");
		
	}
	
	@Test(dependsOnMethods={"flightBookingTest"})
	public void validateFlightSearchTest(){
		SoftAssert softAssert=new SoftAssert();
		BookingResultsPage bookingPage=new BookingResultsPage(driver);
		softAssert.assertEquals(bookingPage.getChooseYourflightTabStatus(),chooseYourflightTabStatusStatus);		
		softAssert.assertEquals(bookingPage.getFromAndDestination(), selectedFromCity+" - "+selectedDestinationCity);
		softAssert.assertEquals(bookingPage.getPassangersDetails().get("JourneyDates"), testData().get("outBoundDate")+" - "+testData().get("inBoundDate"));
		softAssert.assertEquals(bookingPage.getPassangersDetails().get("PassengersData"),
				testData().get("adultsCount")+" Adults, "+testData().get("childrenCount")+" Children, "+testData().get("babiesCount")+" Babies");
		
		softAssert.assertAll();
		
	}
	
	
	private Map<String, String> testData(){
		data= new TreeMap<String, String>();
		data.put("fromCity","Almeria");
		data.put("destinationCity","Barcelona");
		data.put("outBoundDate","22/6/2017");
		data.put("inBoundDate","21/7/2017");
		data.put("adultsCount","2");
		data.put("childrenCount","2");
		data.put("babiesCount","2");
		data.put("familyType","General Large Family");
		
		return data;
	}

}

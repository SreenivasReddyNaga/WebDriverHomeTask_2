package org.commonlibrary;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class WebDriverBaseClass {

	private static final Logger LOGGER = Logger.getLogger(WebDriverBaseClass.class);

	public WebDriver driver;

	@BeforeClass
	@Parameters({ "browserName", "url" })
	public void setUp(String browserName, String url) {

		if (browserName.equals("IExplore")) {

			System.setProperty("webdriver.ie.driver", "src\\test\\resources\\IEDriverServer32Bit.exe");
			driver = new InternetExplorerDriver();
			LOGGER.info("Script execting on " + browserName + "browser");

		} else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
			LOGGER.info("Script execting on " + browserName + "browser");
		} else if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");

			driver = new ChromeDriver();
			LOGGER.info("Script execting on " + browserName + "browser");
		} else if (browserName.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", "src\\test\\resources\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			LOGGER.info("Script execting on " + browserName + "browser");
		} else {

			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
			LOGGER.info("Script execting on default firefox browser");
		}
         
		driver.get(url);
		driver.manage().window().maximize();
	}

	//@AfterClass
	public void tearDown() {
		driver.quit();
	}

}

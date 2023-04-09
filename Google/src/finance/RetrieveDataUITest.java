package finance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RetrieveDataUITest {
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\krakh\\Desktop\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = "https://www.google.com/finance";
        driver.get(url);
	}
	
	@Test(priority = 1)
	public void verifyPageTitle() {
		System.out.println("Running Test verifyPageTitle");
		System.out.println("Verify the page is loaded by asserting the page title");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@id='sdgBod']")));//Locator Of Google Finance
		
		String expectedPageTitle = "Google Finance - Stock Market Prices, Real-time Quotes & Business News";
		String actualPageTitle = driver.getTitle().toString();
		Assert.assertEquals(expectedPageTitle, actualPageTitle);
	}
	
	@Test(priority = 2)
	public void retrieveStockSymbolsListedOnFinancePage() {
		System.out.println("Running Test retrieveStockSymbolsListedOnFinancePage");
		System.out.println("Retrieves the stock symbols listed under the section You may be interested in info ....");
		System.out.println("List of retrived Stocks Ticker From Finance Page");
		List<WebElement> listOfRetrievedStocks = driver.findElements(By.cssSelector("ul[class='sbnBtf'] li a div div div div div div"));//Retrieve the Ticker
		for(WebElement retrievedStock : listOfRetrievedStocks) {
			System.out.println(retrievedStock.getText());
		}
		
	}
	
	@Test(priority = 3)
	public void compareTheStockSymbolsRetrievedFromThree() {
		System.out.println("Running Test compareTheStockSymbolsRetrievedFromThree");
		System.out.println("Compare the stock symbols retrived from (3) with expectedStockSymbols");
		
		//test data		
		System.out.println("Expected stocks");
		List<String> expectedStockSymbols = Arrays.asList("NFLX", "MSFT", "TSLA");
		for(String expectedStock : expectedStockSymbols) {
			System.out.println(expectedStock);
		}
		
		//retrieved data
		System.out.println("Retrieved stocks");
		List<WebElement> listOfRetrievedStocks = driver.findElements(By.cssSelector("ul[class='sbnBtf'] li a div div div div div div"));//Retrieve the Ticker
		for(WebElement retrievedStock : listOfRetrievedStocks) {
			System.out.println(retrievedStock.getText());
		}
		
		//compare two lists
		boolean areEqual = listOfRetrievedStocks.equals(expectedStockSymbols);
		// Print the result of the comparison
        System.out.println("The two lists are " + (areEqual ? "equal" : "not equal"));
		
	}
	
	@Test(priority = 4)
	public void printAllStockSymbolsInThreeBuNotInExpected() {
		System.out.println("Running Test printAllStockSymbolsInThreeBuNotInExpected");
		System.out.println("Print all stock symbols that are in (3) but not in expectedStockSymbols");
		//test data	
		List<String> expectedStockSymbols = Arrays.asList("NFLX", "MSFT", "TSLA");
		
		//retrieved data
		List<WebElement> listOfRetrievedStocks = driver.findElements(By.cssSelector("ul[class='sbnBtf'] li a div div div div div div"));//Retrieve the Ticker
		
		for(WebElement stock : listOfRetrievedStocks) {
			if(!expectedStockSymbols.contains(stock.getText())) {
				System.out.println(stock.getText());
			}
		}
		
	}
	
	@Test(priority = 5)
	public void printAllStockSymbolsInExpectedBuNotInThree() {
		System.out.println("Running Test printAllStockSymbolsInExpectedBuNotInThree");
		System.out.println("Print all stock symbols that are in expectedStockSymbols but not in (3)");
		//test data	
		List<String> expectedStockSymbols = Arrays.asList("NFLX", "MSFT", "TSLA");
		
		//retrieved data
		List<WebElement> listOfRetrievedStocks = driver.findElements(By.cssSelector("ul[class='sbnBtf'] li a div div div div div div"));//Retrieve the Ticker
		//convert List of WebElements to List Of String (Ticker names)
		List<String> textValuesForListOfRetrievedStocks = new ArrayList<>();
		for (WebElement element : listOfRetrievedStocks) {
			textValuesForListOfRetrievedStocks.add(element.getText());
		}
		
		for(String stock : expectedStockSymbols) {
			if(!textValuesForListOfRetrievedStocks.contains(stock)) {
				System.out.println(stock);
			}
		}
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}

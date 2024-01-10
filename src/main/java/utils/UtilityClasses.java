package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.constants.FrameWorkConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UtilityClasses {
	public static WebDriver driver;
	public static Properties prop;
	// develop a functionality for browser selection
	
	
	public static String readProperty(String key) throws Exception {
		String propath = System.getProperty("user.dir") + "/src/main/resources/Data.properties";
		FileInputStream fileInput = new FileInputStream(propath);
		prop.load(fileInput);
		return prop.get(key).toString();
	}

	public static WebDriver initialization() throws IOException {
		prop = new Properties();
		String propath = System.getProperty("user.dir") + "/src/main/resources/Data.properties";
		FileInputStream fis = new FileInputStream(propath);
		prop.load(fis);

		String browserChoice = prop.getProperty("browser");

		switch (browserChoice.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().driverVersion("latest").setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			//chromeOptions.addArguments(prop.getProperty("browserMode"));
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().driverVersion("latest").setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments(prop.getProperty("browserMode"));
			driver = new FirefoxDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().driverVersion("latest").setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Invalid Browser Selection: " + browserChoice);
			System.exit(1);
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement countrySelection = driver.findElement(By.xpath("(//img[@alt='United States'])[1]"));
		countrySelection.click();
		return driver;
	}

	// Add Functionality to capture ScreenShot
	public static String TakeScreenShot(String testname, WebDriver driver) throws IOException {
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshot = FrameWorkConstants.SCREENSHOT_PATH_PREFIX + testname
				+ FrameWorkConstants.SCREENSHOT_PATH_SUFFIX;
		FileUtils.copyFile(srcScreenshot, new File(screenshot));
		return screenshot;

	}
	public void setImplicitWait(int timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	public void inputTextBasedonField(String inputText, String inputValue) {
		WebElement inputfield = driver
				.findElement(By.xpath("//input[@name='" + inputText + "' or @type='" + inputText + "']"));
		inputfield.sendKeys(inputValue);
	}

	public static void SwitchWindow() {
		String ParentWindow = driver.getWindowHandle();
		Set<String> windowhandle = driver.getWindowHandles();
		for (String childwindow : windowhandle) {
			if (!childwindow.contentEquals(ParentWindow)) {
				driver.switchTo().window(childwindow);
			}

		}
	}

	public static WebElement waitforElementTobeClickable(By locator, int timeoutSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static WebElement click(WebElement element, String elementName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public boolean isElementExists(By WebElement) {
		boolean isExists = false;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			isExists = !driver.findElements(WebElement).isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		}
		return isExists;
	}

	public boolean isElementExists(By WebElement, String ElementName) {
		boolean isPresent = isElementExists(WebElement);
		if (isPresent) {
			System.out.println("Element is Present");
		} else {
			System.out.println("Element is Not Present");
		}
		return isPresent;
	}
	public static void waitExplicitUntillTitle(String titleToWait) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.titleIs(titleToWait));
	}
	
	public static String getPageTitle() {
		waitExplicitUntillTitle(driver.getTitle());
		return driver.getTitle();
	}
	
	
	public static int getResponseCode(String url) throws Exception, Exception {
		 HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .build();
	        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
	       int ResponseCode = response.statusCode();
	       return ResponseCode;
	}


}

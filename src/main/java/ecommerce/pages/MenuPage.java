package ecommerce.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage extends utils.UtilityClasses {
	public WebDriver driver;

	public MenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Menu']")
	private WebElement MenuButton;

	@FindBy(xpath = "(//img[@alt='United States'])[1]")
	private WebElement countrySelection;

	@FindBy(xpath = "//h1[text()='Best Buy Support']")
	private WebElement supportText;

	@FindBy(xpath = "//input[@class='search-input']")
	private WebElement searchInput;

	@FindBy(xpath = "(//button[text()='Add to Cart'])[1]")
	private WebElement addtocarbutton;

	@FindBy(xpath = "//span[text()='Cart']")
	private WebElement cartPage;

	@FindBy(xpath = "//button[text()='Continue shopping']")
	private WebElement buttonContinueShopping;

	@FindBy(xpath = "//button[text()='Checkout']")
	private WebElement buttonCheckout;

	@FindBy(xpath = "//span[text()='Continue to Payment Information']")
	private WebElement buttonContinue;
	

	//input[@name='firstName']

	public void clicktitle(String Title) {
		WebElement ClickTitle = driver.findElement(By.xpath("//button[text()='"+Title+"']"));
		ClickTitle.click();
	}

	public void ClickelementbyTitle(String title, String navigationPage) {
		WebElement ClickElementByTitle = driver.findElement(By.xpath("//button[text()='"+title+"']//parent::li//following-sibling::li//div//li//a[text()='"+navigationPage+"']"));
		ClickElementByTitle.click();
	}

	public void clickMenuButton() {
		MenuButton.click();
	}

	public void clickcountrySelection() {
		countrySelection.click();
	}

	public String getTextUsingTitle(String Title) {
		WebElement titleElement = driver.findElement(By.xpath("//h1[text()='"+Title+"']"));
		String text = titleElement.getText();
		return text;
	}

	public String getAttribute(String title) {
		WebElement titleElement = driver.findElement(By.xpath("//img[@alt='"+title+"']"));
		String attribute = titleElement.getAttribute("alt");
		return attribute;

	}

	public String getTextSupport() {
		String text = supportText.getText();
		return text;
	}

	public String getTextUsingDepartmentName(String Title) {
		WebElement titleElement = driver.findElement(By.xpath("(//*[text()='"+Title+"'])[1]"));
		String text = titleElement.getText();
		return text;
	}

	public void clickProductUsingTitle(String title) {
		WebElement titleElement = driver.findElement(By.xpath("//a[contains(text(),'"+title+"')]"));
		titleElement.click();
	}

	public void clickProductUsingBrandName(String title) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		WebElement titleElement = driver.findElement(By.xpath("(//a[contains(text(),'"+title+"')])[1]"));
		titleElement.click();
	}

	public void searchArea(String searchText) {
		searchInput.sendKeys(searchText);
		searchInput.sendKeys(Keys.ENTER);
	}

	public String getTextSearchItem(String Item) {
		WebElement searchText = driver.findElement(By.xpath("//a[contains(text(),'"+Item+"')]"));
		String text = searchText.getText();
		return text;
	}

	public void ScrollandClickAddtoCart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		addtocarbutton.click();
	}

	public void selectProductByBrand(String productBrand) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		WebElement element = driver.findElement(By.xpath("(//a[text()='"+productBrand+"'])[3]"));
		element.click();
	}

	public void scrollandClickProduct(String title) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		WebElement titleElement = driver.findElement(By.xpath("//a[contains(text(),'"+title+"')]"));
		titleElement.click();
	}

	public void clickcartPage() {
		cartPage.click();
	}

	public void clickcontinueShopping() {
		String locator = "//button[text()='Continue shopping']";
		if (clickIfElementExists(driver, By.xpath(locator))) {
			setImplicitWait(5);
			buttonContinueShopping.click();
		}
	}

	public List<String> getCartProducts(String Item) {
		List<WebElement> searchText = driver.findElements(By.xpath("//a[contains(text(),'"+Item+"')]"));
		List<String> Texts = new ArrayList<String>();
		for (WebElement element : searchText) {
			Texts.add(element.getText());
		}
		return Texts;
	}

	private static boolean elementExists(WebDriver driver, By locator) {
		return driver.findElements(locator).size() > 0;
	}

	private static boolean clickIfElementExists(WebDriver driver, By locator) {
		if (elementExists(driver, locator)) {
			WebElement element = driver.findElement(locator);
			element.click();
			return true;
		} else {
			System.out.println("Element does not exist.");
			return false;
		}
	}

	public String bestBuyPageTitle() throws Exception {
		return getPageTitle();
	}

	public int urlResponseCode() throws Exception {
		return getResponseCode(readProperty("url"));
	}

	public void clickCheckoutButton() {
		buttonCheckout.click();
	}

	public void ScrollandClickContinueButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		buttonContinue.click();
	}
	
	public void sendOrderInformation(String paymentElement,String PaymentDetails) {
		List<WebElement> sendTextElements = driver.findElements(By.xpath("//*[@name='"+paymentElement+"']"));
		for(WebElement element :sendTextElements) {
			if(!element.getText().equalsIgnoreCase("state")) {
			element.sendKeys(PaymentDetails);
			}
			else {
				SelectDropdownFromVisibleTextSearch(element,PaymentDetails);
			}
		}
	}
	public String SelectDropdownFromVisibleTextSearch(WebElement Dropdown,String name) {
		Dropdown.click();
		Dropdown.sendKeys(name);
		driver.findElement(By.xpath("//*[@name='"+Dropdown+"']")).click();
		return name;
		
	}

}

package ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.UtilityClasses;

public class RegistrationPage extends utils.UtilityClasses{
	public WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Create Account']")
	private WebElement CreateAccount;

	@FindBy(xpath = "//button[text()='Create an Account']")
	private WebElement buttonCreateAccount;
	
	@FindBy(xpath = "//span[text()='Account']")
	private WebElement Account;
	
	@FindBy(xpath = "//button[text()='Sign In']")
	private WebElement buttonSignIn;
	
	@FindBy(xpath = "//a[text()='Sign In']")
	private WebElement SignIn;
	

	public void clickCreateAccount() {
		CreateAccount.click();
	}

	public void clickAccountCreation() {
		buttonCreateAccount.click();
	}
	public void clickAccount() {
		Account.click();
	}
	
	public void clickSignIn() {
		buttonSignIn.click();
	}
	public void clickSignInPage() {
		SignIn.click();
	}
	
	
	public void inputTextBasedonField(String inputText,String inputValue) {
		WebElement inputfield = driver.findElement(By.xpath("//input[@name='"+inputText+"' or @type='"+inputText+"']"));
		inputfield.sendKeys(inputValue);
	}

}

package baseFunctions;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;


public class CommonFunctions extends utils.UtilityClasses {
	
	@BeforeMethod(alwaysRun=true)
	public void suiteSetup() throws IOException {
		driver = initialization();
	}
	
	public static void loginSetup() {
		WebElement accountButton = driver.findElement(By.xpath("//span[text()='Account']"));
		accountButton.click();
		WebElement signInPage = driver.findElement(By.xpath("//a[text()='Sign In']"));
		signInPage.click();
		WebElement emailInput = driver.findElement(By.xpath("//input[@type='email']"));
		emailInput.sendKeys(prop.getProperty("email"));
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys(prop.getProperty("password"));
		WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
		signInButton.click();
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
}

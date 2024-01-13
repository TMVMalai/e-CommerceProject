package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import baseFunctions.CommonFunctions;
import ecommerce.DDD.dataproviders.DataProviderClasses;
import ecommerce.Modules.Modules;


public class PageNavigationAndProductValidationTest extends CommonFunctions {
	
	
	@BeforeMethod
	public void login() {
		loginSetup();
	}

	@Test(enabled = true, groups = "Regression", dataProviderClass = DataProviderClasses.class, dataProvider = "getJsonData")
	@SuppressWarnings("unchecked")
	public void validatePageNavigation(Object data) {
		Map<String, Object> testData = (Map<String, Object>) data;
		System.out.println(testData);
		Modules m = new Modules(testData, driver);
		m.validatePagenavigation();
		m.validatePagesShopbyDepartment();
		m.searchItem();
		m.productSelection();
	}

	@Test(enabled = true, groups = "Regression", dataProviderClass = DataProviderClasses.class, dataProvider = "getJsonData")
	@SuppressWarnings("unchecked")
	public void selectProducts(Object data) {
		Map<String, Object> testData = (Map<String, Object>) data;
		System.out.println(testData);
		Modules m = new Modules(testData, driver);
		m.clickProductByBrandAndDepartment();
		m.navigateToCartPage();
	}

}

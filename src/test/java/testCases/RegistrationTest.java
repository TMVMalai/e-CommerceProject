package testCases;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Map;

import baseFunctions.CommonFunctions;
import ecommerce.DDD.dataproviders.DataProviderClasses;
import ecommerce.Modules.Modules;

public class RegistrationTest extends CommonFunctions {
	
	@BeforeMethod
	public void login() {
		loginSetup();
	}

	@Test(enabled =false, groups = "Regression", dataProviderClass = DataProviderClasses.class, dataProvider = "getRegistrationData")
	@SuppressWarnings("unchecked")
	public void signupFunctionality(Object data) {
		Map<String, Object> testData = (Map<String, Object>) data;
		System.out.println(testData);
		Modules m = new Modules(testData, driver);
		m.signup();
	}
}

package testCases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseFunctions.CommonFunctions;

import ecommerce.Modules.Modules;

public class LinkBrokenOrNotTest extends CommonFunctions {
	
	@BeforeMethod
	public void login() {
		loginSetup();
	}

	@Test(enabled = true, groups = "Regression")
	public void LinkBrokenorNot() throws Exception {
		Map<String, Object> testData = new HashMap<String,Object>();
		Modules m = new Modules(testData, driver);
		m.linkbrokenorNot();
	}
}

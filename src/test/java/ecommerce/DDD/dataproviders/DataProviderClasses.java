package ecommerce.DDD.dataproviders;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import utils.JsonUtilties;

public class DataProviderClasses {
	JsonUtilties jsonUtils = new JsonUtilties();

	@DataProvider(name ="getJsonData")
	public Object[] getJsonData(Method m) {
		return jsonUtils.readMultiJsonData("DemoTest.json", m.getName());
	}

	@DataProvider(name ="getRegistrationData")
	public Object[] getRegistrationData(Method m) {
		return jsonUtils.readMultiJsonData("LoginData.json", m.getName());
	}
}

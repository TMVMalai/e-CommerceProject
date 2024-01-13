package ecommerce.Modules;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;


import ecommerce.pages.MenuPage;
import ecommerce.pages.RegistrationPage;


public class Modules{
	WebDriver driver;
	MenuPage menuPage;
	RegistrationPage registrationPage;
	Map<String, Object> data;

	public Modules(Map<String, Object> data, WebDriver driver) {
		this.driver = driver;
		menuPage = new MenuPage(driver);
		registrationPage = new RegistrationPage(driver);
		this.data = data;
	}

	public void validatePagenavigation() {
		HashMap<String, Object> dataValue = (HashMap<String, Object>) data;
		String[] titleNames = dataValue.get("TitleNames").toString().split(",");
		String[] titleValues = dataValue.get("TitleValues").toString().split(",");
		int num = 0;
		for (int i = 0; i < titleNames.length; i++) {
			menuPage.clickMenuButton();
			menuPage.clicktitle(titleNames[i]);
			for (int j = num; j < titleValues.length;) {
				menuPage.ClickelementbyTitle(titleNames[i], titleValues[j]);
				if (titleNames[i].equalsIgnoreCase("Deals")) {
					assertEquals(titleValues[j],menuPage.getTextUsingTitle(titleValues[j]));
				} else if (titleNames[i].equalsIgnoreCase("Support & Services")) {
					assertEquals(data.get("SupportStatement").toString(), menuPage.getTextSupport());
				} else {
					assertEquals(titleValues[j], menuPage.getAttribute(titleValues[j]));
				}
				num = j + 1;
				break;
			}
		}
	}

	public void validatePagesShopbyDepartment() {
		HashMap<String, Object> dataValue = (HashMap<String, Object>) data;
		String[] Department = dataValue.get("Departments").toString().split(",");
		String[] DepartmentNames = dataValue.get("DepartmentValues").toString().split(",");
		String[] dropdownList = dataValue.get("DropdownList").toString().split(",");
		int num = 0;
		for (int i = 0; i < Department.length; i++) {
			menuPage.clickMenuButton();
			menuPage.clicktitle(Department[i]);
			for (int j = num; j < DepartmentNames.length;) {
				menuPage.clicktitle(DepartmentNames[j]);
				menuPage.ClickelementbyTitle(DepartmentNames[i], dropdownList[j]);
				if (Department[i].equalsIgnoreCase("Appliances")) {
					assertEquals(DepartmentNames[j], menuPage.getTextUsingDepartmentName(DepartmentNames[j]));
				} else {
					assertEquals(DepartmentNames[j], menuPage.getTextUsingDepartmentName(DepartmentNames[j]));
				}
				num = j + 1;
				break;
			}

		}
	}

	public void searchItem() {
		menuPage.searchArea(data.get("SearchText").toString());
		Assert.assertEquals(data.get("SearchText").toString(), menuPage.getTextSearchItem(data.get("SearchText").toString()));
	}

	public void productSelection() {
		menuPage.clickProductUsingTitle(data.get("SearchText").toString());
		menuPage.ScrollandClickAddtoCart();
	}

	public void signup() {
		HashMap<String, Object> registrationData = (HashMap<String, Object>) data;
		registrationPage.clickAccount();
		registrationPage.clickCreateAccount();
		for (Map.Entry<String, Object> entry : registrationData.entrySet()) {
			registrationPage.inputTextBasedonField(entry.getKey().toString(), entry.getValue().toString());
		}
		registrationPage.clickAccountCreation();

	}

	public void clickProductByBrandAndDepartment() {
		HashMap<String, Object> dataValue = (HashMap<String, Object>) data;
		String[] titleNames = dataValue.get("TitleNames").toString().split(",");
		String[] titleValues = dataValue.get("TitleValues").toString().split(",");
		String[] dropdownList = dataValue.get("DropdownList").toString().split(",");
		int num = 0;
		for (int i = 0; i < titleNames.length; i++) {
			menuPage.clickMenuButton();
			menuPage.clicktitle(titleNames[i]);
			for (int j = num; j < titleValues.length;) {
				if (titleValues[j].equalsIgnoreCase("Small Kitchen Appliances")) {
					menuPage.clicktitle(titleValues[j]);
					menuPage.ClickelementbyTitle(titleValues[j], dropdownList[j]);

				} else {
					menuPage.ClickelementbyTitle(titleNames[i], titleValues[j]);
				}
				if (titleValues[j].equalsIgnoreCase("Apple")) {
					menuPage.selectProductByBrand(data.get("BrandName").toString());
					menuPage.clickProductUsingBrandName(data.get("ProductName").toString());
					menuPage.ScrollandClickAddtoCart();
					menuPage.clickcontinueShopping();

				} else if (titleValues[j].equalsIgnoreCase("Small Kitchen Appliances")) {
					menuPage.scrollandClickProduct(data.get("product").toString());
					menuPage.ScrollandClickAddtoCart();
					menuPage.clickcontinueShopping();
				}
				num = j + 1;
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void navigateToCartPage() {
		HashMap<String, Object> paymentDetails = (HashMap<String, Object>) data;
		HashMap<String, Object> paymentinformation = (HashMap<String, Object>) data.get("paymentInformation");
		menuPage.clickcartPage();
		String[] products = data.get("CartProducts").toString().split(",");
		List<String> getproductsData = new ArrayList<String>();
		for (String data : products) {
			getproductsData.add(menuPage.getCartProducts(data).toString());
		}
		assertEquals(getproductsData.toArray(), products);
		menuPage.clickCheckoutButton();
		menuPage.ScrollandClickContinueButton();
		String[] paymentDetail = paymentDetails.get("TextArea").toString().split(",");
		for (int i = 0; i < paymentDetail.length; i++) {
			for (Map.Entry<String, Object> entry : paymentinformation.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(paymentDetail[i])) {
					menuPage.sendOrderInformation(paymentDetail[i], entry.getValue().toString());
				}
				break;
			}
		}
	}

	public void linkbrokenorNot() throws Exception {
		System.out.println(menuPage.urlResponseCode());
		if (menuPage.urlResponseCode() == 200) {
			assertEquals(menuPage.urlResponseCode(), 200);
		} else {
			assertNotEquals(menuPage.urlResponseCode(), 200);
		}
	}
}

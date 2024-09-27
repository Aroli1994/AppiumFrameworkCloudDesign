package org.rahulshettyacademy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.rahulshettyacademy.TestUtils.AndroidBaseTest;
import org.rahulshettyacademy.pageObjects.android.CartPage;
import org.rahulshettyacademy.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {

	@BeforeMethod(alwaysRun = true)
	public void preSetUp() {
		// screen to homepage
		formPage.setAppActivity();
	}

	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void FillForm(HashMap<String, String> input) {

		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage goToCart = productCatalogue.goToCartPage();

		formPage.waitForElementToAppear(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),
				driver);

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.attributeContains(
//				driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

		double actualSum = goToCart.getProductsSum();
		double expectedSum = goToCart.getTotalAmountDisplayed();
		Assert.assertEquals(actualSum, expectedSum, "Sum of product prices not matching with expected price");

		Assert.assertEquals(goToCart.acceptTermsAndConditons(), "Terms Of Conditions");

		goToCart.submitOrder();

//		Thread.sleep(3000);
//
//		// Hybrid ->Google page
//
//		Set<String> contexts = driver.getContextHandles();
//		for (String contextName : contexts) {
//			System.out.println(contextName);
//		}
//		driver.context("WEBVIEW_com.androidsample.generalstore"); // chromedriver.exe
//		driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
//
//		driver.context("NATIVE_APP");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")
				+ "//src//test//java//org//rahulshettyacademy//testData//eCommerce.json");
		// return new Object[][] { { "rahul shetty", "female", "Argentina" }, { "Krishna
		// shetty", "female", "Angola" } };
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}

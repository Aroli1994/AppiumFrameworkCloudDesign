package org.rahulshettyacademy;

import org.openqa.selenium.By;
import org.rahulshettyacademy.TestUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc_1 extends AndroidBaseTest {

	@BeforeMethod
	public void preSetUp() {
		//screen to homepage
		formPage.setAppActivity();
	}

	@Test
	public void FillForm_ErrorValidation() {
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");

		Assert.assertEquals(toastMessage, "Please enter your name");

	}

	@Test
	public void FillForm_Positive() {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vinay Krishna");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);
	}
}

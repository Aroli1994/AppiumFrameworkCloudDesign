package org.rahulshettyacademy.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {
	AndroidDriver driver;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement maleOption;

	@AndroidFindBy(id = "android:id/text1")
	private WebElement countryDropdown;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@SuppressWarnings("deprecation")
	public void setAppActivity() {
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
//				"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
		 Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity")
			      .setOptionalIntentArguments("package:com.android.chrome");
			  driver.startActivity(activity);
	}

	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void setGender(String gender) {
		if (gender.contains("female"))
			femaleOption.click();
		else
			maleOption.click();
	}

	public void setCountrySelection(String countryName) {
		countryDropdown.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
	}

	public ProductCatalogue submitForm() {
		shopButton.click();
		return new ProductCatalogue(driver);
	}

}

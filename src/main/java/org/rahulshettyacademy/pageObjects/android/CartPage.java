package org.rahulshettyacademy.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayPrice;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
	private WebElement alertTitle;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement closeButton;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement sendMeCheckBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public Double getProductsSum() {
		double totalSum = 0;
		for (int i = 0; i < productPrices.size(); i++) {
			String amountString = productPrices.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum = totalSum + price; // 160.97 + 120 = $280.97
		}
		return totalSum;
	}

	public Double getTotalAmountDisplayed() {
		return getFormattedAmount(displayPrice.getText());
	}

	public String acceptTermsAndConditons() {
		longPressAction(terms);
		String title = alertTitle.getText();
		closeButton.click();
		return title;
	}

	public void submitOrder() {
		sendMeCheckBox.click();
		proceedButton.click();
	}

}

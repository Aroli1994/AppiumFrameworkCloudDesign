package org.rahulshettyacademy.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtils {

	IOSDriver driver;

	public IOSActions(IOSDriver driver) {
		this.driver = driver;
	}
	
	public void longPressGesture(WebElement ele) {
		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", 5);

		driver.executeScript("mobile: touchAndHold", params);
	}

	public void scrollToWebElement(WebElement ele) {
		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("direction", "down");

		driver.executeScript("mobile: scroll", params);
	}
	
	public void swipeGesture(WebElement ele) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("direction", "left");
		// params1.put("element", ((RemoteWebElement)ele).getId());
		driver.executeScript("mobile: swipe", params);
	}

}

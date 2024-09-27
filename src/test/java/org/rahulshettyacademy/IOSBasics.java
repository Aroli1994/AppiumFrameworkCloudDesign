package org.rahulshettyacademy;

import org.rahulshettyacademy.TestUtils.IOSBaseTest;
import org.rahulshettyacademy.pageObjects.ios.AlertViews;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSBasics extends IOSBaseTest {
	
	@Test
	public void IOSBasicsTest() {
		//Locators ->id, accessibilityId, Xpath, classname, IOS, iOSClassChain, iOSPredicateString
		
		AlertViews alertViews = homePage.selectAlertViews();
		
		//Xpath - Xml language
		//iOSClassChain, iOSPredicateString - faster than xpath usage 
		
		alertViews.fillTextLabel("Hello World");
		
		String message = alertViews.getConfirmMessage();
		Assert.assertEquals(message, "A message should be a short, complete sentence.");
		
		alertViews.confirmPopup();
		
	}

}

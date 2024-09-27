package org.rahulshettyacademy.pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.IOSActions;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends IOSActions {

	IOSDriver driver;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")
	private WebElement textEntryMenu;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement textInputBox;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'OK'`]")
	private WebElement acceptPopup;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")
	private WebElement confirmMenuItem;
	
	@iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'a message'")
	private WebElement confirmMessage;
	
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
	private WebElement confirmButton;
	
	
	public AlertViews(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void fillTextLabel(String data) {
		textEntryMenu.click();
		textInputBox.sendKeys(data);
		acceptPopup.click();
	}
	
	public String getConfirmMessage() {
		confirmMenuItem.click();
		return confirmMessage.getText();
	}
	
	public void confirmPopup() {
		confirmButton.click();
	}

}

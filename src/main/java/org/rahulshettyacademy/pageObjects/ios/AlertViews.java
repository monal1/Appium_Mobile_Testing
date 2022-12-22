package org.rahulshettyacademy.pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.IOSActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends IOSActions {

	// Global object driver, which is Local object to our class
	// This is current class instance
	IOSDriver driver;

	// HomePage Constructor: To set/push the driver
	public AlertViews(IOSDriver driver) {
		// From your child class you need to pass the driver to your parent class
		super(driver);
		// local variable using this. = real driver coming from constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Create variable to store object/ locators
	// driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label
	// == 'Text Entry'`]")).click();
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")
	private WebElement textEntryMenu;

	// driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField")).sendKeys("Hello
	// World");
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
	private WebElement textBox;

	// driver.findElement(AppiumBy.iOSNsPredicateString("type ==
	// 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel' ")).click();
	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel' ")
	private WebElement confirmMenuItem;

	// driver.findElement(AppiumBy.iOSNsPredicateString(" type ==
	// 'XCUIElementTypeStaticText' AND name BEGINSWITH[c] 'A message'")).getText();
	@iOSXCUITFindBy(iOSNsPredicate = " type == 'XCUIElementTypeStaticText' AND name BEGINSWITH[c] 'A message'")
	private WebElement confirmMessage;

	// driver.findElement(AppiumBy.iOSNsPredicateString("label ==
	// 'Confirm'")).click();
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
	private WebElement submit;

	// driver.findElement(AppiumBy.accessibilityId("OK")).click();
	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement acceptPopup;

	// Action Methods
	public void fillTextLabel(String name) {
		textEntryMenu.click();
		textBox.sendKeys(name);
		acceptPopup.click();
	}

	public String getConfirmMessage() {
		confirmMenuItem.click();
		return confirmMessage.getText();
	}

	public void clickConfirm() {
		submit.click();
	}

}

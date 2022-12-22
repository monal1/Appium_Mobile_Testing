package org.rahulshettyacademy.pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.IOSActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;



public class HomePage extends IOSActions {
	
	// Global object driver, which is Local object to our class
	// This is current class instance
	IOSDriver driver;
	
	// HomePage Constructor: To set/push the driver
	public HomePage(IOSDriver driver) {
		// From your child class you need to pass the driver to your parent class
		super(driver);
		//local variable using this. = real driver coming from constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// Create variable to store object/ locators
	// driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
	@iOSXCUITFindBy(accessibility="Alert Views")
	private WebElement alertViews;
	
	
	// Action Methods
	public AlertViews selectAlertViews() {
		alertViews.click();
		return new AlertViews(driver);
	}
	
}

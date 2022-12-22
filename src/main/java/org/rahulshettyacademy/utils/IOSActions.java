package org.rahulshettyacademy.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtils{
	// Global object driver, which is Local object to our class
	// This is current class instance
	IOSDriver driver;

	// Class Constructor
	public IOSActions(IOSDriver driver) {

		// local variable using this. = real driver coming from constructor
		this.driver = driver;
	}

	public void iosLongPressAction(WebElement ele, int time) {
		// IOS Long Press Gesture
		Map<String, Object> params = new HashMap<>();
		// key value pairs
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", time); // it went from 0 to 10
		driver.executeScript("mobile:touchAndHold", params);
	}

	public void iosScrollAction(WebElement ele, String action) {
		// IOS Scroll Gesture
		Map<String, Object> params = new HashMap<>();
		// key value pairs
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("direction", "down"); // mandatory step
		driver.executeScript("mobile:scroll", params);
	}

	public void iosSwipeAction(WebElement ele, String direction) {
		// IOS Scroll Gesture
		Map<String, Object> params = new HashMap<>();
		// key value pairs
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("direction", "left"); // mandatory step
		driver.executeScript("mobile:swipe", params);
	}

}

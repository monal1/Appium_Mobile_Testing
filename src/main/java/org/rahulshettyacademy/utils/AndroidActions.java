package org.rahulshettyacademy.utils;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AndroidActions extends AppiumUtils{
	
	// Global object driver, which is Local object to our class
	// This is current class instance
	AndroidDriver driver;
	
	// Class Constructor
	public AndroidActions(AndroidDriver driver) {
		
		//local variable using this. = real driver coming from constructor
		this.driver = driver;
	}

	public void longPressAction(WebElement ele) {
		// Long Press Gesture
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}
	
	public void scrollToEndAction() {
		// Appium guys suggesting another type of scrolling
		// If you don't know exactly where to scroll (NO prior Method) then use Appium executeScript scrollGesture method
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			    // Verify and condition if element is present then go and handle it
			));
		}while(canScrollMore);
	}
	
	public void scrollToText(String text) {
		// scroll to a text
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	
	}
	
	public void swipeAction(WebElement ele, String direction) {
		// Apply Swipe
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
		
	}
	
	public void dragDropAction(WebElement source, int x, int y) {
		// Drag and Drop
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) source).getId(),
			    "endX", x, // Drag Destination
			    "endY", y
			));
	}
	
}

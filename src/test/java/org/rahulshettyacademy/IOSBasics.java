package org.rahulshettyacademy;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.rahulshettyacademy.TestUtils.IOSBaseTest;
import org.rahulshettyacademy.pageObjects.ios.AlertViews;

public class IOSBasics extends IOSBaseTest {

	@Test
	public void IOSBasicsTest() {

		// We can use locator for IOS:
		// xpath, classname, IOS, iosClassCHain, IOSPredicateString, accessibility id,
		// id
		// selectAlertViews also catching the object of the next page AlertViews
		AlertViews alertViews = homePage.selectAlertViews();
		alertViews.fillTextLabel("Hello World");
		String actualMessage = alertViews.getConfirmMessage();
		AssertJUnit.assertEquals(actualMessage, "A message should be a short, complete sentence.");
		alertViews.clickConfirm();
	}
}

package org.rahulshettyacademy;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.rahulshettyacademy.TestUtils.AndroidBaseTest;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.android.Activity;

public class eCommerce_tc_2 extends AndroidBaseTest {

	@BeforeMethod
	public void preSetup() {

		// Set screen to home page before running any test using Activity
		Activity activity = new Activity("com.androidsample.generalstore",
				"com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}

	@Test
	public void FillForm_PositiveFlow() throws MalformedURLException, InterruptedException {

		// Step 1
		// provide name
		formPage.setNameField("Monk");
		// select radio button for male or female
		formPage.setGender("female");
		// open dropdown to select country Argentia
		formPage.setConturySelection("Argentina");
		// submit form also catching the object of the next page
		formPage.submitForm();
		AssertJUnit.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);
	}

	@Test
	public void FillForm_ErrorValidation() throws MalformedURLException, InterruptedException {

		// Step 1
		// select radio button for male or female
		formPage.setGender("female");
		// open dropdown to select country Argentia
		formPage.setConturySelection("Argentina");
		// submit form also catching the object of the next page
		formPage.submitForm();
		// Thread.sleep(3000);
		// To capture Toast message, using android.widget.Toast also stop (provide name)
		String toastMessage = formPage.getMessage();
		AssertJUnit.assertEquals(toastMessage, "Please enter your name");
		// AssertJUnit.assertEquals(toastMessage, "Please your name");

	}

}

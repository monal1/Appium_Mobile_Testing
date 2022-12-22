package org.rahulshettyacademy.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

	// Global object driver, which is Local object to our class
	// This is current class instance
	AndroidDriver driver;

	// FormPage Constructor: To set/push the driver
	public FormPage(AndroidDriver driver) {
		// From your child class you need to pass the driver to your parent class
		super(driver);
		// local variable using this. = real driver coming from constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// Create variable to store object/ locators
	// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Monk");
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	// driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']"))
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;

	@AndroidFindBy(id = "android:id/text1")
	private WebElement countrySelection;

	// driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private WebElement toastMessage;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;

	// Action Methods
	public void setNameField(String name) {
		// call variable.Actions like click, sendkeys etc
		nameField.sendKeys(name);
		driver.hideKeyboard(); // hide keyboard
	}

	public void setGender(String gender) {
		if (gender.contains("female")) {
			femaleOption.click();
		} else {
			maleOption.click();
		}
	}

	public void setConturySelection(String countryName) {
		countrySelection.click(); // will open dropdown with options
		// scroll till Argentina
		scrollToText(countryName);
		// exceptional case you dont have to create a variable
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
	}

	public ProductCatalogue submitForm() {
		shopButton.click();
		return new ProductCatalogue(driver);
	}

	public String getMessage() {
		return toastMessage.getAttribute("name");
	}

	public void setActivity() {
		// Set screen to home page before running any test using Activity
		Activity activity = new Activity("com.androidsample.generalstore",
				"com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
}

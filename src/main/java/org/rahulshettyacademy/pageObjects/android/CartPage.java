package org.rahulshettyacademy.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	// Global object driver, which is Local object to our class
	// This is current class instance
	AndroidDriver driver;

	// CartPage Constructor: To set/push the driver
	public CartPage(AndroidDriver driver) {
		// From your child class you need to pass the driver to your parent class
		super(driver);
		// local variable using this. = real driver coming from constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;

	// driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;

	// driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	public WebElement terms;

	// driver.findElement(By.id("android:id/button1"))
	@AndroidFindBy(id = "android:id/button1")
	public WebElement acceptButton;

	// driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	public WebElement proceed;

	// driver.findElement(AppiumBy.className("android.widget.CheckBox"))
	@AndroidFindBy(className = "android.widget.CheckBox")
	public WebElement checkBox;

	public List<WebElement> getProductList() {
		return productList;
	}

	public double getProductSum() {
		int count = productList.size();
		double totalSum = 0;
		for (int i = 0; i < count; i++) {
			String amountString = productList.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum = totalSum + price; // or sum += price
		}
		return totalSum;
	}

	public double getTotalAmountDisplayed() {
		return getFormattedAmount(totalAmount.getText());
	}

	public void acceptTermsConditions() {
		longPressAction(terms);
		acceptButton.click();

	}

	public void submitOrder() {
		checkBox.click();
		proceed.click();
	}

}

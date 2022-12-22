package org.rahulshettyacademy.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//GrandParent(AppiumUtils)  -->Parent(AndroidActions) -->(Child)ProductCatalogue
public class ProductCatalogue extends AndroidActions {
	
	// Global object driver, which is Local object to our class
	// This is current class instance
	AndroidDriver driver;
	
	// ProductCatalogue Constructor: To set/push the driver
	public ProductCatalogue(AndroidDriver driver) {
		// From your child class you need to pass the driver to your parent class
		super(driver);
		//local variable using this. = real driver coming from constructor
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// Create variable to store object/ locators
	// driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cart;
		
	// Action Methods
	public void addItemToCartByIndex(int index) {
		addToCart.get(index).click();
	}
	
	public CartPage goToCartPage() throws InterruptedException {
		cart.click();
		Thread.sleep(2000);
		return new CartPage(driver);
	}
}

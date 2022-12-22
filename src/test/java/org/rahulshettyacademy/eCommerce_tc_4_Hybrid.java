package org.rahulshettyacademy;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import org.rahulshettyacademy.TestUtils.AndroidBaseTest;
import org.rahulshettyacademy.pageObjects.android.CartPage;
import org.rahulshettyacademy.pageObjects.android.ProductCatalogue;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {
	
	@BeforeMethod(alwaysRun=true)
	public void preSetup() {
		// Set form back to Home Page
		formPage.setActivity();
	}

	@Test(dataProvider = "getData", groups= {"Smoke"})
	public void FillForm(HashMap<String, String> input) throws MalformedURLException, InterruptedException {

		// Calling ExtentReport for every test you will create entry at the beginning
		// but with testNG listener in TestNG it will help to do automatically
		
		// Create object for FormPage
		// Initialize this first object in BaseTest.java instead of here
		// FormPage formPage = new FormPage(driver);

		// Step 1
		// provide name
		formPage.setNameField(input.get("name"));

		// select radio button for male or female
		formPage.setGender(input.get("gender"));

		// open dropdown to select country Argentina
		formPage.setConturySelection(input.get("country"));

		// submit form also catching the object of the next page
		ProductCatalogue productCatalogue = formPage.submitForm();

		// Step 2 Add two products (2 ways) * ADD TO CART changes to ADDED TO CART so
		// using same index 0
		// Now this step not required: ProductCatalogue productCatalogue = new
		// ProductCatalogue(driver);
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);

		// click on cart button
		CartPage cartPage = productCatalogue.goToCartPage();

		Thread.sleep(3000);
		// Lets put wait or explicit wait for cart title
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

		// Step 3 Verify the total sum of products with display sum amount
		// Get 2 product
		double totalSum = cartPage.getProductSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		AssertJUnit.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

		// Hybrid APP --- Land on BrowserWeb page Google page we will not do here
		 
	}
	
	// Creating a Data Provider to make test data free
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//testData//eCommerce.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
		// return new Object[][] { {"Monk", "female", "Argentina"}, {"shetty A", "male", "Argentina"} };
	}
	
	// Key --name, value - Monk
	//  {   {hash},   {hash}  }   so data.get(0)
}

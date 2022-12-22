package org.rahulshettyacademy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	// Global variables
	public AppiumDriverLocalService service;
	
	/*
	 * No more required // Global object driver, which is Local object to our class
	 * // This is current class instance // Will work for both Android and IOS
	 * drivers AppiumDriver driver;
	 * 
	 * // AppiumUtils Constructor: To set/push the driver public
	 * AppiumUtils(AppiumDriver driver) { // local variable using this. = real
	 * driver coming from constructor this.driver = driver;
	 * PageFactory.initElements(new AppiumFieldDecorator(driver), this); }
	 */

	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

	// WebElement will come from pageObject file
	public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
	}

	// Read file utility Code
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		// new File(System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//testData//eCommerce.json"

		// convert json file content to json string, Java accepts paths in double
		// slashes
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		// will read JSON value and convert to list of HashMaps
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	// Starting the Appium Server
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		// Adding env map<String with Path values> ref:
		// https://github.com/appium/appium/issues/15418
		Map<String, String> env = new HashMap<String, String>(System.getenv());
		env.put("ANDROID_HOME", "/Users/monmodha1/Library/Android/sdk");
		env.put("JAVA_HOME", "/Library/Java/JavaVirtualMachines/jdk-11.0.15.1.jdk/Contents/Home");

		// Code to start server programmatically using AppiumServiceBuilder
		// Need to tell where is main.js file, and which ip address and which port,
		// which environment
		// Now service instance is store here in AppiumDriverLocalService
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
				.withIPAddress(ipAddress).usingPort(port).withEnvironment(env).build();

		// Go head and start service using the above service instance
		service.start();
		return service;

	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		// it will take screen shot and put it in framework so we need that path in reporting
		// Step 1 Capture and put it in your folder
		// Step 2 Extent report has to pick file and attached to report
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile)); // copyFile expects (File, File);
		return destinationFile;
	}

}

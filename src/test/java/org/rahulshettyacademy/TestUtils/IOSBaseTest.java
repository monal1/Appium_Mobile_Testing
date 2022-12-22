package org.rahulshettyacademy.TestUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.rahulshettyacademy.pageObjects.ios.HomePage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

// This is reusable utility
public class IOSBaseTest extends AppiumUtils{

	public IOSDriver driver;
	// In framework section you can do protected or private
	public AppiumDriverLocalService service;
	public HomePage homePage;

	@BeforeClass
	public void configureAppium() throws IOException {
		
		/* Moved to AppiumUtils
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
				.withIPAddress("127.0.0.1").usingPort(4723).withEnvironment(env).build();

		// Go head and start service using the above service instance
		service.start();
		*/
		
		Properties prop = new Properties();
		FileInputStream fts = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//rahulshettyacademy//resources//data.properties");
		
		prop.load(fts); // have knowledge of key value pair from properties file
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");

		// start Appium service
		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		// For IOS we use XCUITestOptions driver
		XCUITestOptions options = new XCUITestOptions();

		// Set device name
		options.setDeviceName(prop.getProperty("IOSDeviceName"));

		// For Real Device
		// options.setDeviceName("IOS Device");

		// Set App path for ios App
		// options.setApp("/Users/monmodha1/Library/Developer/Xcode/DerivedData/UIKitCatalog-gfgdhnfrwthfnyetskvfbuvrhzch/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
		// options.setApp("/Users/monmodha1/eclipse-workspace/AppiumFrameworkDesign/src/main/java/org/rahulshettyacademy/resources/UIKitCatalog.app");
		// options.setApp("/Users/monmodha1/eclipse-workspace/AppiumFrameworkDesign/src/main/java/org/rahulshettyacademy/resources/TestApp
		// 3.app");
		options.setApp(System.getProperty("user.dir")+"//src//test//java/org//rahulshettyacademy//resources//UIKitCatalog.app");

		// Set ios mobile verson number or platform version
		options.setPlatformVersion(prop.getProperty("IOSPlatformVersion"));

		// Timeout command: Appium-> Webdriver Agent-> IOS Apps
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		// First tell Appium what kind of driver we are using
		// So we need to create object of Android Driver class
		// AndroidDriver for Android apps
		// IOSDriver object for automating IOS apps
		// Appium client code --> Appium Server --> Mobile device
		// Appium Server has capabilities to interpret all Appium client code and then
		// it decide
		// Below we need to pass Where is the Server started and listening(Server
		// detail), Capabilities so -
		// What operating system you want to execute ? IOS or ANDROID or version number
		// etc
		// Set IOSDriver with options and URL
		// driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
		// because service object knows all about all service details like url etc
		driver = new IOSDriver(service.getUrl(), options);

		// Global timeouts
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Create first object for ios tests
		homePage = new HomePage(driver);
	}

	@AfterMethod
	@AfterClass
	public void tearDown() {
		// Close driver
		driver.quit();

		// Close service
		service.stop();
	}

}

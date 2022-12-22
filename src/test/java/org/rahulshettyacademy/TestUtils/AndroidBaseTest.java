package org.rahulshettyacademy.TestUtils;


import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.rahulshettyacademy.pageObjects.android.FormPage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

// This is reusable utility in AppiumFrameworkDesign
public class AndroidBaseTest extends AppiumUtils{


	public AndroidDriver driver;
	// In framework section you can do protected or private
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun=true)
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

		// Go haead and start service using the above service instance
		service.start();
		*/
		
		Properties prop = new Properties();
		FileInputStream fts = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//rahulshettyacademy//resources//data.properties");
		// Directly coming from Maven command from terminal if not then use from data.properties
		// Ternary operator
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress"): prop.getProperty("ipAddress");
		
		prop.load(fts); // have knowledge of key value pair from properties file
		// String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		
		// start Appium service
		service = startAppiumServer(ipAddress, Integer.parseInt(port));
		
		// Capabilities related to App
		UiAutomator2Options options = new UiAutomator2Options();

		// On which device you want to automate Emulator or Simulator
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		
		// For Real Device
		// options.setDeviceName("Android Device");
		
		// To Set Chromedriver executable for Hybrid App
		options.setChromedriverExecutable("//Users//monmodha1//eclipse-workspace//Appium//HybridApp_ChromeDriver//chromedriver");

		// Set App and put path of the App
		// options.setApp("//Users//monmodha1//eclipse-workspace//AppiumFrameworkDesign//src//main//java//org//rahulshettyacademy//resources//ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//resources//General-Store.apk");
		
		
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
		// driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		// because service object knows all about all service details like url etc
		driver = new AndroidDriver(service.getUrl(), options);
		
		// Global timeouts
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// First object
		formPage = new FormPage(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		// Close driver
		driver.quit();

		// Close service
		service.stop();
	}

}

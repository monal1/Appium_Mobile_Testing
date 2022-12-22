package org.rahulshettyacademy.TestUtils;

import java.io.IOException;

import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {

	ExtentTest test;
	// Since it is static we call it ClassName.methodName no object creation needed
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	AppiumDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName()); // Mandatory Step
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// 1. Failing the test entry in Report
		test.fail(result.getThrowable());
		
		// 2. Get driver for that particular test
		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3. Screen shot capture code and add to report
		try {
			// addScreenCaptureFromPath(Sting ImagePath, String title) : title is test case name
			// This will add screenshot to the Report
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		// So our report will be closed and it will be ready for you to open
		extent.flush();
	}

}

package utilis;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import helper.Base;
import helper.ExtentManager;


public class TestListener extends Base implements ITestListener {

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {

		ExtentTestManager.getTest().log(Status.FAIL,
				"Test Failed->C:\\Users\\Shrey\\eclipse-workspace1\\Tresgen\\screenshot");
		ExtentTestManager.getTest().fail(result.getThrowable());

		try {

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "\\screenshot\\" + System.currentTimeMillis()
					+ ".png";
			File finaldestination = new File(destination);
			try {
				FileUtils.copyFile(scrFile, finaldestination);
				ExtentTestManager.getTest().fail("Screenshot",
						MediaEntityBuilder.createScreenCaptureFromPath(destination).build());
			} catch (IOException exception1) {
				// TODO Auto-generated catch-block stub.
				exception1.printStackTrace();
			}
		} catch (IllegalArgumentException exception1) {
			// TODO Auto-generated catch-block stub.
			exception1.printStackTrace();
		} catch (SecurityException exception2) {
			// TODO Auto-generated catch-block stub.
			exception2.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}

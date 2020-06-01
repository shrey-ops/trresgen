package utilis;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import helper.ExtentManager; //UserCreated Clasee

public class ExtentTestManager {

	public WebDriver driver;
	private static ITestContext context;

	// ExtentTest test;
	public static Map<String, WebDriver> map1 = new HashMap<String, WebDriver>();
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}

	/*
	 * public void reportLog(String message) { ExtentTest test;
	 * test.log(Status.INFO,message);//For extentTest HTML report //
	 * logger.info("Message: " + message); // Reporter.log(message);
	 * 
	 * }
	 */

	public static ITestContext setContext(ITestContext iTestContext, WebDriver driver) {
		iTestContext.setAttribute("driver", driver);

		return iTestContext;
	}
}

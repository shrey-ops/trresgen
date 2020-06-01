package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.opencsv.CSVReader;

import utilis.ExtentTestManager;

import java.util.HashMap;
import java.util.Properties;

public class Base {

	public static WebDriver driver;
	public WebDriverWait wait;

	public static HashMap<String, String> CSV = new HashMap<String, String>();

	// ChromeDriverLaunchMethod
	public Base() {

		if (driver == null)
			driver = new ChromeDriver();
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, 15);
	}

	// Wait Wrapper Method
	public void waitVisibility(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
	}

	// Click Method
	public void click(By elementBy) {
		waitVisibility(elementBy);
		driver.findElement(elementBy).click();
	}

	// Write Text
	public void writeText(By elementBy, String text) {
		waitVisibility(elementBy);
		driver.findElement(elementBy).sendKeys(text);
	}

// // Read Text
//	public String readText(By elementBy) {
//		waitVisibility(elementBy);
//		return this.driver.findElement(elementBy).getText().trim();
//	}
//
////	// Assert
//	public void assertEquals(By elementBy, String expectedText) {
//		waitVisibility(elementBy);
//		Assert.assertEquals(readText(elementBy), expectedText);
//
//	}

	// Assertion Method
	public void assertions(By elementBy, String expect) throws IOException {

		waitVisibility(elementBy);
		String given1 = driver.findElement(elementBy).getText().trim();
		System.out.println("Success" + given1);

		Assert.assertEquals(given1, expect);

	}

	// Property File (Resource)
	public static String propertyFile(String Key) throws IOException {

		File file = new File(
				"C:\\Users\\Shrey\\eclipse-workspace1\\Tresgen\\src\\main\\java\\resource\\File.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileInput);
		return prop.getProperty(Key);
	}
    
	//CSV File (Resource)
	@SuppressWarnings({ "resource" })
	public static HashMap<String, String> CSVfile() throws IOException {

		String PATH = "C:\\Users\\Shrey\\eclipse-workspace1\\Tresgen\\src\\main\\java\\resource\\credentials.csv";
		CSVReader reader = new CSVReader(new FileReader(PATH));
		String[] csvCell;
		while ((csvCell = reader.readNext()) != null) {
			CSV.put(csvCell[0], csvCell[1]);

		}

		return CSV;
	}

	public void report() {
		ExtentHtmlReporter report = new ExtentHtmlReporter(
				System.getProperty("C:\\Users\\Shrey\\eclipse-workspace1\\Tresgen\\TestReport")
						+ "TresgenReporting.html");
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(report);
		report.config().setDocumentTitle("Tresgeneraciones");
		report.config().setReportName("Tresgeneraciones Report");

	}

	// Reporting in Extent
	public void report(String message) {
		ExtentTestManager.getTest().log(Status.INFO, message);

	}

}

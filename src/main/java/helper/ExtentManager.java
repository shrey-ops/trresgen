package helper;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Shrey Parashar.
 *         Created 08-Jan-2020.
 */
public class ExtentManager {

	private static ExtentReports extent;
    private static String reportFileName = "TresgenReporting"+".html";
  //  private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = "C:\\Users\\Shrey\\eclipse-workspace1\\Tresgen\\TestReport\\";
    private static String reportFileLocation =  reportFilepath + reportFileName;
  
 
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);
       
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setCSS (".black-text { color: #fff !important; }");
 
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Set environment details
        extent.setSystemInfo("Application Name","Tresgen");
        extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");
		
        return extent;
    }
     
    //Create the report path
    private static String getReportPath (String path) {
    	File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                System.out.println("Directory: " +path+ " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " +path);
                return ("C:\\Users\\Shrey\\eclipse-workspace1\\Tresgen\\TestReport\\");
            }
        } else {
            System.out.println("Directory already exists: " +path);
        }
		return reportFileLocation;
    }
 
}
	
	
	


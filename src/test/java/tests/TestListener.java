package tests;

import java.io.File;
import java.io.IOException;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.thread.TestNGThreadFactory;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class TestListener implements ITestListener {
	
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	
	@Override
	public void onStart(ITestContext context) {
		String reportPath = System.getProperty("user.dir") + "/extent-reports/extent-report.html" ;
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setDocumentTitle("My practise project report");
		sparkReporter.config().setReportName("Automation Test Report ");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		
		
	}
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS,"Test passed");
		
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		 test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

	        Object currentClass = result.getInstance();

	        // Ensure the instance is of type BaseTest
	        if (currentClass instanceof BaseTest) {
	            WebDriver driver = ((BaseTest) currentClass).getDriver();

	            // Generate screenshot
	            String screenshotsDir = System.getProperty("user.dir") + "/screenshots/";
	            new File(screenshotsDir).mkdirs(); // Ensure directory exists
	            String screenshotPath = screenshotsDir + result.getName() + ".png";

	            try {
	                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	                FileUtils.copyFile(screenshot, new File(screenshotPath));
	                test.get().addScreenCaptureFromPath(screenshotPath, "Screenshot on failure");
	            } catch (IOException e) {
	                test.get().log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
	            }
	        } else {
	            test.get().log(Status.FAIL, "Failed to retrieve WebDriver instance for screenshot.");
	        }
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		  test.get().log(Status.SKIP, "Test Skipped");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		  if (extent != null) {
	            extent.flush(); // Close the Extent report and write to the file
	}
	
	
	
	
	
	}

}

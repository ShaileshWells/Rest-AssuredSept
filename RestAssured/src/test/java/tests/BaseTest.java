package tests;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeSuite;

	import Reporting.ExtentReportManager;

	public class BaseTest {

	    protected static ExtentReports extent;
	    protected static ExtentTest test;

	    @BeforeSuite
	    public void setUpReport() {
	        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
	        extent = ExtentReportManager.createInstance(reportPath, "API Automation Report", "Rest Assured Test Results");
	    }

	    @AfterMethod
	    public void captureResult(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            test.fail("Test Failed: " + result.getThrowable());
	        } else if (result.getStatus() == ITestResult.SUCCESS) {
	            test.pass("Test Passed");
	        } else if (result.getStatus() == ITestResult.SKIP) {
	            test.skip("Test Skipped: " + result.getThrowable());
	        }
	    }

	    @AfterSuite
	    public void tearDownReport() {
	        extent.flush(); // writes everything to ExtentReport.html
	    }
	}

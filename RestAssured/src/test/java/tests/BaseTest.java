package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setupExtent() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void tearDownExtent() {
        extent.flush();
    }

    // Helper method to create ExtentTest and set ThreadLocal
    public static void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    // Helper method to get current ExtentTest
    public static ExtentTest getTest() {
        return test.get();
    }
}

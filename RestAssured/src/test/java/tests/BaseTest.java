package tests;

import java.lang.reflect.Method;   // ✅ correct import
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {

    protected static ExtentReports extentReports;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setUpReport() {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("API Automation Report");
        sparkReporter.config().setDocumentTitle("Rest Assured Test Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    @BeforeMethod
    public void startTest(Method method) {
        ExtentTest extentTest = extentReports.createTest(method.getName());
        test.set(extentTest);
    }

    @AfterMethod
    public void updateResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip("Test Skipped: " + result.getThrowable());
        } else {
            test.get().pass("Test Passed");
        }
    }

    @AfterSuite
    public void tearDownReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}

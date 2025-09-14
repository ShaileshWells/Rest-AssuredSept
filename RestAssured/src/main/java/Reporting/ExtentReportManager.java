package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    // Change report file name here when calling createInstance (see listener).
    public synchronized static ExtentReports createInstance(String reportFileName) {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/" + reportFileName + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            /* --- Change these to customize the report --- */
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("API Automation Report");      // change display name
            spark.config().setDocumentTitle("Rest Assured Test Report"); // change browser title
            spark.config().setEncoding("utf-8");
            /* -------------------------------------------- */

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Change system info here
            extent.setSystemInfo("Tester", "Shailesh");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Framework", "Rest Assured + TestNG");
        }
        return extent;
    }
}

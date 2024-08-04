package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;	
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.response.Response;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Set up the report before the test suite starts
    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//API_TestReport.html");
        sparkReporter.config().setDocumentTitle("API Test Report");
        sparkReporter.config().setReportName("API Testing");
        sparkReporter.config().setTheme(Theme.DARK);
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Host Name", "PetStore");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User Name", "Sritam");
    }

    // Clean up after all tests have run
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Start logging for individual tests
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    // Log test success
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    // Log test failure and capture API response if available
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        Object[] parameters = result.getParameters();
        for (Object param : parameters) {
            if (param instanceof Response) {
                Response response = (Response) param;
                extentTest.get().log(Status.FAIL, "Response: " + response.asPrettyString());
            }
        }
    }

    // Log test skip
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    // Capture additional details when test fails within certain configurations
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Implement if necessary
    }
}
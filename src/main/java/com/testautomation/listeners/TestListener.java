package com.testautomation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testautomation.reports.ExtentManager;
import com.testautomation.utility.Constants;
import com.testautomation.utility.ScreenshotCapture;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

/**
 * TestListener class implements ITestListener to handle logging and reporting.
 */
public class TestListener implements ITestListener {

    /**
     * Logger for logging test events.
     */
    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    /**
     * ExtentReports instance for generating reports.
     */
    private static final ExtentReports extent = ExtentManager.createInstance();

    /**
     * Called when a test starts.
     *
     * @param result the test result
     */
    @Override
    public void onTestStart(final ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(extentTest);
        log.info("Test started: " + result.getMethod().getMethodName());
    }

    /**
     * Called when a test passes.
     *
     * @param result the test result
     */
    @Override
    public void onTestSuccess(final ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test Passed");
        log.info("Test passed: " + result.getMethod().getMethodName());
    }

    /**
     * Called when a test fails.
     *
     * @param result the test result
     */
    @Override
    public void onTestFailure(final ITestResult result) {
        ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
        log.error("Test failed: " + result.getMethod().getMethodName());
        log.error("Failure reason: " + result.getThrowable());

        // Capture a screenshot for the failed test
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute(Constants.DRIVER);
        try {
            String screenshotPath = ScreenshotCapture.takeFailureScreenshot(driver, result.getMethod().getMethodName());
            ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
            log.info("Screenshot captured for failed test: " + screenshotPath);
        } catch (IOException e) {
            log.error("Error capturing screenshot for failed test: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Called when a test is skipped.
     *
     * @param result the test result
     */
    @Override
    public void onTestSkipped(final ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, "Test Skipped");
        log.warn("Test skipped: " + result.getMethod().getMethodName());
    }

    /**
     * Called when all tests finish.
     *
     * @param context the test context
     */
    @Override
    public void onFinish(final ITestContext context) {
        extent.flush();
        log.info("All tests completed. Report generated.");
    }
}

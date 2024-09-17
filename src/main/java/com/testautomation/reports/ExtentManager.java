package com.testautomation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter; // Updated to ExtentSparkReporter
    private static String reportFilePath = System.getProperty("user.dir") + "/target/test-reports/test-report.html";

    // Thread-safe variable to hold the ExtentTest for each test
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Create and initialize the ExtentReports instance
    public static ExtentReports createInstance() {
        sparkReporter = new ExtentSparkReporter(reportFilePath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Results");
        sparkReporter.config().setReportName("Extent Report");

        // Initialize ExtentReports and attach the ExtentSparkReporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Tester Name");
        return extent;
    }

    // Set the current test in the ThreadLocal
    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    // Get the current test from the ThreadLocal
    public static ExtentTest getTest() {
        return extentTest.get();
    }
}

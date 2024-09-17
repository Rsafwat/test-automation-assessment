package com.testautomation.utility;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots during test execution.
 */
public abstract class ScreenshotCapture {

    // Directory to store screenshots for each test step
    private static final String BASE_SCREENSHOT_PATH = System.getProperty("user.dir") + "/target/testCase_screenshots/";

    // Directory to store screenshots on test failures
    private static final String BASE_FAILURE_SCREENSHOT_PATH = System.getProperty("user.dir") + "/target/failures_screenshots/";

    /**
     * Captures a screenshot for a specific test step and stores it in the directory for the test class.
     *
     * @param driver        WebDriver instance.
     * @param stepName      Description of the test step.
     * @param testClassName The name of the test class for organizing screenshots.
     * @return Path to the saved screenshot file.
     * @throws IOException If saving the screenshot fails.
     */
    public static String takeStepScreenshot(final WebDriver driver, final String stepName, final String testClassName) throws IOException {
        String sanitizedStepName = stepName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = sanitizedStepName + "_" + timestamp;
        String testClassScreenshotDir = BASE_SCREENSHOT_PATH + testClassName + "/";
        return takeScreenshot(driver, fileName, testClassScreenshotDir);
    }

    /**
     * Takes a screenshot on test failure.
     *
     * @param driver   WebDriver instance
     * @param testName The name of the test for which the failure screenshot is taken
     * @return The path of the screenshot file
     * @throws IOException If there is an issue while saving the screenshot
     */
    public static String takeFailureScreenshot(final WebDriver driver, final String testName) throws IOException {
        return takeScreenshot(driver, testName, BASE_FAILURE_SCREENSHOT_PATH);
    }

    /**
     * General method for taking a screenshot with a given base path.
     *
     * @param driver   WebDriver instance
     * @param name     The name of the screenshot file (step or test name)
     * @param basePath The base path for the screenshot
     * @return The path of the screenshot file
     * @throws IOException If there is an issue while saving the screenshot
     */
    private static String takeScreenshot(final WebDriver driver, final String name, final String basePath) throws IOException {
        File screenshotDir = new File(basePath);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();  // Create directories if not exist
        }
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = basePath + name + ".png";
        FileUtils.copyFile(screenshot, new File(screenshotPath));
        return screenshotPath;
    }
}

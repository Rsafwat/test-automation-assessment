package com.testautomation.tests;

import com.testautomation.listeners.TestScreenshotListener;
import com.testautomation.utility.Config;
import com.testautomation.utility.Constants;
import com.testautomation.utility.TestUtil;
import com.testautomation.utility.drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;

/**
 * BaseTest class manages the test lifecycle, including setup and teardown of the WebDriver.
 */
public class BaseTest {
    /**
     * WebDriver instance for browser automation.
     */
    private WebDriver driver;

    /**
     * Logger for logging test events.
     */
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    /**
     * Initialize the configuration before any test suite starts.
     */
    @BeforeSuite
    public void setupConfig() {
        log.info("Setting up configuration...");
        Config.initialize();
    }

    /**
     * Initializes the WebDriver (local or remote) before the test class.
     *
     * @throws MalformedURLException if there is an issue with the remote WebDriver URL
     */
    @BeforeClass
    public void setUp(final ITestContext context) throws MalformedURLException {
        WebDriver actualDriver = DriverFactory.createDriver();
        WebDriverListener listener = new TestScreenshotListener(actualDriver, TestUtil.getTestCaseName(context));
        WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(actualDriver);
        this.driver = decoratedDriver;
        context.setAttribute(Constants.DRIVER, this.driver);
        log.info("Navigating to base URL: " + Config.get(Constants.BASE_URL));
        driver.get(Config.get(Constants.BASE_URL));
    }

    /**
     * Quits the WebDriver after all test methods in the class finish.
     */
    @AfterClass
    public void quitDriver() {
        if (driver != null) {
            log.info("Quitting WebDriver");
            driver.quit(); // Quits the entire session, closing all windows
        }
    }

    /**
     * Returns the WebDriver instance.
     *
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }


}
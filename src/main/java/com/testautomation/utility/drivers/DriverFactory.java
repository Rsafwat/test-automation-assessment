package com.testautomation.utility.drivers;

import com.testautomation.utility.Config;
import com.testautomation.utility.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DriverFactory is responsible for creating and initializing WebDriver instances.
 * It supports local and remote WebDriver setups (Selenium Grid) and various browsers.
 */
public class DriverFactory {
    /**
     * Logger for log relevant driver set up messages
     */
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    /**
     * Creates a WebDriver instance based on the configuration.
     * If grid is enabled, it returns a RemoteWebDriver; otherwise, it creates a local WebDriver.
     *
     * @return WebDriver instance (local or remote)
     * @throws MalformedURLException if there is an issue with the remote WebDriver URL
     */
    public static WebDriver createDriver() throws MalformedURLException {
        if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
            return getRemoteDriver();
        } else {
            return getLocalDriver();
        }
    }

    /**
     * Initializes and returns a RemoteWebDriver for Selenium Grid.
     *
     * @return RemoteWebDriver instance
     * @throws MalformedURLException if the Grid hub URL is invalid
     */
    private static WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = getBrowserCapabilities();

        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);

        log.info("Connecting to Selenium Grid at {}", url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    /**
     * Initializes and returns a local WebDriver instance for the specified browser.
     * Maximizes the browser window for local drivers.
     *
     * @return Local WebDriver instance
     */
    private static WebDriver getLocalDriver() {
        String browser = Config.get(Constants.BROWSER);

        log.info("Initializing local WebDriver for browser: {}", browser);
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case Constants.FIREFOX:
                driver = new FirefoxDriver(new FirefoxOptions());
                break;

            case Constants.CHROME:
            default:
                driver = new ChromeDriver(new ChromeOptions());
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Determines and returns the appropriate browser capabilities for remote execution.
     *
     * @return Capabilities object for the desired browser
     */
    private static Capabilities getBrowserCapabilities() {
        String browser = Config.get(Constants.BROWSER);
        log.info("Setting up browser capabilities for: {}", browser);

        switch (browser.toLowerCase()) {
            case Constants.FIREFOX:
                return new FirefoxOptions();

            case Constants.CHROME:
            default:
                return new ChromeOptions();
        }
    }
}

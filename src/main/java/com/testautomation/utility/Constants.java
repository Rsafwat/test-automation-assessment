package com.testautomation.utility;
/**
 * Utility class for storing constants used across the test framework.
 * Contains configuration values like wait times, browser settings, and grid options.
 */
public abstract class Constants {
    /** Maximum retry attempts for actions. */
    public static final int MAX_ATTEMPTS = 3;

    /** Default wait time in milliseconds for explicit waits. */
    public static final int DEFAULT_EXPLICIT_WAIT = 10000;

    /** Flag to enable/disable Selenium Grid. */
    public static final String GRID_ENABLED = "selenium.grid.enabled";

    /** URL format for Selenium Grid. */
    public static final String GRID_URL_FORMAT = "selenium.grid.urlFormat";

    /** Selenium Grid hub host. */
    public static final String GRID_HUB_HOST = "selenium.grid.hubHost";

    /** Browser name for the test (e.g., chrome, firefox). */
    public static final String BROWSER = "browser";

    /** Constant for Chrome browser. */
    public static final String CHROME = "chrome";

    /** Constant for Firefox browser. */
    public static final String FIREFOX = "firefox";

    /** WebDriver instance used in tests. */
    public static final String DRIVER = "driver";

    /** Base URL of the application under test. */
    public static final String BASE_URL = "base.url";

}

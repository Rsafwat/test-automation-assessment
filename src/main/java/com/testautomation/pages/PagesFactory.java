package com.testautomation.pages;


import org.openqa.selenium.WebDriver;

/**
 * The Class PageObject.
 */
public class PagesFactory {

    /**
     * The driver.
     */
    private WebDriver driver;

    /**
     * Instantiates a new page object.
     *
     * @param newDriver the new driver
     */
    public PagesFactory(final WebDriver newDriver) {
        this.setDriver(newDriver);

    }

    /**
     * Gets the driver.
     *
     * @return the driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Sets the driver.
     *
     * @param newDriver the new driver
     */
    public void setDriver(final WebDriver newDriver) {
        this.driver = newDriver;
    }
}
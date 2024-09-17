package com.testautomation.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Common utility methods to handle common interactions with web elements
 * across different pages using Selenium WebDriver.
 */
public abstract class CommonMethods {
    /**
     * Logger for tracking actions and errors in the CommonMethods class.
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonMethods.class);

    /**
     * Clicks on a web element after waiting for it to be present and clickable.
     *
     * @param driver             the WebDriver instance
     * @param elementToClickOnIt the locator of the element to click
     */
    public static void clickOnElement(final WebDriver driver, final By elementToClickOnIt) {
        new WebDriverWait(driver, Duration.ofMillis(Constants.DEFAULT_EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(elementToClickOnIt));
        new WebDriverWait(driver, Duration.ofMillis(Constants.DEFAULT_EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(elementToClickOnIt)).click();
    }

    /**
     * Performs a mouse hover action over a specified web element.
     *
     * @param driver  the WebDriver instance
     * @param locator the locator of the element to hover over
     */
    public static void mouseHoverOnElement(final WebDriver driver, final By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).perform();
    }

    /**
     * Retrieves the text content of a web element after waiting for it to be present.
     *
     * @param driver  the WebDriver instance
     * @param locator the locator of the element to retrieve the text from
     * @return the text content of the specified web element
     */
    public static String getElementText(final WebDriver driver, final By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofMillis(Constants.DEFAULT_EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(locator));
        return element.getText();
    }

    /**
     * Switch to iframe.
     *
     * @param driver the driver
     * @param iFrame the by
     */
    public static void switchToIframe(final WebDriver driver, final By iFrame) {
        new WebDriverWait(driver, Duration.ofMillis(Constants.DEFAULT_EXPLICIT_WAIT)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
    }


    /**
     * Attempts to click on a specified element multiple times until successful or the maximum
     * number of attempts is reached.
     *
     * @param driver  The WebDriver instance used to interact with the web page.
     * @param locator The By locator used to identify the element to be clicked.
     * @return true if the element is successfully clicked, false if all attempts fail.
     */
    public static boolean retryClick(final WebDriver driver, final By locator) {
        int attempts = 0;

        while (attempts < Constants.MAX_ATTEMPTS) {
            try {
                CommonMethods.clickOnElement(driver, locator);
                return true;
            } catch (Exception e) {
                logger.warn("Attempt " + (attempts + 1) + " failed.");
            }
            attempts++;
        }
        return false;
    }

}

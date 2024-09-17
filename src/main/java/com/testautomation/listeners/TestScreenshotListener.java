package com.testautomation.listeners;

import com.testautomation.utility.ScreenshotCapture;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.IOException;

/**
 * TestScreenshotListener captures a screenshot after specific WebDriver actions.
 * It implements the WebDriverListener interface to capture actions such as navigation,
 * clicking, retrieving text, and switching frames, saving them in the appropriate folder for each test class.
 */
public class TestScreenshotListener implements WebDriverListener {

    /** web driver */
    private WebDriver driver;
    /** Store the test class name */
    private String testClassName;

    /**
     * Constructor to initialize the listener with the WebDriver instance and test class name.
     *
     * @param driver        WebDriver instance used to perform the actions.
     * @param testClassName Name of the test class to create a folder for screenshots.
     */
    public TestScreenshotListener(final WebDriver driver, final String testClassName) {
        this.driver = driver;
        this.testClassName = testClassName;
    }

    /**
     * Takes a screenshot after navigating to a URL.
     */
    @Override
    public void afterGet(final WebDriver driver, final String url) {
        try {
            ScreenshotCapture.takeStepScreenshot(driver, "Navigated_to_" + getUrlDescription(url), testClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a screenshot after retrieving text from an element.
     */
    @Override
    public void afterGetText(final WebElement element, final String result) {
        try {
            ScreenshotCapture.takeStepScreenshot(driver, "Text_retrieved", testClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a screenshot after clicking on an element.
     */
    @Override
    public void afterClick(final WebElement element) {
        try {
            ScreenshotCapture.takeStepScreenshot(driver, "Clicked_on_element", testClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a screenshot after switching to a frame.
     */
    @Override
    public void afterFrame(final WebDriver.TargetLocator targetLocator, final WebElement frameElement, WebDriver driver) {
        try {
            ScreenshotCapture.takeStepScreenshot(driver, "Switched_to_frame", testClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Takes a screenshot after switching to a new window or popup.
     */
    @Override
    public void afterWindow(final WebDriver.TargetLocator targetLocator, final String nameOrHandle, final WebDriver driver) {
        try {
            ScreenshotCapture.takeStepScreenshot(driver, "Switched_to_window_" + nameOrHandle, testClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Takes a screenshot after switching to the parent frame.
     */
    @Override
    public void afterParentFrame(final WebDriver.TargetLocator targetLocator, final WebDriver driver) {
        try {
            ScreenshotCapture.takeStepScreenshot(driver, "Switched_to_parent_frame", testClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a screenshot after switching to the default content.
     */
    @Override
    public void afterDefaultContent(final WebDriver.TargetLocator targetLocator, final WebDriver driver) {
        try {
            ScreenshotCapture.takeStepScreenshot(driver, "Switched_to_default_content", testClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a screenshot after navigating to a URL using WebDriver's navigation.
     */
    @Override
    public void afterTo(final WebDriver.Navigation navigation, final String url) {
        try {
            ScreenshotCapture.takeStepScreenshot(driver, "Navigated_to_" + getUrlDescription(url), testClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cleans and formats the URL for use in file names.
     */
    private String getUrlDescription(final String url) {
        return url.replaceAll("[^a-zA-Z0-9-_]", "_");
    }
}
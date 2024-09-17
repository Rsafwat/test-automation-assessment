package com.testautomation.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class SynchronizationMethods {


    public static WebElement waitTillElementPresence(
            final WebDriver driver,
            final By locator,
            final int timeoutInMillis) {
        return new WebDriverWait(
                driver,
                Duration.ofMillis(timeoutInMillis)).
                until(ExpectedConditions.presenceOfElementLocated(locator));


    }
}

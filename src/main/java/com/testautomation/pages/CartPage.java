package com.testautomation.pages;

import com.testautomation.utility.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the Cart Page and provides methods to interact with cart-related elements.
 */
public class CartPage extends PagesFactory {

    /**
     * Locator for the checkout button.
     */
    private By checkoutButton = By.cssSelector("[data-hook='CheckoutButtonDataHook.button']");

    /**
     * Locator for the 'Got It' button in the order popup.
     */
    private By gotItButton = By.cssSelector("[data-hook='dialog-action']");

    /**
     * Locator for the total price displayed in the cart.
     */
    private By totalPrice = By.cssSelector("[data-hook='Total.formattedValue']");

    /**
     * Locator for the iframe containing the cart modal popup.
     */
    private By cartModelPopUpIframe = By.cssSelector("iframe[src^='https://ecom.wix.com/storefront/cartModal?']");

    /**
     * Initializes the CartPage with the WebDriver instance.
     *
     * @param newDriver the WebDriver instance to interact with the page.
     */
    public CartPage(WebDriver newDriver) {
        super(newDriver);
    }

    /**
     * Clicks the 'Proceed to Checkout' button.
     */
    public void proceedToCheckout() {
        CommonMethods.clickOnElement(getDriver(), checkoutButton);
    }

    /**
     * Handles the order confirmation popup by switching to the popup iframe and clicking the 'Got It' button.
     */
    public void handleOrderPopUp() {
        CommonMethods.switchToIframe(getDriver(), cartModelPopUpIframe);
        CommonMethods.clickOnElement(getDriver(), gotItButton);
        getDriver().switchTo().parentFrame();
    }

    /**
     * Retrieves the total price from the cart.
     *
     * @return The total price as a String.
     */
    public String getTotalPrice() {
        return CommonMethods.getElementText(getDriver(), totalPrice);
    }
}
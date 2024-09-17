package com.testautomation.pages;

import com.testautomation.utility.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the Product Page and provides methods to interact with product-related elements.
 */
public class ProductPage extends PagesFactory {

    /**
     * The locator for the quantity up arrow button used to increase the product quantity.
     */
    private By quantityUpArrow = By.cssSelector("[data-hook='number-input-spinner-up-arrow']");

    /**
     * The locator for the 'Add to Cart' button.
     */
    private By addToCartButton = By.cssSelector("[data-hook='add-to-cart']");

    /**
     * The locator for the 'View Cart' button within the cart iframe.
     */
    private By viewCartButton = By.cssSelector("[data-hook='widget-view-cart-button']");

    /**
     * The locator for the iframe that contains the 'View Cart' button.
     */
    private By viewCartIframe = By.cssSelector("iframe[src^='https://ecom.wixapps.net/storefront/cartwidgetPopup?']");

    /**
     * Initializes the ProductPage with the WebDriver instance.
     *
     * @param newDriver the WebDriver instance to interact with the page.
     */
    public ProductPage(WebDriver newDriver) {
        super(newDriver);
    }

    /**
     * Selects the product color by clicking on the corresponding radio button
     * based on the color provided.
     *
     * @param color The color as test data (e.g., "Black", "White").
     */
    public void selectProductColor(String color) {
        By colorRadioButton = By.xpath(" //input[@aria-label='" + color + "']/ancestor::div//div[contains(@style, 'background-color:')]");
        CommonMethods.clickOnElement(getDriver(), colorRadioButton);
    }

    /**
     * Sets the product quantity by clicking the up arrow a specified number of times.
     *
     * @param quantity The number of items to add to the cart.
     */
    public void setQuantity(int quantity) {
        CommonMethods.mouseHoverOnElement(getDriver(), quantityUpArrow);
        for (int i = 1; i < quantity; i++) {
            CommonMethods.clickOnElement(getDriver(), quantityUpArrow);
        }
    }

    /**
     * Clicks the 'Add to Cart' button.
     */
    public void ClickOnAddToCartButton() {
        CommonMethods.clickOnElement(getDriver(), addToCartButton);
    }

    /**
     * Clicks the 'View Cart' button after adding a product to the cart.
     * and switches to the cart iframe.
     */
    public void clickOnViewCart() {
        ClickOnAddToCartButton();
        CommonMethods.switchToIframe(getDriver(), viewCartIframe);
        CommonMethods.clickOnElement(getDriver(), viewCartButton);
        getDriver().switchTo().parentFrame();
    }
}

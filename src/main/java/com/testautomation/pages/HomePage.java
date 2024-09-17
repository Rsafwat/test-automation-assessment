package com.testautomation.pages;

import com.testautomation.utility.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Represents the Home Page and provides methods to interact with home page elements.
 */
public class HomePage extends PagesFactory {

    /**
     * Locator for the 'Shop' link.
     */
    private By shopLink = By.xpath("//a[@data-testid='linkElement']//p[contains(text(),'Shop')]");

    /**
     * Locator for the Best Seller product link.
     */
    private By bestSellerProduct = By.xpath("//div[contains(@aria-label, 'Best Seller gallery')]//a");

    /**
     * Initializes the HomePage with the WebDriver instance.
     *
     * @param newDriver the WebDriver instance to interact with the page.
     */
    public HomePage(WebDriver newDriver) {
        super(newDriver);
    }


    /**
     * Returns the locator for the Best Seller product.
     *
     * @return the By locator for the Best Seller product.
     */
    public By getBestSellerProduct() {
        return bestSellerProduct;
    }

    /**
     * Clicks the 'Shop' link to navigate to the shop section.
     */

    public void goToShop() {
        CommonMethods.clickOnElement(getDriver(), shopLink);
    }

    /**
     * Selects the Best Seller product by clicking on the corresponding element.
     */

    public void selectBestSellerProduct() {
        CommonMethods.retryClick(getDriver(), bestSellerProduct);
    }
}
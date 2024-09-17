package com.testautomation.tests;

import com.testautomation.pages.CartPage;
import com.testautomation.pages.HomePage;
import com.testautomation.pages.ProductPage;
import com.testautomation.tests.datamodels.BestSellerTestData;
import com.testautomation.utility.Config;
import com.testautomation.utility.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Test class for verifying the total price at checkout for a Best Seller product.
 */
public class BestSellerProductCheckoutTest extends BaseTest {
    /**
     * Test data for the Best Seller product checkout, loaded from an external JSON file.
     */
    private BestSellerTestData testData;

    /**
     * Page object representing the Cart Page interactions.
     */
    private CartPage cartPage;

    /**
     * Page object representing the Home Page interactions.
     */
    private HomePage homePage;

    /**
     * Page object representing the Product Page interactions.
     */
    private ProductPage productPage;

    /**
     * a test method for Verifying that the total price at checkout for a Best Seller product
     * matches the expected value.
     *
     * @param testData for best seller products test data
     */
    @Test(dataProvider = "Best-Seller-Test-Data",
        description = "Verifies that the total price at checkout for a Best Seller product matches the expected value.")
    public void VerifyBestSellerProductTotalPrice(final BestSellerTestData testData) throws InterruptedException, IOException {
        homePage.goToShop();
        homePage.selectBestSellerProduct();
        productPage.selectProductColor(testData.color());
        productPage.setQuantity(Integer.parseInt(testData.quantity()));
        productPage.clickOnViewCart();
        cartPage.proceedToCheckout();
        cartPage.handleOrderPopUp();
        Assert.assertEquals(cartPage.getTotalPrice(), testData.expectedPrice());
    }

    /**
     * Initializes page objects before each test method.
     * This ensures each test method has a clean slate.
     */
    @BeforeMethod
    public void BeforeMethod() {
        productPage = new ProductPage(getDriver());
        cartPage = new CartPage(getDriver());
        homePage = new HomePage(getDriver());

    }

    @DataProvider(name = "Best-Seller-Test-Data")
    public Object[][] getBestSellerTestData() {
        this.testData = JsonUtil.getTestData(
            Config.get("best.seller.product.test.data.Path"),
            BestSellerTestData.class);
        return new Object[][]{{testData}};
    }

}

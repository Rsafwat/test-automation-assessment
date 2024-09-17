package com.testautomation.tests.datamodels;

/**
 * Record class representing test data for the best seller product checkout.
 */
public record BestSellerTestData(
        String color,
        String quantity,
        String expectedPrice
) {


}

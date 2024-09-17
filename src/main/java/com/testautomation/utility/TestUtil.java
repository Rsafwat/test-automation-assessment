package com.testautomation.utility;

import org.testng.ITestContext;

public class TestUtil {

    /**
     * Gets the simple name of the test class from the TestNG context.
     *
     * @param context The TestNG context.
     * @return The simple test class name.
     */
    public static String getTestCaseName(final ITestContext context) {
        String testClassName = context.getCurrentXmlTest().getXmlClasses().get(0).getName(); // Get full class name
        return testClassName.substring(testClassName.lastIndexOf('.') + 1); // Extract simple class name
    }
}

package tests;

import org.testng.annotations.Test;

public class ApiAuthTest extends BaseTest {

    @Test
    public void testApiAuthentication() {
        createTest("API Authentication Test");

        // Example usage
        getTest().info("Starting API authentication test");

        // Your API code here
        // e.g., RestAssured.given() ...

        getTest().pass("API authentication test passed");
    }
}

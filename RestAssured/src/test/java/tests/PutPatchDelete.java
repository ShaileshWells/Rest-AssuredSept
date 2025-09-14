package tests;

import org.testng.annotations.Test;

public class PutPatchDelete extends BaseTest {

    @Test
    public void testPutApi() {
        createTest("PUT API Test");

        getTest().info("Starting PUT API test");

        // Your PUT API logic
        // e.g., RestAssured.given()...

        getTest().pass("PUT API test passed");
    }

    @Test
    public void testPatchApi() {
        createTest("PATCH API Test");

        getTest().info("Starting PATCH API test");

        // Your PATCH API logic
        // e.g., RestAssured.given()...

        getTest().pass("PATCH API test passed");
    }

    @Test
    public void testDeleteApi() {
        createTest("DELETE API Test");

        getTest().info("Starting DELETE API test");

        // Your DELETE API logic
        // e.g., RestAssured.given()...

        getTest().pass("DELETE API test passed");
    }
}

package tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ChainedApiTest {

    // Class-level variable to store 'id' from first API
    private String extractedId;

    // Runs once before all tests in this class
    @BeforeClass
    public void setup() {
        // Set base URL for all API requests
        RestAssured.baseURI = "https://reqres.in/api";
    }

    // First test: Call API and extract 'id' of the first user in page 2
    @Test(priority = 1)
    public void getFirstApiAndExtractId() {
        // Send GET request to /users?page=2
        Response response = given()
        		.header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users?page=2")       
                .then()
                .statusCode(200)            
                .extract()
                .response();                // Extract the response

        // Extract 'id' of the first user from the JSON array
        extractedId = response.jsonPath().getString("data[0].id");

        // Print extracted ID
        System.out.println("Extracted ID from first API: " + extractedId);
    }

    // Second test: Use the extracted 'id' in another API (/users/{id})
    @Test(priority = 2, dependsOnMethods = {"getFirstApiAndExtractId"})
    public void useExtractedIdInNextApi() {
        given()
                .pathParam("id", extractedId)     // Replace {id} in URL with extractedId
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/{id}")               // Call second API
                .then()
                .statusCode(200)                  // Validate HTTP 200 OK
                .body("data.id", equalTo(Integer.parseInt(extractedId))); // Validate response body 'id' matches extractedId
    }
}

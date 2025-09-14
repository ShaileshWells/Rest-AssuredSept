package tests;
	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;
	import org.json.simple.JSONObject;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.equalTo;

	public class Chained_Post_Get {
	    // Store user id created in POST API
	    private String userId;

	    @BeforeClass
	    public void setup() {
	        // Base URI for ReqRes API
	        RestAssured.baseURI = "https://reqres.in/api";
	    }

	    // Step 1: Create a new user
	    @Test(priority = 1)
	    public void createUserAndExtractId() {
	        // Prepare JSON body for POST
	        JSONObject requestBody = new JSONObject();
	        requestBody.put("name", "John Doe");
	        requestBody.put("job", "Software Engineer");

	        // Send POST request
	        Response response = given()
	        		.header("x-api-key", "reqres-free-v1")
	                .contentType(ContentType.JSON)
	                .body(requestBody.toJSONString())
	                .when()
	                .post("/users")
	                .then()
	                .statusCode(201) // Created
	                .extract()
	                .response();

	        // Extract the generated 'id' from POST response
	        userId = response.jsonPath().getString("id");

	        System.out.println("Created User ID: " + userId);
	    }

	    // Step 2: Fetch the user using extracted id
	    @Test(priority = 2, dependsOnMethods = {"createUserAndExtractId"})
	    public void getUserById() {
	        // Call GET /users/{id} using the extracted userId
	        given()
	                .pathParam("id", userId)
	                .header("x-api-key", "reqres-free-v1")
	                .when()
	                .get("/users/{id}")
	                .then()
	                .statusCode(200)
	                // Validate that returned user id matches created user id
	                .body("data.id", equalTo(Integer.parseInt(userId)));
	    }
	}

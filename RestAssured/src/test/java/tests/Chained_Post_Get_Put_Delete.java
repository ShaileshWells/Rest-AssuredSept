package tests;

	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;
	import org.json.simple.JSONObject;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.equalTo;

	public class Chained_Post_Get_Put_Delete {

	    private String userId;

	    @BeforeClass
	    public void setup() {
	        // Local JSON Server URL
	        RestAssured.baseURI = "http://localhost:3000";
	    }

	    // Step 1: Create a new user
	    @Test(priority = 1)
	    public void createUser() {
	        JSONObject requestBody = new JSONObject();
	        requestBody.put("name", "John Doe");
	        requestBody.put("job", "Software Engineer");

	        Response response = given()
	                .contentType(ContentType.JSON)
	                .body(requestBody.toJSONString())
	                .when()
	                .post("/users")
	                .then()
	                .statusCode(201) // Created
	                .extract()
	                .response();

	        userId = response.jsonPath().getString("id");
	        System.out.println("Created User ID: " + userId);
	    }

	    // Step 2: GET user by ID
	    @Test(priority = 2, dependsOnMethods = {"createUser"})
	    public void getUser() {
	        given()
	                .pathParam("id", userId)
	                .when()
	                .get("/users/{id}")
	                .then()
	                .statusCode(200)
	                .body("id", equalTo(Integer.parseInt(userId)))
	                .body("name", equalTo("John Doe"));
	    }

	    // Step 3: Update user using PUT
	    @Test(priority = 3, dependsOnMethods = {"getUser"})
	    public void updateUser() {
	        JSONObject requestBody = new JSONObject();
	        requestBody.put("name", "John Doe Updated");
	        requestBody.put("job", "Senior Engineer");

	        given()
	                .contentType(ContentType.JSON)
	                .pathParam("id", userId)
	                .body(requestBody.toJSONString())
	                .when()
	                .put("/users/{id}")
	                .then()
	                .statusCode(200)
	                .body("name", equalTo("John Doe Updated"))
	                .body("job", equalTo("Senior Engineer"));
	    }

	    // Step 4: Delete user
	    @Test(priority = 4, dependsOnMethods = {"updateUser"})
	    public void deleteUser() {
	        given()
	                .pathParam("id", userId)
	                .when()
	                .delete("/users/{id}")
	                .then()
	                .statusCode(200);

	        // Validate user no longer exists
	        given()
	                .pathParam("id", userId)
	                .when()
	                .get("/users/{id}")
	                .then()
	                .statusCode(404);
	    }
	}


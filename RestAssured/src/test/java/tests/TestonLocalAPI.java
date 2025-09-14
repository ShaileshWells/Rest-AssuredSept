package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TestonLocalAPI extends BaseTest {

	//@Test
	
	public void get() {
		baseURI= "http://localhost:3000/";
		
		given().get("/users")
		.then()
		.statusCode(200)
		.log().all();
	}
	

	   //"id": 5
	
	//@Test
	public void post() {
	    
	    // Create JSON request body
	    JSONObject request = new JSONObject();
	    request.put("firstname", "Thomas");
	    request.put("lastname", "Edison");
	    request.put("subject", "Maths");

	    // Set Base URI
	    baseURI = "http://localhost:3000/";

	    // Perform POST request
	    given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        .body(request.toJSONString())
	    .when()
	        .post("/users")
	    .then()
	        .statusCode(201)  // 201 Created
	        .log().all();     // Log the response
	}
	
	//@Test
		public void put() {
		    
		    // Create JSON request body
		    JSONObject request = new JSONObject();
		    request.put("firstname", "Shanvis");
		    request.put("lastname", "Edison");
		    request.put("subject", "Mathsfff");

		    // Set Base URI
		    baseURI = "http://localhost:3000/";

		    // Perform POST request
		    given()
		        .contentType(ContentType.JSON)
		        .accept(ContentType.JSON)
		        .body(request.toJSONString())
		    .when()
		        .put("/users/7")
		    .then()
		        .statusCode(200)  // 201 Created
		        .log().all();     // Log the response
		}
	
	//@Test
	public void patch() {
	    
	    // Create JSON request body
	    JSONObject request = new JSONObject();
	    
	    request.put("subject", "AIII");

	    // Set Base URI
	    baseURI = "http://localhost:3000/";

	    // Perform Patch request
	    given()
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        .body(request.toJSONString())
	    .when()
	        .patch("/users/7")
	    .then()
	        .statusCode(200)  // 201 Created
	        .log().all();     // Log the response
	}
	
	@Test
	public void delete() {
	    baseURI = "http://localhost:3000/";

	    // Perform delete request
	    when()
	        .delete("/users/7")
	    .then()
	        .statusCode(200)  // 201 Created
	        .log().all();     // Log the response
	    // if data is already deleted status code should be  <404>.
	}
	
}
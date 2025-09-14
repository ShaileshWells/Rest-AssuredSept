package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class TestExample extends BaseTest {
@Test
public void test_1(){
	
	//Response response= RestAssured.get("https://reqres.in/api/users?page=2");
	Response response= get("https://reqres.in/api/users?page=2");

	System.out.println(response.getStatusCode());
	System.out.println(response.getTime());
	System.out.println(response.getBody().asString());
	System.out.println(response.getHeader("content-type"));
	
	int statusCode= response.getStatusCode(); 
	Assert.assertEquals(statusCode, 200);
	
}

	@Test
    public void test_02() {
        // Set base URI
        RestAssured.baseURI = "https://reqres.in/api";

     // if dot not provide will show error synatx error ; to complete the statement
       
        given()
        // Send GET request with API key header
        .header("x-api-key", "reqres-free-v1?")
        .when()
            .get("/users?page=2")
        .then()
            .statusCode(200)
            .body("data[1].id", equalTo(8))// Optional: Validate response body
        
           // .log().headers(); // log only headers
            .log().all();
            //If you want this in pretty / new line format, you can use prettyPrint() or prettyPeek() in Rest Assured.
            // path finder to check path of particular element
     // Print in pretty JSON format (new lines) but new to create object for response
       // System.out.println(response.getBody().prettyPrint());
        
        //You can do this using extract() after then(). That way you don’t explicitly create a Response object.
// Difference: .asString() → one-line JSON. while .asPrettyString() → JSON in new lines & indented.
        
    }



///>>>>>>>>>>>>>>>>>>>>>>>>
@Test
public void test_03() {
    // Set base URI
    RestAssured.baseURI = "https://reqres.in/api";

    // Extract value directly and print in pretty format New LINE
    String responseBody =
        given()
            .header("x-api-key", "reqres-free-v1") // demo header
        .when()
            .get("/users?page=2")
        .then()
            .statusCode(200)
            .body("data[1].id", equalTo(6)) // Validate response body
            .log().headers()                 // log only headers
            .extract()
            .asPrettyString(); // ✅ pretty print in new lines

    System.out.println(responseBody); // clean JSON format
}
}

package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetandPostExample extends BaseTest {

//	@Test
	public void testGet(){
		
		
		baseURI= "https://reqres.in/api/";
		
		given()
		.get("/users?page=2")
		.then().statusCode(200)
		.body("data[4].first_name", equalTo("George"))
		.body("data.first_name", hasItems("George", "Rachel"))
		.log().body();

	}



//@Test
public void testPost_1() {
	
	Map<String, Object> map = new HashMap<>();
	
	/*map.put("name" , "Shailesh");
	//if want name in quote >> "name"=Shailesh > use \" is the escape sequence for a double quote inside a string.
	//but it is not a good approach add library from maven central name is "gson >>> map.put("\"name\"" , "Shailesh");
	
map.put("job", "teacher");
System.out.println(map);
*/
	// JSON.simple » 1.1.1 library

JSONObject request =  new JSONObject();
request.put("name" , "Shailesh");
request.put("job", "teacher");

System.out.println(request.toJSONString());

}

/*	// Create Gson instance
    Gson gson = new Gson();
    String jsonString = gson.toJson(map);
    System.out.println("JSON String: " + jsonString);
}

    //JSONObject.put() → works with org.json.JSONObject.
	//JsonObject.addProperty() → works with Gson’s JsonObject.
	//Gson gson = new Gson(); gson.toJson(map) → converts a Map or object into a JSON string.
*/



@Test
public void testwithPost_3() {
    // Create request body using JSONObject
    JSONObject request = new JSONObject();
    request.put("name", "Shailesh");
    request.put("job", "teacher");

    System.out.println(request.toJSONString());

    baseURI = "https://reqres.in/api";

    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(request.toJSONString())
    .when()
        .post("/users")
    .then()
        .statusCode(201)  // 201 Created is expected
        //.body("name", equalTo("Shailesh"))
        //.body("job", equalTo("teacher"))
        .log().all();
}
}

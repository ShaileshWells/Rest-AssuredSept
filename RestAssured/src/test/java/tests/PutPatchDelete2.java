package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class PutPatchDelete2 extends BaseTest{

    @Test
    public void testPut() {
    	test = extentReports.createTest("PUT API Test - Update User");
        JSONObject request = new JSONObject();
        request.put("name", "Shailesh");
        request.put("job", "teacher");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .header("x-api-key", "reqres-free-v1")
            .body(request.toJSONString())
        .when()
            .put("/users/2")   
        .then()
            .statusCode(200)
            .log().all();
    }

//https://reqres.in/api/users/2
	
    @Test
    public void testPatch() {
    	test = extentReports.createTest("PATCH API Test - Partial Update User");
        JSONObject request = new JSONObject();
        request.put("name", "Shailesh");
        request.put("job", "teacher");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in";

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .header("x-api-key", "reqres-free-v1")
            .body(request.toJSONString())
        .when()
            .patch("/api/users/2")   
        .then()
            .statusCode(200)
            .log().all();
    }


    //URL https://reqres.in/api/users/2
    
    @Test
    public void testDelete() {
    	test = extentReports.createTest("DELETE API Test - Delete User");
        baseURI = "https://reqres.in";
        given()
            .contentType(ContentType.JSON)
            .header("x-api-key", "reqres-free-v1")
        .when()
            .delete("/api/users/2")   
        .then()
            .statusCode(204)
            .log().all();
    }
}
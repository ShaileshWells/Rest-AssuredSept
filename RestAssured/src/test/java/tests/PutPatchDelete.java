package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class PutPatchDelete {

    @Test
    public void testPut() {

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
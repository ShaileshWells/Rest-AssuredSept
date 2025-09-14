package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiAuthTest extends BaseTest {

    @Test
    public void basicAuthTest() {
        test = extent.createTest("Basic Authentication Test");

        baseURI = "https://httpbin.org";

        given()
            .auth().basic("user", "passwd")
        .when()
            .get("/basic-auth/user/passwd")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .body("user", equalTo("user"));
    }
}

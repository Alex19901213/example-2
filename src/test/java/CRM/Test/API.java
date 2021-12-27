package CRM.Test;

import Tools.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class API{
    private User user = new User();

    String host = "https://example.com/";
    String loginAPI = "api/v1/login";

    @Test
    public void PositiveLoginTest(){
        JSONObject request = new JSONObject();

        request.put("email", user.getAdminLogin);
        request.put("password", user.getAdminPassword);

        given()
                .header("Content-Type","application/json")
                .body(request.toJSONString())
        .when()
                .post(host+loginAPI)
        .then()
                .statusCode(200)
                .body("user", is(notNullValue()))
                .body("token", is(notNullValue()));
    }

    @Test
    public void NegativeLoginTest(){
        JSONObject request = new JSONObject();

        request.put("email", user.getAdminLogin);
        request.put("password", user.getWrongPassword);

        given()
                .header("Content-Type","application/json")
                .body(request.toJSONString())
        .when()
                .post(host+loginAPI)
        .then()
                .statusCode(401)
                .body("code", equalTo(1000))
                .body("error", is(notNullValue()));
    }

    public Response getResponseFromPOST(String uri, String api){
        JSONObject requestParams = new JSONObject();

        RestAssured.baseURI = uri;
        RequestSpecification request = given();

        requestParams.put("email", user.getAdminLogin);
        requestParams.put("password", user.getAdminPassword);

        request
                .header("Content-Type","application/json")
                .body(requestParams.toJSONString());

        return request.post(api);
    }

}

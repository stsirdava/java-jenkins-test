import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {

    @Test(groups = "api")
    public void verifyPostExists() {

        Allure.step("Send get request on typicode.com");
        Response response =
                RestAssured
                        .given()
                        .baseUri("https://jsonplaceholder.typicode.com")
                        .when()
                        .get("/posts/1");

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );
    }
}
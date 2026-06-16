package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Backend")
@Feature("Posts API")
@Story("Retrieve Existing Post")
public class ApiTest extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Issue("JIRA-101")
    @TmsLink("TC-001")
    @Test(groups = "api")
    public void verifyPostExists() {

        Allure.step(
                "Send GET request",
                () -> {}
        );

        Response response =
                RestAssured
                        .given()
                        .baseUri(
                                "https://jsonplaceholder.typicode.com"
                        )
                        .when()
                        .get("/posts/1");

        Allure.addAttachment(
                "Response Body",
                response.asPrettyString()
        );

        Assert.assertEquals(
                response.getStatusCode(),
                200
        );
    }
}
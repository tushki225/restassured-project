package test.services.viapi;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import test.utils.ViTokenAzure;

public class TokenAzureService {

    private static final String URL = "https://login.microsoftonline.com/c08d8fb6-f1bb-4e76-859b-9324ff1f0f97/oauth2/v2.0/token";

    public ValidatableResponse getTokenAzure(ViTokenAzure tokenAzure) {

        return RestAssured
                .given()
                .header("Cookie", tokenAzure.getCookie())
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "api://visual-intelligence-api/.default")
                .formParam("client_id", tokenAzure.getClientId())
                .formParam("client_secret", tokenAzure.getSecret())
                .when()
                .post(URL)
                .then();
    }
}

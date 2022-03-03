package test.services.viassessment;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import test.utils.ViUser;

public class TokenService {

    protected static final String GI_API_GATEWAY_ENDPOINT = "https://gi-api-gateway.tkg-rms-"
            + ".eudc01.solera.farm/oauth/token?grant_type=password";

    public ValidatableResponse getToken(ViUser user) {
        return RestAssured
                .given()
                .headers("Authorization", "Basic YXBwLnFhcHRlcl9zeW5jOg==",
                        "country", user.getCountry())
                .contentType("multipart/form-data")
                .multiPart("username", user.getLoginId())
                .multiPart("password", user.getPw())
                .when()
                .post(GI_API_GATEWAY_ENDPOINT)
                .then();
    }
}

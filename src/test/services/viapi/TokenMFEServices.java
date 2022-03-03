package test.services.viapi;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import test.services.VisualIntelligenceServiceBase;
import test.utils.ViStringBuilder;
import test.utils.ViTokenAzure;
import test.utils.ViUser;

public class TokenMFEServices extends VisualIntelligenceServiceBase {

    public ValidatableResponse getTokenMFE(ViUser user, String taskId, String origin, ViTokenAzure tokenAzure) {

        TokenAzureService tokenAzureService = new TokenAzureService();

        ViStringBuilder endPoint = new ViStringBuilder();
        endPoint.append("auth?claimId=" + taskId);

        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get("access_token").toString(),
                        COUNTRY_CODE, user.getCountry(),
                        ORIGIN, origin)
                .when()
                .post(getVIApiURL(endPoint.getStringValue()))
                .then();
    }
}

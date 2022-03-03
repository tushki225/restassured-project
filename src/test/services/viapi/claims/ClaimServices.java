package test.services.viapi.claims;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import test.services.VisualIntelligenceServiceBase;
import test.services.viapi.TokenAzureService;
import test.utils.ViTokenAzure;
import test.utils.ViUser;

public class ClaimServices extends VisualIntelligenceServiceBase {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String CLAIMS = "claims/";

    public ValidatableResponse createClaim(ViUser user, String origin, ViTokenAzure tokenAzure) {
        TokenAzureService tokenAzureService = new TokenAzureService();
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get(ACCESS_TOKEN).toString(),
                        CONTENT_TYPE, APPLICATION_JSON,
                        COUNTRY_CODE, user.getCountry(),
                        LOGIN_ID, user.getLoginId(),
                        ORIGIN, origin)
                .contentType(ContentType.JSON)
                .body("vin")
                .when()
                .post(getVIApiURL(CLAIMS))
                .then();
    }

    public ValidatableResponse getClaim(String claimId, ViUser user, String origin, ViTokenAzure tokenAzure) {
        TokenAzureService tokenAzureService = new TokenAzureService();
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get(ACCESS_TOKEN).toString(),
                        COUNTRY_CODE, user.getCountry(),
                        LOGIN_ID, user.getLoginId(),
                        ORIGIN, origin)
                .when()
                .get(getVIApiURL(CLAIMS + claimId))
                .then();
    }

    public ValidatableResponse createQClaimsDeepLink(String claimId, ViUser user, String origin,
            ViTokenAzure tokenAzure) {
        TokenAzureService tokenAzureService = new TokenAzureService();
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get(ACCESS_TOKEN).toString(),
                        COUNTRY_CODE, user.getCountry(),
                        ORIGIN, origin)
                .when()
                .post(getVIApiURL(CLAIMS + claimId + "/deeplinkDC"))
                .then();
    }
}

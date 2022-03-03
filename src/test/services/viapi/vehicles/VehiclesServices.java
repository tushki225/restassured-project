package test.services.viapi.vehicles;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import test.services.VisualIntelligenceServiceBase;
import test.services.viapi.TokenAzureService;
import test.utils.ViTokenAzure;
import test.utils.ViUser;

public class VehiclesServices extends VisualIntelligenceServiceBase {

    private static final String ACCESS_TOKEN = "access_token";

    public ValidatableResponse getManufacturers(ViUser user, String origin, ViTokenAzure tokenAzure) {
        TokenAzureService tokenAzureService = new TokenAzureService();
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get(ACCESS_TOKEN).toString(),
                        COUNTRY_CODE, user.getCountry(),
                        ORIGIN, origin)
                .when()
                .get(getVIApiURL("manufacturers"))
                .then();
    }

    public ValidatableResponse getModels(ViUser user, String origin, ViTokenAzure tokenAzure, String manufacturer) {
        TokenAzureService tokenAzureService = new TokenAzureService();
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get(ACCESS_TOKEN).toString(),
                        COUNTRY_CODE, user.getCountry(),
                        ORIGIN, origin)
                .when()
                .get(getVIApiURL("manufacturers/" + manufacturer + "/models"))
                .then();
    }

    public ValidatableResponse getSubModels(ViUser user, String origin, ViTokenAzure tokenAzure, String manufacturer,
            String model) {
        TokenAzureService tokenAzureService = new TokenAzureService();
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get(ACCESS_TOKEN).toString(),
                        COUNTRY_CODE, user.getCountry(),
                        ORIGIN, origin)
                .when()
                .get(getVIApiURL("manufacturers/" + manufacturer + "/models/" + model + "/submodel"))
                .then();
    }

}

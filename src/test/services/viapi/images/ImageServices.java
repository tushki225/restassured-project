package test.services.viapi.images;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import test.services.VisualIntelligenceServiceBase;
import test.services.viapi.TokenAzureService;
import test.utils.ImagesBody;
import test.utils.ViStringBuilder;
import test.utils.ViTokenAzure;
import test.utils.ViUser;

import java.io.File;
import java.util.List;

public class ImageServices extends VisualIntelligenceServiceBase {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String CLAIMS = "claims/";

    public ValidatableResponse uploadImage(String claimId, ViUser user, String origin, ViTokenAzure tokenAzure,
                                           String group, String tag, String imageFullPath) {

        ViStringBuilder endPoint = new ViStringBuilder();
        endPoint.append(CLAIMS + claimId + "/images?");
        endPoint.appendIfNotEmpty("group=", group);
        endPoint.appendIfNotEmpty("&tag=", tag);

        TokenAzureService tokenAzureService = new TokenAzureService();

        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get(ACCESS_TOKEN).toString(),
                        COUNTRY_CODE, user.getCountry(),
                        LOGIN_ID, user.getLoginId(),
                        ORIGIN, origin)
                .contentType("multipart/form-data")
                .multiPart("image", new File(imageFullPath))
                .when()
                .post(getVIApiURL(endPoint.getStringValue()))
                .then();
    }

    public ValidatableResponse deleteImage(String claimId, ViUser user, String origin, ViTokenAzure tokenAzure,
            String imageId) {

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
                .delete(getVIApiURL(CLAIMS + claimId + "/images/" + imageId))
                .then();
    }

    public ValidatableResponse getAllImages(String claimId, ViUser user, String origin, ViTokenAzure tokenAzure) {

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
                .get(getVIApiURL(CLAIMS + claimId + "/images"))
                .then();
    }

    public ValidatableResponse uploadImagesUsingArrayUrls(String claimId, ViUser user, String origin,
            ViTokenAzure tokenAzure, List<String> imagesUrls) {

        TokenAzureService tokenAzureService = new TokenAzureService();

        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + tokenAzureService.getTokenAzure(tokenAzure)
                                .extract().jsonPath().get(ACCESS_TOKEN).toString(),
                        COUNTRY_CODE, user.getCountry(),
                        LOGIN_ID, user.getLoginId(),
                        ORIGIN, origin)
                .contentType(ContentType.JSON)
                .body(ImagesBody.getImagesUrlsBody(imagesUrls))
                .when()
                .post(getVIApiURL(CLAIMS + claimId + "/images/url"))
                .then();
    }

}

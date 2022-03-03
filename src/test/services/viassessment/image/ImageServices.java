package test.services.viassessment.image;


import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import test.services.VisualIntelligenceServiceBase;
import test.utils.ViStringBuilder;
import test.utils.ViUser;

import java.io.File;

public class ImageServices extends VisualIntelligenceServiceBase {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String COUNTRY_CODE = "CountryCode";

    public ValidatableResponse submitVehicleInfo(ViUser user, String taskId,
                                                 String imageFullPath, String group,
                                                 String foreignAttachmentId, String tag) {

        ViStringBuilder endPoint = new ViStringBuilder();
        endPoint.append(taskId + "/images?");
        endPoint.appendIfNotEmpty("group=", group);
        endPoint.appendIfNotEmpty("&foreignAttachmentId=", foreignAttachmentId);
        endPoint.appendIfNotEmpty("&tag=", tag);

        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .contentType("multipart/form-data")
                .multiPart("image", new File(imageFullPath))
                .when()
                .post(getViUrl(endPoint.getStringValue()))
                .then();
    }

    public ValidatableResponse getImages(ViUser user, String taskId) {

        ViStringBuilder endPoint = new ViStringBuilder();
        endPoint.append(taskId + "/images");
        return RestAssured.given()
                          .headers(
                                  AUTHORIZATION, BEARER + user.getBearerToken(),
                                  COUNTRY_CODE, user.getCountry())
                          .when()
                          .get(getViUrl(endPoint.getStringValue()))
                          .then();
    }

    public ValidatableResponse deleteImage(ViUser user, String taskId, String imageId) {
        ViStringBuilder endPoint = new ViStringBuilder();
        endPoint.append(taskId + "/images/" + imageId);

        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .when()
                .delete(getViUrl(endPoint.getStringValue()))
                .then();
    }

    public ValidatableResponse submitImage(ViUser user, String taskId,
            String imageFullPath) {

        ViStringBuilder endPoint = new ViStringBuilder();
        endPoint.append(taskId + "/images");

        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .contentType("multipart/form-data")
                .multiPart("image", new File(imageFullPath))
                .when()
                .post(getViUrl(endPoint.getStringValue()))
                .then();
    }


}

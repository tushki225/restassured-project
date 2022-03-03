package test.services.viassessment.vin;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import test.services.VisualIntelligenceServiceBase;
import test.utils.ViUser;

import java.io.File;

public class VinServices  extends VisualIntelligenceServiceBase {

    public ValidatableResponse scanVin(ViUser user, String imageFullPath) {
        return RestAssured
                .given()
                .headers(
                        "Authorization", "Bearer " + user.getBearerToken())
                .contentType("multipart/form-data")
                .multiPart("image", new File(imageFullPath))
                .when()
                .post(getScanVinUrl())
                .then();
    }
}

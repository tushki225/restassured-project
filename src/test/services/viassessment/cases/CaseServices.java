package test.services.viassessment.cases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import test.services.VisualIntelligenceServiceBase;
import test.utils.ViStringBuilder;
import test.utils.ViUser;

import java.util.HashMap;
import java.util.Map;

public class CaseServices extends VisualIntelligenceServiceBase {

    public ValidatableResponse executeCase(ViUser user, String taskId) {
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .when()
                .post(getViUrl(taskId + "/results"))
                .then();
    }

    public ValidatableResponse getResults(ViUser user, String taskId) {
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .when()
                .get(getViUrl(taskId + "/results"))
                .then();
    }

    public ValidatableResponse getStatus(ViUser user, String taskId) {
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .when()
                .get(getViUrl(taskId + "/status"))
                .then();
    }

    public ValidatableResponse updatePartSide(ViUser user, String taskId, String guideNumber, String newDamageSide) {
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("side", newDamageSide);

        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .body(bodyParams)
                .when()
                .contentType(ContentType.JSON)
                .put(getViUrl(taskId + "/results/parts/" + guideNumber + "/side"))
                .then();

    }

    public ValidatableResponse editDamageType(ViUser user, String taskId, String guideNumber, String damageId,
            String newDamageType) {
        ViStringBuilder endPoint = new ViStringBuilder();
        endPoint.append(taskId + "/results/parts/" + guideNumber + "/damages/" + damageId + "/type");

        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("type", newDamageType);

        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .body(bodyParams)
                .when()
                .contentType(ContentType.JSON)
                .put(getViUrl(endPoint.getStringValue()))
                .then();
    }

    public ValidatableResponse noVehicleDataCase(ViUser user, String taskId) {
        ViStringBuilder bodyParams = new ViStringBuilder();
        bodyParams.append("{");
        bodyParams.appendIfNotEmpty("\"countryCode\": \"",
                user.getCountry(), "\",");
        bodyParams.append("\"client\": {");
        bodyParams.appendIfNotEmpty("\"languageCode\": \"",
                user.getLanguage(), "\",");
        bodyParams.appendIfNotEmpty("\"userId\": \"",
                user.getUserId(), "\",");
        bodyParams.appendIfNotEmpty("\"organizationId\": \"",
                user.getOrganizationId(), "\"");
        bodyParams.append("}}");

        return RestAssured
                .given()
                .headers(
                         AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .body(bodyParams.getStringValue())
                .when()
                .contentType(ContentType.JSON)
                .post(getViUrl(taskId))
                .then();
    }
}

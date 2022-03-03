package test.services.viassessment.repairoperation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import test.services.VisualIntelligenceServiceBase;
import test.utils.ViUser;

public class RepairOperationServices extends VisualIntelligenceServiceBase {

    private static final String RESULTS_PARTS = "/results/parts/";

    public ValidatableResponse addRepairOperation(
            ViUser user, String taskId, String guideNumber, String repairOperation) {
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .contentType(ContentType.JSON)
                .body("ChangeRepairOperationBody.getBody(repairOperation)")
                .when()
                .post(getViUrl(taskId + RESULTS_PARTS + guideNumber + "/repairOperations"))
                .then();
    }

    public ValidatableResponse updateWorkUnits(
            ViUser user, String taskId, String guideNumber, String newWorkUnits) {
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .contentType(ContentType.JSON)
                .body("UpdateWorkUnitsBody.getBody(newWorkUnits)")
                .when()
                .put(getViUrl(taskId + RESULTS_PARTS + guideNumber + "/workUnits"))
                .then();
    }

    public ValidatableResponse deleteRepairOperation(
            ViUser user, String taskId, String guideNumber, String repairOperation) {
        return RestAssured
                .given()
                .headers(
                        AUTHORIZATION, BEARER + user.getBearerToken(),
                        COUNTRY_CODE, user.getCountry())
                .contentType(ContentType.JSON)
                .when()
                .delete(getViUrl(taskId + RESULTS_PARTS + guideNumber + "/repairOperations/ " + repairOperation))
                .then();
    }
}

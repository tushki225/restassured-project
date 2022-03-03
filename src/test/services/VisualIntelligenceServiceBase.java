package test.services;

public class VisualIntelligenceServiceBase {

    protected static final String AUTHORIZATION = "Authorization";
    protected static final String BEARER = "Bearer ";
    protected static final String COUNTRY_CODE = "CountryCode";
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String APPLICATION_JSON = "application/json";
    protected static final String ENVIRONMENT = "environment.name";
    protected static final String LOGIN_ID = "loginId";
    protected static final String ORIGIN = "origin";

    protected String getViUrl(String endPoint) {
        return "https://vi-assessment-be.tkg-rms-"
                + ".eudc01.solera.farm/api/v3/" + endPoint;
    }

    protected String getScanVinUrl() {
        return "https://vin-ocr-google.tkg-rms-"
                + ".eudc01.solera.farm/api/v1/analyze";
    }

    protected String getVIApiURL(String endPoint) {
        return "https://visual-intelligence-api.tkg-rms-"
                + ".eudc01.solera.farm/" + endPoint;
    }

}
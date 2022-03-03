package test.utils;

public class ViUser {

    private String loginId;
    private String pw;
    private String bearerToken;
    private String country;
    private String userId;
    private String organizationId;
    private String language;

    public ViUser(String loginId, String pw, String country) {
        this.loginId = loginId;
        this.pw = pw;
        this.country = country;
        TokenService tokenService = new TokenService();
        this.bearerToken = tokenService.getToken(this).extract().body().jsonPath()
                .get("access_token");
    }

    public String getCountry() {
        return country;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPw() {
        return pw;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

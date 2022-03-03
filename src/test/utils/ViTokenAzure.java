package test.utils;

public class ViTokenAzure {

    private String cookie;
    private String clientId;
    private String secret;

    public ViTokenAzure(String cookie, String clientId, String secret) {
        this.cookie = cookie;
        this.clientId = clientId;
        this.secret = secret;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}

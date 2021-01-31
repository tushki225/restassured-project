package test.java.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

public class RestUtils {
    
	public static String path;
    public static String jsonPathTerm;

    //Sets Base URI
    public static void setBaseURI (){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    //Sets base path
    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }

    //Reset Base URI (after test)
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    //Reset base path
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    //Sets ContentType
    public static void setContentType (ContentType type){
        given().contentType(type);
    }

    //Returns response by given path
    public static Response getResponsebyPath(String path) {
        return get(path);
    }

    //Returns response
    public static Response getResponse() {
        return get();
    }

    //Returns JsonPath object
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        System.out.print("Returned json response: " + json);
        return new JsonPath(json);
    }
}

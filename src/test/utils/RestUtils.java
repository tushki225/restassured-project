package test.utils;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class RestUtils {
    
	public static String path;
    public static String jsonPathTerm;      
    
    //Sets Base URI
    public static void setBaseURI (String baseUri){
    	if(!baseUri.isEmpty())
    		RestAssured.baseURI = baseUri;    	
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
    
    //Returns response
    public static Response getResponse() {
        return get();
    }

    //Returns response by given path
    public static Response getResponsebyPath(String path) {
        return get(path);
    }        
	
    
    //Create Resource with request body
    public static Response createResource(String uri, String requestBody) {
    	return given().contentType(Constants.APPLICATION_JSON).body(requestBody).when().post(uri);
    } 
    
    //Updates a Resource with request body
    public static Response updateResource(String uri, String requestBody) {
    	return given().contentType(Constants.APPLICATION_JSON).body(requestBody).when().put(uri);
    }
    
    //Deletes a Resource
    public static Response deleteResource(String uri) {
    	return given().contentType(Constants.APPLICATION_JSON).when().delete(uri);
    }

    //Returns JsonPath object
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }
}

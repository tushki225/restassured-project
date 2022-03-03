package test.utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RestUtils {
    
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
        RestAssured.given().contentType(type);
    }
    
    //Returns response
    public static Response getResponse() {
        return RestAssured.get();
    }

    //Returns response by given path
    public static Response getResponsebyPath(String path) {
        return RestAssured.get(path);
    }        
	
    
    //Create Resource with request body
    public static Response createResource(String uri, String requestBody) {
    	return RestAssured.given().contentType(Constants.APPLICATION_JSON).body(requestBody).when().post(uri);
    } 
    
    //Updates a Resource with request body
    public static Response updateResource(String uri, String requestBody) {
    	return RestAssured.given().contentType(Constants.APPLICATION_JSON).body(requestBody).when().put(uri);
    }
    
    //Deletes a Resource
    public static Response deleteResource(String uri) {
    	return RestAssured.given().contentType(Constants.APPLICATION_JSON).when().delete(uri);
    }

    //Returns JsonPath object
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }

    public JSONObject getJsonParsed(String body) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject)parser.parse(body);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package test.utils;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import java.io.File;
import org.json.simple.JSONObject;

public class RestUtils {
    
	public static String path;
    public static String jsonPathTerm;
    
    @SuppressWarnings("unchecked")
    //Sample
    //Create Json object
	public static JSONObject getJsonObject() {
    	JSONObject requestParams = new JSONObject();
    	requestParams.put("FirstName", "Virender"); 
    	requestParams.put("LastName", "Singh");
    	requestParams.put("UserName", "sdimpleuser2dd2011");
    	requestParams.put("Password", "password1");
    	requestParams.put("Email",  "sample2ee26d9@gmail.com");
    	return requestParams;
    }
    
    //Sample
    //Generates json object form json file
	public static JSONObject getJsonObjectFromJsonFile(File file) {
		JsonPath jsonPath;
		if(file.exists() && !(file==null)) 
			jsonPath= new JsonPath(file);			
		else
			jsonPath= new JsonPath(new File(".\\testdata\\sample.json"));
		
		JSONObject requestParams = new JSONObject(jsonPath.get());   	
    	return requestParams;
    }
    
    //Sample
    public static void sampleRequestWithJsonHeaderObject(String path) {
    	getResponseByHeader(path,getJsonObject());
    	getResponseByHeader(path,getJsonObjectFromJsonFile(null));
    }
    
    
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
	
	  //Returns response 
    public static Response getResponseByHeader(String path, JSONObject header) {
    	return get(path, header.toJSONString());
    }	 

    //Returns JsonPath object
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        //System.out.print("Returned json response: " + json);
        return new JsonPath(json);
    }
}

package test.pages;

import com.jayway.restassured.response.Response;

import test.utils.Constants;
import test.utils.RestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUserObject {
	
	//Get response based on URI
	public Response getResponse(String uri) {     	
		Response response = RestUtils.getResponsebyPath(uri); 
    	return response;    		
    }	
	
	//Validate user existence from given response
	public boolean validateUserExistence(Response response, String name) {    	   	
    	List<Map<String, String>> users = response.jsonPath().getList(Constants.ALL);    	
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).get(Constants.USERNAME).equals(name)) {
    			System.out.println("User exists with name:"+users.get(i).get(Constants.NAME));    			
    			return true;    
    		}    			
        }
    	return false;    		
    }
	
	//Get UserId for specific user from response string
	public Integer getUserId(Response response, String name) { 		
    	List<Map<String, String>> users = response.jsonPath().getList(Constants.ALL);    	
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).get(Constants.USERNAME).equals(name)) { 
    			Integer id=Integer.parseInt(String.valueOf(users.get(i).get(Constants.ID)));
    			return id;    
    		}    			
        }
    	return null;    		
    }	
    
	//Get all posts by specific userid
	public int getPostsByUserId(Response response) {  
		List<Map<String, String>> allPosts = response.jsonPath().getList(Constants.ALL); 		
		System.out.println("Total posts with given userId are::"+allPosts.size());    	
    	return allPosts.size();    		
    }
	
	//Validate email from each post based on regular expressions
	public boolean validateEmailFromEachPost(Response response, String uri) {  
		List<Map<String, String>> allPosts = response.jsonPath().getList(Constants.ALL); 
		System.out.println("Total posts are::"+allPosts.size());
		
		List<String> emails= new ArrayList<String>();
		for (int i = 1; i <= allPosts.size(); i++) {
			Response res = getResponse(uri+"/"+i+"/comments");   //Fetches comment corresponding to each post
			List<Map<String, String>> comments = res.jsonPath().getList(Constants.ALL);
			for (int j = 0; j < comments.size(); j++) {
				emails.add(comments.get(j).get(Constants.EMAIL));
			}
        }
		System.out.println("Total emails are::"+emails.size());
		return validateEmails(emails);
    }
	
	private boolean validateEmails(List<String> emails) {		 
		Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX);
		
		for(String email : emails){
		    Matcher matcher = pattern.matcher(email);
		    if(!matcher.matches())
		    	return false;
		}		
		return true;
	}
	
	//Create post with json params
	public Response createPostWithJson(String uri, String fileName) throws IOException {     	
		String filePath= Constants.BASE_FILE_PATH+fileName;
		String body=new String(Files.readAllBytes(Paths.get(filePath)));
		Response response = RestUtils.createResource(uri, body);		
		return response;		
    }
	
	//Deletes post
	public Response deletePost(String uri) throws IOException {		
		Response response = RestUtils.deleteResource(uri);		
		return response;		
    }
}

package test.pages;

import com.jayway.restassured.response.Response;
import test.utils.RestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUserObject {
	
	public Response getResponse(String uri) {     	
		Response response = RestUtils.getResponsebyPath(uri); 
    	return response;    		
    }	
	
	public boolean validateUserExistence(Response response, String name) {    	   	
    	List<Map<String, String>> users = response.jsonPath().getList("$");    	
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).get("username").equals(name)) {
    			System.out.println("User exists with name:"+users.get(i).get("name"));    			
    			return true;    
    		}    			
        }
    	return false;    		
    }
	
	public Integer getUserId(Response response, String name) { 		
    	List<Map<String, String>> users = response.jsonPath().getList("$");    	
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).get("username").equals(name)) { 
    			Integer id=Integer.parseInt(String.valueOf(users.get(i).get("id")));
    			return id;    
    		}    			
        }
    	return null;    		
    }	
    
	public int getPostsByUserId(Response response) {  
		List<Map<String, String>> allPosts = response.jsonPath().getList("$"); 		
		System.out.println("Total posts with given userId are::"+allPosts.size());    	
    	return allPosts.size();    		
    }
	
	public boolean validateEmailFromEachPost(Response response, String uri) {  
		List<Map<String, String>> allPosts = response.jsonPath().getList("$"); 
		System.out.println("Total posts are::"+allPosts.size());
		
		List<String> emails= new ArrayList<String>();
		for (int i = 1; i <= allPosts.size(); i++) {
			Response res = getResponse(uri+"/"+i+"/comments");   //Fetches comment corresponding to each post
			List<Map<String, String>> comments = res.jsonPath().getList("$");
			for (int j = 0; j < comments.size(); j++) {
				emails.add(comments.get(j).get("email"));
			}
        }
		System.out.println("Total emails are::"+emails.size());
		return validateEmails(emails);
    }
	
	private boolean validateEmails(List<String> emails) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";		 
		Pattern pattern = Pattern.compile(regex);
		
		for(String email : emails){
		    Matcher matcher = pattern.matcher(email);
		    if(!matcher.matches())
		    	return false;
		}		
		return true;
	}
}

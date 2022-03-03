package test.scenarios;

import java.io.IOException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.config.Config;
import test.pages.TestUserObject;
import test.utils.Constants;

public class UserTestSteps extends Config{
	
	public Response response = null;
    TestUserObject testUserObj = new TestUserObject();
    
    @Test
    @Parameters({"username"})
    public void checkUserExistence(String username, ITestContext context) {
    	System.out.println(Constants.TESTS_STARTED + context.getName());     	
    	
    	response = testUserObj.getResponse(usersURI);
    	System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");  
    	Assert.assertEquals(testUserObj.validateUserExistence(response, username), true ,"User not found...");    	
    	
    	System.out.println(Constants.TESTS_ENDED + context.getName());
    }
    
    @Test
    @Parameters({"username"})
    public void searchPostsByUser(String username, ITestContext context) { 
    	System.out.println(Constants.TESTS_STARTED + context.getName());
    	
    	//Gets userid of specific user with username
    	response = testUserObj.getResponse(usersURI);
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Integer userId= testUserObj.getUserId(response, username);
    	Assert.assertNotNull(userId, "No user exists with mentioned name!");
    	
    	//Get posts for specific userid
    	response = testUserObj.getResponse(userPostsURI+"?userId="+userId);
    	System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Assert.assertTrue(testUserObj.getPostsByUserId(response)>0, "Total number of posts by user didnt match!");
        
    	System.out.println(Constants.TESTS_ENDED + context.getName());
    }
    
    @Test
    public void fetchCommentsAndValidateEmail(ITestContext context) {
    	System.out.println(Constants.TESTS_STARTED + context.getName());     	
    	
    	//Fetch comments and validating email 
    	response = testUserObj.getResponse(userPostsURI);
    	System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");  
    	Assert.assertTrue(testUserObj.validateEmailFromEachPost(response, userPostsURI), "Email validation is failed!");    	
    	
    	System.out.println(Constants.TESTS_ENDED + context.getName());
    }
    
    @Test
    @Parameters({"fileName"})
    public void createPost(String fileName, ITestContext context) {    	  	
    	try {
    		System.out.println(Constants.TESTS_STARTED + context.getName());
			
    		//Creates a new based on json params
    		response = testUserObj.createPostWithJson(userPostsURI, fileName);
			System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
	    	Assert.assertEquals(response.getStatusCode(),201, "Status Code Check Failed!");
	    	Assert.assertTrue(response.jsonPath().get(Constants.ID)!=null, "Post is not created!");   
			
	    	System.out.println(Constants.TESTS_ENDED + context.getName());
		} catch (IOException e) {			
			e.printStackTrace();
		}      	
    }
    
    @Test
    @Parameters({"id"})
    public void deletePost(String id, ITestContext context) {    	  	
    	try {
    		System.out.println(Constants.TESTS_STARTED + context.getName());  
			
    		//deletes a post with given id
    		response = testUserObj.deletePost(userPostsURI + "/" + id);
			System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
	    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
			
	    	System.out.println(Constants.TESTS_ENDED + context.getName());
		} catch (IOException e) {			
			e.printStackTrace();
		}      	
    }
    
    @Test
    public void getallTodos(ITestContext context) {    	  	
    	try {
    		System.out.println(Constants.TESTS_STARTED + context.getName());  
			
    		//Get all todos
    		response = testUserObj.getResponse(todosURI);
			System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
	    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
	    	Assert.assertTrue(response.jsonPath().getList(Constants.ALL).size()==200, "Todos verification failed!");
			
	    	System.out.println(Constants.TESTS_ENDED + context.getName());
		} catch (Exception e) {			
			e.printStackTrace();
		}      	
    }
    
    @Test
    @Parameters({"username"})
    public void getTodosForUser(String username, ITestContext context) { 
    	System.out.println(Constants.TESTS_STARTED + context.getName());
    	
    	//Gets userid for specific user
    	response = testUserObj.getResponse(usersURI);
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Integer userId= testUserObj.getUserId(response, username);
    	Assert.assertNotNull(userId, "No user exists with mentioned name!");
    	
    	//Get todos by specific userid
    	response = testUserObj.getResponse(usersURI+"/"+userId+todosURI);
    	System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Assert.assertTrue(response.jsonPath().getList(Constants.ALL).size()==20, "Todos verification failed!");
        
    	System.out.println(Constants.TESTS_ENDED + context.getName());
    }
    
    @Test
    public void getallPhotos(ITestContext context) {    	  	
    	try {
    		System.out.println(Constants.TESTS_STARTED + context.getName()); 
			
    		//Get all photos
    		response = testUserObj.getResponse(photosURI);
			System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
	    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
	    	Assert.assertTrue(response.jsonPath().getList(Constants.ALL).size()==5000, "Photos verification failed!");
			
	    	System.out.println(Constants.TESTS_ENDED + context.getName());
		} catch (Exception e) {			
			e.printStackTrace();
		}      	
    }    
    
    @Test
    @Parameters({"albumId"})
    public void getPhotosForSpecificAlbum(String albumId, ITestContext context) { 
    	System.out.println(Constants.TESTS_STARTED + context.getName());
    	
    	//Get photos for specific album id
    	response = testUserObj.getResponse(photosURI+"?albumId="+ albumId);
    	System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Assert.assertTrue(response.jsonPath().getList(Constants.ALL).size()==50, "Photos verification failed!");
        
    	System.out.println(Constants.TESTS_ENDED + context.getName());
    }
    
    @Test
    public void getallAlbums(ITestContext context) {    	  	
    	try {
    		System.out.println(Constants.TESTS_STARTED + context.getName());   
			
    		//Get all albums
    		response = testUserObj.getResponse(albumsURI);
			System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
	    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
	    	Assert.assertTrue(response.jsonPath().getList(Constants.ALL).size()==100, "Albums verification failed!");
			
	    	System.out.println(Constants.TESTS_ENDED + context.getName());
		} catch (Exception e) {			
			e.printStackTrace();
		}      	
    }
    
    @Test
    @Parameters({"username"})
    public void getAlbumsForSpecificUser(String username, ITestContext context) { 
    	System.out.println(Constants.TESTS_STARTED + context.getName());
    	
    	//Gets userid for specific user
    	response = testUserObj.getResponse(usersURI);
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Integer userId= testUserObj.getUserId(response, username);
    	Assert.assertNotNull(userId, "No user exists with mentioned name!");
    	
    	//Get todos for specific userid
    	response = testUserObj.getResponse(albumsURI+"?userId="+userId);
    	System.out.println(Constants.RESPONSE_CODE + response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Assert.assertTrue(response.jsonPath().getList(Constants.ALL).size()==10, "Albums verification failed!");
        
    	System.out.println(Constants.TESTS_ENDED + context.getName());
    }
}

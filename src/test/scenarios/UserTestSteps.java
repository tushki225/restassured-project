package test.scenarios;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;

import test.config.Config;
import test.pages.TestUserObject;

public class UserTestSteps extends Config{
	
	public Response response = null; 
    TestUserObject testUserObj = new TestUserObject();
    
    @Test
    @Parameters({"username"})
    public void checkUserExistence(String username, ITestContext context) {
    	System.out.println("-----Test Name: "+ context.getName()+ " started-----");     	
    	
    	response = testUserObj.getResponse(usersURI);
    	System.out.println("Response code is::"+response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");  
    	Assert.assertEquals(testUserObj.validateUserExistence(response, username), true ,"User not found...");    	
    	
    	System.out.println("-----Test Name: "+ context.getName()+ " ended-----");
    }
    
    @Test
    @Parameters({"username"})
    public void searchPostsByUser(String username, ITestContext context) { 
    	System.out.println("-----Test Name: "+ context.getName()+ " started-----");
    	
    	//Gets userid of user
    	response = testUserObj.getResponse(usersURI);
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Integer userId= testUserObj.getUserId(response, username);
    	Assert.assertNotNull(userId, "No user exists with mentioned name!");
    	
    	//Get posts by specific userid
    	response = testUserObj.getResponse(userPostsURI+"?userId="+userId);
    	System.out.println("Response code is::"+response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");
    	Assert.assertTrue(testUserObj.getPostsByUserId(response)>0, "Total number of posts by user didnt match!");
        
    	System.out.println("-----Test Name: "+ context.getName()+ " ended-----");
    }
    
    @Test
    public void fetchCommentsAndValidateEmail(ITestContext context) {
    	System.out.println("-----Test Name: "+ context.getName()+ " started-----");     	
    	
    	response = testUserObj.getResponse(userPostsURI);
    	System.out.println("Response code is::"+response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(),200, "Status Code Check Failed!");  
    	Assert.assertTrue(testUserObj.validateEmailFromEachPost(response, userPostsURI), "Email validation is failed!");    	
    	
    	System.out.println("-----Test Name: "+ context.getName()+ " ended-----");
    }
}

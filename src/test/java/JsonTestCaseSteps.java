package test.java;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import test.java.utils.RestUtils;
import test.java.utils.TestUtils;

public class JsonTestCaseSteps extends Config{
	
	public Response response = null; 
    public JsonPath jsonPath = null;
    public String resourcePath="/users";
    
    //Instantiate a Helper Test Methods (testUtils) Object
    TestUtils testUtils = new TestUtils();
    
    @Test
    public void validateResponse() {        
    	response = RestUtils.getResponsebyPath(resourcePath);
        testUtils.validateStatusCode(response,200);
        testUtils.validateResponseTime(response);
    }

    @Test
    @Parameters({"companyName","count"})
    public void getObjectCount(String companyName, String count) {
    	response = RestUtils.getResponsebyPath(resourcePath);
    	jsonPath = RestUtils.getJsonPath(response);
    	if(response.getStatusCode()==200)
    		testUtils.validateCompanyName(response, companyName, count);
    }
}

package test.java.utils;

import com.jayway.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.testng.Assert;

public class TestUtils {

    public void validateStatusCode(Response res, int code) {
        Assert.assertEquals(res.getStatusCode(),code, "Status Code Check Failed!");
    }
    
    public void validateResponseTime(Response res) {
    	long time= res.getTime();
    	System.out.print("Response time:" + time);
    	Assert.assertTrue((time<2000 && time!=-1),"Status Code Check Failed!");
    }
    
    public void validateCompanyName(Response response, String name, String count) {
    	int counter=0;    	
    	List<Map<String, String>> companies = response.jsonPath().getList("company");    	
    	for (int i = 0; i < companies.size(); i++) {
    		String companyName=companies.get(i).get("name");
    		if(companyName.contains(name))
    			counter++;
        }    	
    	Assert.assertTrue(counter==Integer.parseInt(count),"Company name having Group is not equal to 2!!");
    }
}

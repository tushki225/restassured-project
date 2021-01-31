package test.java;

import com.jayway.restassured.http.ContentType;
import test.java.utils.RestUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Config {	

    @BeforeClass
    public void setup (){        
        RestUtils.setBaseURI(); //Setup Base URI
        RestUtils.setBasePath(""); //Setup Base Path
        RestUtils.setContentType(ContentType.JSON); //Setup Content Type
    }

    @AfterClass
    public void afterTest (){
        //Reset Values
        RestUtils.resetBaseURI();
        RestUtils.resetBasePath();
    }

}

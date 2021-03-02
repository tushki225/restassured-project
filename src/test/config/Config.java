package test.config;

import com.jayway.restassured.http.ContentType;
import test.utils.RestUtils;
import java.io.FileReader;
import java.util.Properties;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Config {
	
	public String propFileName = ".\\src\\test\\config\\build.properties";
	public String baseUri=null;
	public String basePath=null;
	public String usersURI=null;	
	public String userPostsURI=null;
	
	@BeforeClass 
	public void readPropertiesFile (){ 
		try { 
			FileReader reader=new FileReader(propFileName); 
			Properties prop=new Properties();
			prop.load(reader);
			
			this.baseUri= prop.getProperty("baseURI"); 
			this.basePath= prop.getProperty("basePath"); 
			this.usersURI= prop.getProperty("user_uriPath");
			this.userPostsURI= prop.getProperty("userPosts_uriPath");
  
		} catch (Exception e) { 
		  e.printStackTrace(); 
	  	}
	}	 
	
	@BeforeClass
    public void setup (){        
        RestUtils.setBaseURI(baseUri); //Setup Base URI
        RestUtils.setBasePath(basePath); //Setup Base Path
        RestUtils.setContentType(ContentType.JSON); //Setup Content Type
    }

    @AfterClass
    public void afterTest (){
        //Reset Values
        RestUtils.resetBaseURI();
        RestUtils.resetBasePath();
    }

}

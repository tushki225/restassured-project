package test.config;

import com.jayway.restassured.http.ContentType;

import test.utils.Constants;
import test.utils.RestUtils;
import java.io.FileReader;
import java.util.Properties;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Config {
	public String baseUri=null;
	public String basePath=null;
	public String usersURI=null;	
	public String userPostsURI=null;
	public String todosURI=null;
	public String photosURI=null;
	public String albumsURI=null;
	
	@BeforeClass 
	public void readPropertiesFile (){ 
		try { 
			FileReader reader=new FileReader(Constants.PROPERTIES_FILE_PATH); 
			Properties prop=new Properties();
			prop.load(reader);
			
			this.baseUri= prop.getProperty(Constants.BASE_URI_IDENTIFIER); 
			this.basePath= prop.getProperty(Constants.BASE_PATH_IDENTIFIER); 
			this.usersURI= prop.getProperty(Constants.USER_URIPATH_IDENTIFIER);
			this.userPostsURI= prop.getProperty(Constants.USERPOST_URIPATH_IDENTIFIER);
			this.todosURI= prop.getProperty(Constants.TODOS_URIPATH_IDENTIFIER);
			this.photosURI= prop.getProperty(Constants.PHOTOS_URIPATH_IDENTIFIER);
			this.albumsURI= prop.getProperty(Constants.ALBUMS_URIPATH_IDENTIFIER);
  
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

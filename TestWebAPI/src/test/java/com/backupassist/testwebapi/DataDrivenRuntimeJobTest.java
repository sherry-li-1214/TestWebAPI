package com.backupassist.testwebapi;

import java.io.File;
import java.util.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import org.testng.Assert;

import com.backupassist.util.*;

public class DataDrivenRuntimeJobTest extends TestShell {
    @BeforeClass
    public void initBeforeClass() {
    	this.headerForClass(Thread.currentThread().getStackTrace()[1].getClassName());
    }
	
	@DataProvider(name = "ValidJobFile")
	public Iterator<Object[]> jobProvider(){
		File folder = new File(testprop.getProperty("test.data.func.path"));
		JsonFileUtil util = new JsonFileUtil();
		
		return util.jsonFileProvider(folder);
	}
			
	@Test(dataProvider = "ValidJobFile") 
	public void createAndRunJob(File jsonFile ) {
		super.headerForCase();
    	  logger.info("Start to read JSON file:" + jsonFile.getAbsolutePath());
    	
        //Assert.assertTrue(false); // intentionally fail the test, e.g. to avoid runing the jobs
      
        Response response = 
          RestAssured.given()
            .body(jsonFile)
            .contentType("application/json")
          .when()
            .post(GlobalVariables.CONST_BACKUPJOB_API)
          .then()
            .log().ifValidationFails()
            .statusCode(201)
          .extract()
            .response();
      
        String returnHeader = response.getHeader("location");
        
        //String returnHeader = RestAssured.given().body(jsonFile).contentType("application/json").when().post(GlobalVariables.CONST_BACKUPJOB_API).then().statusCode(200).getHeader("location");
        
        String id = returnHeader.replace(GlobalVariables.CONST_BACKUPJOB_API, "");
        logger.info("Created a Job with id:" + id);
	}
	
	//@AfterClass
	public void cleanupAfterClass() {
		// BackupAssistUtil.deleteAllJob(); // ToDo: (?) delete only the jobs created by this class
	}
}

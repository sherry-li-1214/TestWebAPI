package com.backupassist.testwebapi;

import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.backupassist.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class BackupJobTest extends TestShell 
{
	private final String apiDataPath = testprop.getProperty("test.data.api.path");
	
    @BeforeClass public void initBeforeClass() {
    	this.headerForClass(Thread.currentThread().getStackTrace()[1].getClassName());
    }
	
	@Test
	public void getAmazonS3(){
		super.headerForCase();
    	RestAssured.get( GlobalVariables.CONST_AMAZONS3_API ).then().body("Region", equalTo("us-east-1"));
    }
    
    @Test
    public void createAndDeleteAJob(){
		super.headerForCase();
    	//Create Backup job
    	File testDataFile = new File(apiDataPath + "/ValidJob.json");
        String returnHeader = RestAssured.given().body(testDataFile).contentType("application/json").when().post(GlobalVariables.CONST_BACKUPJOB_API).getHeader("location");
        String id = returnHeader.replace(GlobalVariables.CONST_BACKUPJOB_API, "");

        //Delete
        RestAssured.delete(GlobalVariables.CONST_BACKUPJOB_API + "{id}", id).toString();
    }
    
    @Test
    public void getAllJobs() {
		super.headerForCase();
		String result = RestAssured.get(GlobalVariables.CONST_BACKUPJOB_API).asString();
		Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
		
    	Gson gson = new Gson();
    	List<Job> jobList = gson.fromJson(result, listType);
    	
    	for (Job job : jobList) {
    		logger.info("Get job with id:" + job.Id + ", and name:" + job.Name);
    	}
    }
    
    @Test
    public void getAValidJobAndUpdateIt()
    {
		super.headerForCase();
		String result = RestAssured.get(GlobalVariables.CONST_BACKUPJOB_API).asString();
		Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
		
    	Gson gson = new Gson();
    	List<Job> jobList = gson.fromJson(result, listType);
    	
        Job updateJob =	RestAssured.get( GlobalVariables.CONST_BACKUPJOB_API + "{id}", jobList.get(0).Id).as(Job.class);
        
        logger.info("Updating description for job " + updateJob.Id.toString());
        
        
        updateJob.Description = updateJob.Description + "Update by TestNG";
        
        RestAssured.given()
          .contentType("application/json")
          .body(gson.toJson(updateJob))
          .put(GlobalVariables.CONST_BACKUPJOB_API)
        .then()
          .statusCode(200);
        
        //RestAssured.body(gson.toJson(updateJob)).put( GlobalVariables.CONST_BACKUPJOB_API).then().statusCode(200);
        
        RestAssured.get( GlobalVariables.CONST_BACKUPJOB_API + "{id}", updateJob.Id).then().body("Description", equalTo(updateJob.Description));
    }
    
    @Test public void cloneAJob(){
		super.headerForCase();
		String result = RestAssured.get(GlobalVariables.CONST_BACKUPJOB_API).asString();
		Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
		
    	Gson gson = new Gson();
    	List<Job> jobList = gson.fromJson(result, listType);
    	
        //Clone the job
        Response response = RestAssured
          .given()
            .contentType("application/json")
          .when()
            .post( GlobalVariables.CONST_BACKUPJOB_CLONE_API + "{id}", jobList.get(0).Id)
          .then()
            .log().ifValidationFails()
            .statusCode(201)
          .extract()
            .response();
            
        String returnHeader = response.getHeader("location");
        
        //String returnHeader = RestAssured.post( GlobalVariables.CONST_BACKUPJOB_CLONE_API + "{id}", jobList.get(0).Id).getHeaders().toString();
        String idForCloneJob = returnHeader.replace(GlobalVariables.CONST_BACKUPJOB_API, "");
        logger.info("A cloned job is created, the id is:" + idForCloneJob);
    }
}

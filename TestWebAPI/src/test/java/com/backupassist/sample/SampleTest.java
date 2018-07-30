package com.backupassist.sample;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.*;

import com.backupassist.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;

public class SampleTest extends TestShell {
    @BeforeClass public void initBeforeClass() {
    	this.headerForClass(Thread.currentThread().getStackTrace()[1].getClassName());
    }
	
	@Test
	public void testJason() {
		super.headerForCase();
		try {
	    	Reader testDataFile = new FileReader(testprop.getProperty("test.data.path") + "/Sample/ValidJob.json");
	    	//InputSteam
	    	Gson gson = new Gson();
	    	Job job = gson.fromJson(testDataFile, Job.class);
	    	logger.info(job.Id);
	    	logger.info(job.Description);
	    	logger.info(job.BackupDestination.DestinationType);
	    	logger.info(job.BackupSelections.Selections[0].Inclusions[0]);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testArrayJason() {
		super.headerForCase();
		try {
	    	Reader testDataFile = new FileReader(testprop.getProperty("test.data.path") + "/Sample/ValidJobArray.json");
			Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
			
	    	Gson gson = new Gson();
	    	List<Job> jobList = gson.fromJson(testDataFile, listType);
	    	
	    	for (Job job : jobList) {
	    		logger.info(job.Id);
	    		logger.info(job.Name);
	    		logger.info(job.Description);
		    	logger.info(job.BackupDestination.DestinationType);
		    	logger.info(job.BackupSelections.Selections[0].Inclusions[0]);
	    	}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name = "ValidJobFile")
	public Iterator<Object[]> jobProvider(){
		File folder = new File(testprop.getProperty("test.data.path") + "/Sample");
		JsonFileUtil util = new JsonFileUtil();
		
		return util.jsonFileProvider(folder);
	}
			
	@Test(dataProvider = "ValidJobFile") 
	public void testDataProvider(File jsonFile ) {
		super.headerForCase();
    	logger.info("Start to read JSON file:" + jsonFile.getAbsolutePath());
	}
	
	@Test(enabled = false)
	public void deleteAll() {
		super.headerForCase();
		BackupAssistUtil.deleteAllJob();
	}
	
	//ToDo: to research the json to java class provided by RestAssured
	@Test
	public void testRestAssuredToClass() {
		super.headerForCase();
    	//Create Backup job
		logger.info(RestAssured.get(GlobalVariables.CONST_BACKUPJOB_API + "{id}", 1).asString());
        SampleJob job = RestAssured.get(GlobalVariables.CONST_BACKUPJOB_API + "{id}", 1).getBody().as(SampleJob.class);
        logger.info(job.Description);
	
	}
}

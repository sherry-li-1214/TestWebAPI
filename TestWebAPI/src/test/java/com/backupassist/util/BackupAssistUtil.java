package com.backupassist.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.restassured.RestAssured;

public class BackupAssistUtil extends TestShell{

	public static void deleteAllJob() {
		String result = RestAssured.get(GlobalVariables.CONST_BACKUPJOB_API).asString();
		Type listType = new TypeToken<ArrayList<Job>>(){}.getType();
		
    	Gson gson = new Gson();
    	List<Job> jobList = gson.fromJson(result, listType);
    	
    	for (Job job : jobList) {
    		logger.info("Remove job with id:" + job.Id + ", and name:" + job.Name);
    		RestAssured.delete(GlobalVariables.CONST_BACKUPJOB_API + "{id}", job.Id).then().statusCode(200);
    	}
	}
}

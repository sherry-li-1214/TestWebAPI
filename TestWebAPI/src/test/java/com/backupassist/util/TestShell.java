package com.backupassist.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

public class TestShell{
	protected static TestProperties testprop;
	protected static Logger logger;
	private static int count = 0;
	
	public TestShell(){
		testprop = loadTestProperty();
		logger = LogManager.getLogger("");
	}

    @AfterMethod public void cleanupAfterMethod() {
    	this.footerForCase();
    }
    
    public static TestProperties loadTestProperty() {
     	TestProperties prop = new TestProperties();
      	
     	try {
      		InputStream input = new FileInputStream(System.getProperty("test.propertyFile"));
      		prop.load(input);
        }
      	catch (IOException e){
      		e.printStackTrace();
      	}
      	
  		return prop;
    }
    
    public void headerForClass(String className) {
    	logger.info("******************************************************************");
    	logger.info("*     " + className);
    	logger.info("******************************************************************");
    }
    
    public void headerForCase() {
    	count++;
    	logger.info("----------------Test Case " + count + " : " + Thread.currentThread().getStackTrace()[2].getMethodName() + "----------------");
    }
    
    public void footerForCase() {
    	logger.info("                                                                  ");
    }
}

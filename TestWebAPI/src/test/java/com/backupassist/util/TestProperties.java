package com.backupassist.util;

import java.util.Properties;

public class TestProperties extends Properties{
	private static final long serialVersionUID = 4112578634029874840L;
	
    public TestProperties( )
    {
        super();
    }
    
    @Override
    public String getProperty(String key) {
    	String value = super.getProperty(key);
    	
    	while (value.indexOf("${") > -1) {
    		value = substitute(value);
    	}
    	
    	return value;
    }
    
    //Variables substitute
    private String substitute(String value) {
    	int start = value.indexOf("${");
    	int end = value.indexOf("}");
    	
    	String beforeValue = value.substring(0, start);
    	String variable = value.substring(start+2, end);
    	String leftValue = value.substring(end + 1);
    	
    	if (variable.equals("basedir")) {
    		value = System.getProperty("basedir");
    	}
    	else {
    		value = beforeValue + this.getProperty(variable) + leftValue;
    	}
    	
    	return value;
    }
}

package com.backupassist.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class JsonFileUtil {

	public ArrayList<File> listJsonFileInDirectory(File folder) {
		ArrayList<File> resultList = new ArrayList<File>();
		
		File[] listOfJsonFiles = folder.listFiles();
	    for (int i = 0; i < listOfJsonFiles.length; i++) {
	    	if (listOfJsonFiles[i].isDirectory()) {
	    		resultList.addAll(listJsonFileInDirectory(listOfJsonFiles[i]));
	    	}
	    	else {
	    		if (listOfJsonFiles[i].getName().toLowerCase().endsWith(".json")){
	    			resultList.add(listOfJsonFiles[i]);
	    		}
	    	}
	    }

		return resultList;
	}
	
	public Iterator<Object[]> jsonFileProvider(File folder){
		ArrayList<File> listOfJsonFiles = this.listJsonFileInDirectory(folder);

	    Collection<Object[]> data = new ArrayList<Object[]>();
		
	    for (File file : listOfJsonFiles) {
	    	data.add(new Object[]{file});
	    }
		
		return data.iterator();
	}
}

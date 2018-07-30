package com.backupassist.util;

import com.backupassist.sample.SampleJob.BackupEngineType;
import com.backupassist.sample.SampleJob.BackupSelections;

public class Job {
	public enum BackupEngineType {LazarusEngine};
	
	public String Id;
	public String Name;
	public String Description;
	public boolean Suspended;
	public boolean Licensed;
	public String ParentId;
  public String[] DependentIds;
  public  String ResultStatus;
  public String Status;
	public BackupEngineType BackupEngineType;
	public BackupSelections BackupSelections;
	
	
	
	public BackupDestination BackupDestination;
	public String NextBackupTime;
	public String LastRunStatus;
	public String LastRunStartDateTime;
	public String LastRunDuration;
	
	public class BackupSelections {
		public Selection[] Selections;
	}
	
	
	public BackupSelections getBackupSelections()
	  {
	    return BackupSelections;
	  }
	  
	  public void setBackupSelections(BackupSelections backupSelections)
	  {
	    this.BackupSelections = backupSelections;
	  }
	  
	public class Selection {
		public boolean BareMetal;
		public boolean BackupEntireSystem;
		public boolean ValueWasEverSelectedAutomatically;
		public String SelectionType;
		public String[] Inclusions;
		public String Exclusions;
	}
	
	public class BackupDestination {
		public String DestinationPath;
		public String DestinationType;
		public String ImageUri;
		public String UISelector;
	}
}

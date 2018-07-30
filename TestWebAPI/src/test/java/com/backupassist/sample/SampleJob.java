package com.backupassist.sample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class SampleJob {
	//Constructor
	//public SampleJob() {};
	
	public enum BackupEngineType {LazarusEngine, CloudCityEngine};
	
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
  
  public BackupSelections getBackupSelections()
  {
    return BackupSelections;
  }
  
  public void setBackupSelections(BackupSelections backupSelections)
  {
    this.BackupSelections = backupSelections;
  }
  
	public BackupDestination BackupDestination;
	public String NextBackupTime;
	public String LastRunStatus;
	public String LastRunStartDateTime;
	public String LastRunDuration;
	
	public class BackupSelections {
		//Constructor
		public BackupSelections() {};

		public Selection[] Selections;
    
    public Selection[] getSelections()
    {
      return Selections;
    }
    
    public void setSelections(Selection[] selections)
    {
      this.Selections = selections;
    }
	}
	
	public static class Selection {
		//Constructor
		public Selection() {};

    public boolean BareMetal; 
    public boolean BackupEntireSystem;
    public boolean ValueWasEverSelectedAutomatically;
    
		public String SelectionType;
		public String[] Inclusions;
		public String[] Exclusions;
	}
	
  @JsonIgnoreProperties(ignoreUnknown = true)
	public static class BackupDestination {
		//Constructor
		public BackupDestination() {};
		
		public String DestinationPath;
		public String DestinationType;
		public String ImageUri;
		public String UISelector;
	}
}

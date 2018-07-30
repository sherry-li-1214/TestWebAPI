package com.backupassist.util;

public class GlobalVariables {
    public static final String CONST_HOST = "localhost";
    public static final String CONST_PORT = "9999";
    public static final String CONST_URL = "http://" + CONST_HOST + ":" + CONST_PORT + "/api/v1/";
    public static final String CONST_AMAZONS3_API = CONST_URL + "AmazonS3/";
    public static final String CONST_BACKUPJOB_API = CONST_URL + "BackupJob/";
    public static final String CONST_BACKUPJOB_CLONE_API = CONST_URL + "BackupJob/clone/";
    public static final String CONST_BACKUPJOB_PROTOTYPES_API = CONST_URL + "BackupJob/Prototypes/";
    public static final String CONST_BACKUPJOB_VALIDATE_API = CONST_URL + "BackupJob/Validate/";
    public static final String CONST_BACKUPSCHEME_API = CONST_URL + "BackupScheme/";
    public static final String CONST_BACKUPSTRATEGY_API = CONST_URL + "BackupStrategy/";
    public static final String CONST_TREENODES_API = CONST_URL + "TreeNodes/";
    
}

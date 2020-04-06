<?php

class DB_Functions {

    private $db;

    
    function __construct() {
        include_once './db_connect.php';
        // Connect to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }
    // destructor
    function __destruct() {
        
    }
    /**
     * Insert new user
     * 
     */
    public function insertUser($emailId, $gcmRegId, $deviceId) {
        // Insert user into database
        $result = mysql_query("INSERT INTO gcm_users (email,gcm_registration_id,device_id) VALUES('$emailId','$gcmRegId','$deviceId')");		
        if ($result) {
			return true;
        } else {			 
			return false;			          
        }
    }
	/**
     * Insert new post
     * 
     */
    public function insertAnnouncement($title, $content, $disco_id, $created_by) {
        // Insert user into database
        $result = mysql_query("INSERT INTO post_announcements (title,content,disco_code,created_by) VALUES('$title','$content','$disco_id','$created_by')");		
        if ($result) {
			return true;
        } else {			 
			return false;			          
        }
    }
	/**
     * Select all user
     * 
     */
	 public function getAllUsers() {
        $result = mysql_query("select * FROM gcm_users");
        return $result;
    }
	/**
     * Select all regIds
     * 
     */
	 public function getAllGCMRegID() {
        $result = mysql_query("select gcm_registration_id FROM gcm_users");
        return $result;
    }
	/**
     * Get GCMRegId
     * 
     */
	public function getGCMRegID($user_id){
		 $result = mysql_query("SELECT gcm_registration_id FROM gcm_users WHERE user_id = "."$user_id");
		 return $result;
	}
	
	public function getGCMRegIDByDevice($device_id){
		 $result = mysql_query("SELECT gcm_registration_id FROM gcm_users WHERE device_id = "."$device_id");
		 if ($result->num_rows > 0) {
		 	return true;
	        } else {			 
			return false;			          
        	}
	}
	public function updateUser($gcm_reg_id, $device_id){
		 $result = mysql_query("UPDATE gcm_users SET gcm_registration_id = '".$gcm_reg_id."' WHERE device_id = "."$device_id");
		 if ($result) {
		 	return true;
	        } else {			 
			return false;			          
        	}
	}
}
?>
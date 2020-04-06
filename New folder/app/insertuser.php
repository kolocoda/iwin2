<?php
include_once './db_functions.php';
//Create Object for DB_Functions class
$db = new DB_Functions(); 
$emailID = $_POST["emailId"];
$regId = $_POST["regId"];
$deviceId = $_POST["deviceId"];

$result = $db->getGCMRegIDByDevice($deviceId);
if($result){
	$res = $db->updateUser($regId, $deviceId);
	echo "Device Id ".$deviceId." RegId ".$regId ;
	if ($res) {
		echo "GCM Reg Id bas been updated successfully with Server";
	} else {			 
		echo "Error occurred while sharing GCM Reg Id with Server web app";			          
	}

}else {
	$res = $db->insertUser($emailID, $regId, $deviceId);
	echo "Device Id ".$deviceId." RegId ".$regId ;
	if ($res) {
		echo "GCM Reg Id bas been shared successfully with Server";
	} else {			 
		echo "Error occurred while sharing GCM Reg Id with Server web app";			          
	}
}

?>
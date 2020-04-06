<?php
include_once './db_functions.php';
//Create Object for DB_Functions clas
$db = new DB_Functions(); 
$title = $_POST["title"];
$content = $_POST["content"];
$disco = $_POST["disco"];
$userid = $_POST["userId"];

$res = $db->insertAnnouncement($title, $content, $disco, $userid);
if ($res) {
	$statuscode = 200;
	echo json_encode($statuscode);
} else {			 
	$statuscode = 201;
	echo json_encode($statuscode);
}
?>
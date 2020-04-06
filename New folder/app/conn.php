<?php
	$host='localhost';
	$uname='iwin_mobile';
	$pwd='iwin_mobile_app12345';
	$db="iwin_iwin05";
		
	$con = mysqli_connect($host,$uname,$pwd,$db) or die("connection failed");
	
	$action = $_POST["action"];
	$id= $_POST["id"];
	$offset = $_POST["offset"];
	$catid = $_POST["catid"];
	
	//Action types
	
	$getNews = "getNews";
	$getNewsAfter = "getNewsAfter";
	$getNewsBefore = "getNewsBefore";
	$getFaq = "getFaq";
	$getFaqAfter = "getFaqAfter";
	$getAnnouncement = "getAnnouncement";
	$getAnnouncementAfter = "getAnnouncementAfter";
	
	//select function based on action type
	
	if ($action == $getNews){
		selectNews($catid, $con);		
	} elseif ($action == $getNewsAfter){
		selectNewsAfter($catid, $id, $con);
	} elseif ($action == $getNewsBefore){
		selectNewsBefore($catid, $id, $con);
	} elseif ($action == $getFaq){
		selectFAQ($catid, $con);
	} elseif ($action == $getFaqAfter){
		selectFAQAfter($catid, $id, $con);
	} elseif ($action == $getAnnouncement){
		selectAnnouncement($con);
	} elseif ($action == $getAnnouncementAfter){
		selectAnnouncementAfter($id, $con);
	} else {
		echo "You should not be here!!!";
	}	
	
		
	function selectNews($id, $con){
		$sql = "SELECT c.id, c.title, c.introtext, c.fulltext, c.created, u.name FROM `wmqde_k2_items` c, `wmqde_users` u WHERE c.catid = ".$id ." AND c.created_by = u.id ORDER BY c.id DESC LIMIT 30";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up user information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectNewsAfter($catid, $id, $con){
		$sql = "SELECT c.id, c.title, c.introtext, c.fulltext, c.created, u.name FROM `wmqde_k2_items` c, `wmqde_users` u WHERE c.catid = 176 AND c.created_by = u.id AND c.id > ".$id ." ORDER BY c.id DESC LIMIT 30";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up user information; ' . mysqli_error($con));
		
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectNewsBefore($catid, $id, $con){
		$sql = "SELECT c.id, c.title, c.introtext, c.fulltext, c.created, u.name FROM `wmqde_k2_items` c, `wmqde_users` u WHERE c.catid = 176 AND c.created_by = u.id AND c.id< ".$id." ORDER BY c.id DESC LIMIT 30";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up user information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectFAQ($catid, $con){
		$sql = "SELECT c.id, c.title, c.introtext, c.fulltext, c.created, u.name FROM `wmqde_k2_items` c, `wmqde_users` u WHERE c.catid = 166 AND c.created_by = u.id AND c.published = 1 AND c.created > '2013-12-31 00:00' ORDER BY c.id DESC LIMIT 20";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up user information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectFAQAfter($catid, $id, $con){
		$sql = "SELECT c.id, c.title, c.introtext, c.fulltext, c.created, u.name FROM `wmqde_k2_items` c, `wmqde_users` u WHERE c.catid = 166 AND c.created_by = u.id AND c.published = 1 AND c.id > ".$id." ORDER BY c.id DESC LIMIT 20";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up user information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectAnnouncement($con){
		$sql = "SELECT p.id, p.title, p.content, p.disco_code, p.created_date, u.name FROM `post_announcements` p, `wmqde_users` u WHERE p.created_by = u.id ORDER BY p.id DESC LIMIT 20";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up user information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectAnnouncementAfter($id, $con){
		$sql = "SELECT p.id, p.title, p.content, p.disco_code, p.created_date, u.name FROM `post_announcements` p, `wmqde_users` u WHERE p.created_by = u.id AND p.id > ".$id ." ORDER BY p.id DESC LIMIT 30";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up user information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
    //close the db connection
    mysqli_close($con);

?>
<?php
/**
 * Joomla! External authentication script
 *
 * @author Abafor Chima
 * Version 1.0
 *
 * Code adapted from /index.php
 *
 * @package    Joomla.Site
 *
 * @copyright  Copyright (C) 2005 - 2014 Open Source Matters, Inc. All rights reserved.
 * @license    GNU General Public License version 2 or later; see LICENSE.txt
 */

if (version_compare(PHP_VERSION, '5.3.1', '<'))
{
    die('Your host needs to use PHP 5.3.1 or higher to run this version of Joomla!');
}

/**
 * Constant that is checked in included files to prevent direct access.
 * define() is used in the installation folder rather than "const" to not error for PHP 5.2 and lower
 */
define('_JEXEC', 1);

if (file_exists(__DIR__ . '/defines.php'))
{
    include_once __DIR__ . '/defines.php';
}

if (!defined('_JDEFINES'))
{
    define('JPATH_BASE', __DIR__);
    require_once JPATH_BASE . '/includes/defines.php';
}

require_once JPATH_BASE . '/includes/framework.php';

// Instantiate the application.
$app = JFactory::getApplication('site');
jimport('joomla.plugin.helper');

// JFactory
require_once (JPATH_BASE .'/libraries/joomla/factory.php');

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
	$getChart= "getChart";
	$getChartAfter = "getChartAfter";
	$getReport = "getReport";	
	$getReportAfter = "getReportAfter";
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
	} elseif ($action == $getReport){
		selectReport($con);
	} elseif ($action == $getReportAfter){
		selectReportAfter($id, $con);
	} elseif ($action == $getChart){
		selectChart($con);
	} elseif ($action == $getChartAfter){
		selectChartAfter($id, $con);
	} else {
		echo "You should not be here!!!";
	}	
	
		
	function selectNews($id, $con){
		//test function
		$catid = 176;
		$published = 1;
		$db = JFactory::getDBO();
		$query = $db->getQuery(true);
		$query->select($db->quoteName(array('a.id','a.title','a.introtext','a.fulltext','a.created','a.ordering')))
			  ->select($db->quoteName('b.name', 'name'))
			  ->from($db->quoteName('#__k2_items', 'a'))
			  ->join('INNER', $db->quoteName('#__users', 'b') . ' ON (' . $db->quoteName('a.created_by') . ' = ' . $db->quoteName('b.id') . ')')
			  ->where($db->quoteName('a.catid')." = ".$db->quote($catid))
			  ->where($db->quoteName('a.published')." like".$db->quote($published ))
			  ->order($db->quoteName('a.id').'DESC');
		$db->setQuery($query,0,20);
		$itemList = $db->loadObjectList();
		if(count($itemList) > 0) {
		foreach ($itemList as $item){
		
		    $obj= array();
		    $obj['id'] = $item->id;
		    $obj['title'] = urldecode(stripslashes($item->title));
		    $obj['introtext'] = urldecode(stripslashes($item->introtext));
		    $obj['fulltext'] = urldecode(stripslashes($item->fulltext));
		    $obj['published'] = $item->created;
		    $obj['name'] = $item->name;
		    
		$emparray[] = $obj;

		} // foreach
		} // if count > 0
		
		
    
	echo (json_encode($emparray));
	}
	
	function selectNewsAfter($catid, $id, $con){
		//test function
		$catid = 176;
		$published = 1;
		$db = JFactory::getDBO();
		$query = $db->getQuery(true);
		$query->select($db->quoteName(array('a.id','a.title','a.introtext','a.fulltext','a.created','a.ordering')))
			  ->select($db->quoteName('b.name', 'name'))
			  ->from($db->quoteName('#__k2_items', 'a'))
			  ->join('INNER', $db->quoteName('#__users', 'b') . ' ON (' . $db->quoteName('a.created_by') . ' = ' . $db->quoteName('b.id') . ')')
			  ->where($db->quoteName('a.catid')." = ".$db->quote($catid))
			  ->where($db->quoteName('a.id')." > ".$db->quote($id))
			  ->where($db->quoteName('a.published')." like".$db->quote($published ))
			  ->order($db->quoteName('a.id').'DESC');
		$db->setQuery($query,0,20);
		$itemList = $db->loadObjectList();
		if(count($itemList) > 0) {
		foreach ($itemList as $item){
		
		    $obj= array();
		    $obj['id'] = $item->id;
		    $obj['title'] = urldecode(stripslashes($item->title));
		    $obj['introtext'] = urldecode(stripslashes($item->introtext));
		    $obj['fulltext'] = urldecode(stripslashes($item->fulltext));
		    $obj['published'] = $item->created;
		    $obj['name'] = $item->name;
		    
		$emparray[] = $obj;

		} // foreach
		} // if count > 0
		
		
    
	echo (json_encode($emparray));
	}
	
	function selectNewsBefore($catid, $id, $con){
		//test function
		$catid = 176;
		$db = JFactory::getDBO();
		$query = $db->getQuery(true);
		$query->select($db->quoteName(array('a.id','a.title','a.introtext','a.fulltext','a.created','a.ordering')))
			  ->select($db->quoteName('b.name', 'name'))
			  ->from($db->quoteName('#__k2_items', 'a'))
			  ->join('INNER', $db->quoteName('#__users', 'b') . ' ON (' . $db->quoteName('a.created_by') . ' = ' . $db->quoteName('b.id') . ')')
			  ->where($db->quoteName('a.catid')." = ".$db->quote($catid))
			  ->where($db->quoteName('a.id')." < ".$db->quote($id))
			  ->where($db->quoteName('a.published')." like".$db->quote($published ))
			  ->order($db->quoteName('a.id').'DESC');
		$db->setQuery($query,0,20);
		$itemList = $db->loadObjectList();
		if(count($itemList) > 0) {
		foreach ($itemList as $item){
		
		    $obj= array();
		    $obj['id'] = $item->id;
		    $obj['title'] = urldecode(stripslashes($item->title));
		    $obj['introtext'] = urldecode(stripslashes($item->introtext));
		    $obj['fulltext'] = urldecode(stripslashes($item->fulltext));
		    $obj['published'] = $item->created;
		    $obj['name'] = $item->name;
		    
		$emparray[] = $obj;

		} // foreach
		} // if count > 0
		
		
    
	echo (json_encode($emparray));
	}
	
	function selectFAQ($catid, $con){
		//test function
		$catid = 166;
		$id = 507;
		$db = JFactory::getDBO();
		$query = $db->getQuery(true);
		$query->select($db->quoteName(array('a.id','a.title','a.introtext','a.fulltext','a.created','a.ordering')))
			  ->select($db->quoteName('b.name', 'name'))
			  ->from($db->quoteName('#__k2_items', 'a'))
			  ->join('INNER', $db->quoteName('#__users', 'b') . ' ON (' . $db->quoteName('a.created_by') . ' = ' . $db->quoteName('b.id') . ')')
			  ->where($db->quoteName('a.catid')." = ".$db->quote($catid))
			  ->where($db->quoteName('a.id')." > ".$db->quote($id))
			  ->order($db->quoteName('a.id').'DESC');
		$db->setQuery($query,0,20);
		$itemList = $db->loadObjectList();
		if(count($itemList) > 0) {
		foreach ($itemList as $item){
		
		    $obj= array();
		    $obj['id'] = $item->id;
		    $obj['title'] = urldecode(stripslashes($item->title));
		    $obj['introtext'] = urldecode(stripslashes($item->introtext));
		    $obj['fulltext'] = urldecode(stripslashes($item->fulltext));
		    $obj['created'] = $item->created;
		    $obj['name'] = $item->name;
		    
		$emparray[] = $obj;

		} // foreach
		} // if count > 0
		
		
    
	echo (json_encode($emparray));
	}
	
	function selectFAQAfter($catid, $id, $con){
		//test function
		$catid = 166;
		$db = JFactory::getDBO();
		$query = $db->getQuery(true);
		$query->select($db->quoteName(array('a.id','a.title','a.introtext','a.fulltext','a.created','a.ordering')))
			  ->select($db->quoteName('b.name', 'name'))
			  ->from($db->quoteName('#__k2_items', 'a'))
			  ->join('INNER', $db->quoteName('#__users', 'b') . ' ON (' . $db->quoteName('a.created_by') . ' = ' . $db->quoteName('b.id') . ')')
			  ->where($db->quoteName('a.catid')." = ".$db->quote($catid))
			  ->where($db->quoteName('a.id')." > ".$db->quote($id))
			  ->order($db->quoteName('a.id').'DESC');
		$db->setQuery($query,0,20);
		$itemList = $db->loadObjectList();
		if(count($itemList) > 0) {
		foreach ($itemList as $item){
		
		    $obj= array();
		    $obj['id'] = $item->id;
		    $obj['title'] = urldecode(stripslashes($item->title));
		    $obj['introtext'] = urldecode(stripslashes($item->introtext));
		    $obj['fulltext'] = urldecode(stripslashes($item->fulltext));
		    $obj['created'] = $item->created;
		    $obj['published'] = $item->name;
		    
		$emparray[] = $obj;

		} // foreach
		} // if count > 0
		
		
    
	echo (json_encode($emparray));
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
	
	function selectReport($con){
		$sql = "SELECT * FROM `generation_report` ORDER BY id DESC LIMIT 20";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up report information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectReportAfter($id, $con){
		$sql = "SELECT * FROM `generation_report` WHERE id > ".$id ." ORDER BY id DESC LIMIT 30";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up user information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectChart($con){
		$sql = "SELECT * FROM `generation_report_chart` ORDER BY id DESC LIMIT 20";
		 
		$result = mysqli_query($con, $sql) or die('Could not look up report information; ' . mysqli_error($con));
		
		$emparray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$emparray[] = $row;
		}
		print(json_encode($emparray));
	}
	
	function selectChartAfter($id, $con){
		$sql = "SELECT * FROM `generation_report_chart` WHERE id > ".$id ." ORDER BY id DESC LIMIT 30";
		 
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
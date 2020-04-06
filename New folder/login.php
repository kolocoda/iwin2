<?php
/**
 * Joomla! External authentication script
 *
 * @author vdespa
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

// Hardcoded for now
$credentials['username'] = $_POST["username"];;
$credentials['password'] = $_POST["password"];;


// Get a database object
$db    = JFactory::getDbo();
$query = $db->getQuery(true)
    ->select('id, password')
    ->from('#__users')
    ->where('username=' . $db->quote($credentials['username']));

$db->setQuery($query);
$result = $db->loadObject();

if ($result)
{
    $match = JUserHelper::verifyPassword($credentials['password'], $result->password, $result->id);

    if ($match === true)
    {
        // Bring this in line with the rest of the system
        $user = JUser::getInstance($result->id);

    //perform the login action
    $error = $app->login($credentials);
    $user = JFactory::getUser();
    $groups = JUserHelper::getUserGroups($user->id);
    
    //return user details.
    $user_array = array();
    $user_array['id'] = $user->id;
    $user_array['name'] = $user->name;
    $user_array['email'] = $user->email;
    $user_array['username'] = $user->username;
    $user_array['groups'] = $groups->id;
    $user_array['status_code'] = 1;
    
    echo json_encode($user_array);
		
    }
    else
    {
        // Invalid password
        // Prmitive error handling
        die('');
         $user_array['status_code'] = 0;
    
    echo json_encode($user_array);
    }
} else {
    // Invalid user
    // Prmitive error handling
    die('');
     $user_array['status_code'] = 0;
    
    echo json_encode($user_array);
}
?>
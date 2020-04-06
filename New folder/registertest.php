<?php 
define('_JEXEC', 1);
define('JPATH_BASE', __DIR__);
define('DS', DIRECTORY_SEPARATOR);

/* Required Files */
require_once(JPATH_BASE . DS . 'includes' . DS . 'defines.php');
require_once(JPATH_BASE . DS . 'includes' . DS . 'framework.php');
$app = JFactory::getApplication('site');
$app->initialise();

require_once(JPATH_BASE . DS . 'components' . DS . 'com_users' . DS . 'models' . DS . 'registration.php');

$model = new UsersModelRegistration();


$language = JFactory::getLanguage();
$language->load('com_users', JPATH_SITE);
$type       = 0;
$username   = 'rony';
$password   = 'rony';
$name       = 'rony';
$mobile     = '07039428639';
$email      = 'chrischuks56@gmail.com';
$alias      = strtr($name, array(' ' => '-'));
$sendEmail  = 1;
$activation = 1;

            
$data       = array('username'   => $username,
            'name'       => $name,
            'email'     => $email,
            'password1'  => $password, // First password field
            'password2'  => $password, // Confirm password field
            'sendEmail'  => $sendEmail,
            'activation' => $activation,
            'block'      => "0", 
            'mobile'     => $mobile,
            'groups'     => array("2", "2"));
$user = JFactory::getUser(0);
        //Write to database
        if(!$user->bind($data)) {
            throw new Exception("Could not bind data. Error: " . $user->getError());
        }
        if (!$user->save()) {
            throw new Exception("Could not save user. Error: " . $user->getError());
        }

        echo $user->id;

echo $data['name'] . " saved!";
?>
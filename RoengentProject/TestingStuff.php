<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
</head>
    <body>
    <p>This page uses frames. The current browser you are using does not support frames.</p>
    <?php
	include ("UserDTO.php");    
    	
	echo "test 1";
    $var = file_exists("C:\git\Area51_E2014\RoengentProject\UserDTO.php");
    echo "yes/no " . $var ."<br>";
    $teststuff = new testSpaceStuff\UserDTO();
    echo $teststuff->getUsername();
    echo "test 1.5";
	$userDTO = new testSpaceStuff\UserDTO("Rúni");
	echo "test 2 <br>";
	echo $userDTO->getUsername();
	echo "<br>test 3 <br>";
	print $userDTO->getUsername();
	?>
    </body>
</html>
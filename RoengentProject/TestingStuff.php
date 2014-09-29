<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
</head>
    <body>
    <p>This page uses frames. The current browser you are using does not support frames.</p>
    <?php
    echo "test 1";
	$userDTO = new testSpaceStuff\UserDTO("Rúni");
	echo "test 2";
	echo $userDTO->getUsername();
	echo "test 3";
	print $userDTO->getUsername();
	?>
    </body>
</html>
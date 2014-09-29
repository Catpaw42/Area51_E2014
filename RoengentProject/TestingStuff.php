<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
</head>
    <body>
    <p>This page uses frames. The current browser you are using does not support frames.</p>
    <?php
    
    
    use testSpace\TestClass;
	use testSpaceStuff\UserDTO;
    
	//clear any prebuild autoloaders
	spl_autoload_register(null, false);
	
	//register extensions with the autoloader
	spl_autoload_extensions(".php");
	
	//create custom class loader
	function classloader($class)
	{
		echo "here we are in the autoloader!!<br>";
		echo "\$class = ", $class, "<br>";
		//make the classname lowercase.
		$filename = strtolower($class);
		//divide the classname at all "\" as these signify the namespace.
		$filename = explode("\\", $filename);
		//take the last part of the result (the classname w/o the namespace).
		//and append the file extention
		$file = end($filename)  . ".php";
		//check that the file for that class exists.
		if(!file_exists($file))
			return false;
		//include the file.
		include $file;
	}
	
	//register the new autoloader with the __autoload() magic function
	spl_autoload_register("classloader");
	
	
	echo "test 1";
    $var = file_exists("C:\git\Area51_E2014\RoengentProject\UserDTO.php");
    echo "yes/no " . $var ."<br>";
    $teststuff = new testSpaceStuff\UserDTO();
    echo $teststuff->getUsername();
    echo "test 1.5";
	$userDTO = new testSpaceStuff\UserDTO("Rùni");
	echo "test 2 <br>";
	echo $userDTO->getUsername();
	echo "<br>test 3 <br>";
	print $userDTO->getUsername();
	?>
    </body>
</html>
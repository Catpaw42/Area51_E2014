
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
</head>


<body>
<h1>
test test text test <br> test some more
</h1>
<h1 id="test">
test test text test <br> test some more
</h1>
<p>
test test text test <br> test some more
</p>

</body>

<?php

//clear any prebuild autoloaders
spl_autoload_register(null, false);

//register extensions with the autoloader
spl_autoload_extensions(".php");

//create custom class loader
function classloader($class)
{
	echo "here we are in the autoloader!!<br>";
	
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

echo "<p id=specialP>Hello World PHP edition !!<br></p>";
echo "the time is " , date('H:i') , "<br>";
$test = "loosely typed variables! This can only end wrong <br>";
echo $test;
$n = 0;
for ($i = 0; $i < 10; $i++)
{
	$n = 1 - $n;
	echo "<p id=try$n>testing loops: $i<br></p>";
}
define("HELLO", "Penis Penis<br>");
echo HELLO;

echo writeStuff();
echo "<p id=specialP>",writeStuff("This is something else"),"<br></p>";


function writeStuff($word = "this is the default value")
{
	//concat stuff on the value
	return $word . "123"; 
}

class MyClass
{
	public $test = "lalalalal";
	private $name = 123;
}

$obj = new MyClass;

echo $obj->test, "<br>";

$var = new testSpace\TestClass();

echo $var->data, "<br>";

$database = new DBAccess\DBAccess("127.0.0.1", "root", "__________", "test_database");
$database->printDatabases();


?>

</html>

<!DOCTYPE HTML>
<?php 
//create variables and set them to NULL of they dont exist in the _POST array
$username = (isset($_POST["username"]) ? $_POST["username"] : NULL);
$password = (isset($_POST["password"]) ? $_POST["password"] : NULL);

//if the "submit" variable is not set in _POST show the form.
if(!isset($_POST["submit"]))
{
	?>
	<html>
		<head>
			<title>Login</title>
			<meta charset="utf-8">
			<link href="css/loginScreenStyle.css" rel="stylesheet" type="text/css" media="screen">
		</head>
		
		<body>
			<section class="loginform" >
			<h1>Login</h1>
				<form id="login" action=<?php echo htmlentities($_SERVER["PHP_SELF"]); ?> method="post" accept-charset="utf-8">
					<ul>
						<li>
						<label for="username">Brugernavn:</label>
						<input type="text" id="username" name="username" placeholder="Brugernavn" required="required">
						</li>
						<li>
						<label for="password">Kode:</label>
						<input type="password" id="password" name="password" placeholder="Kode" required="required">
						</li>
						<li>
						<input type="submit" id="submit" name="submit" value="submit">
						</li>
					</ul>
				</form>
			</section>
		</body>
	</html>
<?php 
}
//if the "sumbmit" variable has been set in _POST, display the results of the form
else 
{
	echo "username: $username <br> password: $password";
}

?>

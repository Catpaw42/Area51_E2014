<!DOCTYPE HTML>
<html>
	<head>
		<title>Login</title>
		<meta charset="utf-8">
		<link href="css/loginScreenStyle.css" rel="stylesheet" type="text/css" media="screen">
	</head>
	
	<body>
		<section class="loginform cf" >
		<h1>Login</h1>
			<form id="login" action="HelloWorld.php" method="get" accept-charset="utf-8">
				<ul>
					<li>
					<label for="username">Brugernavn:</label>
					<input type="text" id="username" placeholder="Brugernavn" required="required">
					</li>
					<li>
					<label for="password">Kode:</label>
					<input type="password" id="password" placeholder="Kode" required="required">
					</li>
					<li>
					<input type="submit" id="loginButton" value="Login">
					</li>
				</ul>
			</form>
		</section>
	</body>
</html>


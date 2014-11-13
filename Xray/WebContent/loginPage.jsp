<!DOCTYPE HTML>
	<html>
		<head>
			<title>Login</title>
			<meta charset="utf-8">
			<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
			<link href="css/loginPage.css.css" rel="stylesheet" type="text/css" media="screen">
		</head>
		
		<body>
			<section class="loginform" >
			<h1>Login</h1>
				<form id="login" action=LoginServlet method="post" accept-charset="utf-8">
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
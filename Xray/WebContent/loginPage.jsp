<!DOCTYPE HTML>
	<%@page import="helperClasses.Const"%>
<html>
		<head>
			<title>Login</title>
			<meta charset="utf-8">
			<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
			<link href="css/loginPage.css" rel="stylesheet" type="text/css" media="screen">
			<% Boolean loginFailed = (Boolean) request.getAttribute(Const.LOGIN_FAILED); %>
		</head>
		
		<body>
			<section class="loginform" >
			<img alt="Logo for norsjællands hospital" src="img/NSHospitallogo.png">
			<h1>Login</h1>
				<form id="login" action=LoginServlet method="post" accept-charset="utf-8">
				<div><% if (loginFailed !=null && loginFailed==true){
							out.print("Brugernavn eller kodeord forkert!!");
						}
							
							%></div>
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
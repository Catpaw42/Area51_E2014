<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
		<meta charset="utf-8">
		<link href="styleSheet.css" rel="stylesheet" type="text/css" media="screen">
<title>Insert title here</title>
</head>
	<body>
		<div id="topBanner">
			<ul class=showInline>
				<li id="logo">
					<h3>Area 51</h3>
				</li>
				
				<li id="user">
					<p>Brian Bjørn</p>
					<p>Visitator</p>
				</li>
			</ul>
		</div>
		<div id="mainmenu">
			<nav>
				<ul>
					<li>
						<a href="MainServlet?page=rekvirer">Rekvirer</a>
					</li>
					<li>
						<a href="MainServlet?page=visiter">Visiter</a>
					</li>
					<li>
						<a href="MainServlet?page=book">Book</a>
					</li>
					<li>
						<a href="MainServlet?page=admin">Administrer Brugere</a>
					</li>
				</ul>
			</nav>
		</div>
	</body>
</html>

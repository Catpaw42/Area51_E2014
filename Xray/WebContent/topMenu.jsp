<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
	<head>
		<!-- TODO: user validation/protection -->
		<base target="_parent">
		<meta charset="utf-8">
		<link href="topStyleSheet.css" rel="stylesheet" type="text/css" media="screen">
		<title>Insert title here</title>
	</head>
	<body>
		<div id="wrapper">
			<div id="logo">
				<h3>Area 51</h3>
			</div>
			<div id="mainmenu">
				<ul>
					<li> <a href="MainServlet?page=rekvirer">Rekvirer</a> </li>
					<li> <a href="MainServlet?page=visiter">Visitér</a> </li>
					<li> <a href="MainServlet?page=book">Book</a> </li>
					<li> <a href="MainServlet?page=admin">Administrer Brugere</a> </li>
				</ul>
			</div>
			<div id="user">
				<p>Brian Bjorn</p>
			</div>
		</div>
	</body>
</html>
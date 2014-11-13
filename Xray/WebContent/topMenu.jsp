<%@page import="helperClasses.Const"%>
<%@page import="database.dto.Rettigheder.Rettighed"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="database.dto.Bruger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<!-- TODO: user validation/protection -->
<base target="_parent">
<meta charset="utf-8">
<link href="css/topStyleSheet.css" rel="stylesheet" type="text/css"
	media="screen">
<title>Insert title here</title>
<% Bruger activeUser = (Bruger)request.getSession().getAttribute(Const.ACTIVE_USER); %>
</head>
<body>
	<div id="wrapper">
		<div id="user">Brian Bjorn</div>
		<div id="mainmenu">
			<ul>
				<% if (activeUser.harRettighed(Rettighed.REQUEST)) 
						out.print("<li> <a href='MainServlet?page=rekvirer'>Rekvirer</a> </li>");
				if (activeUser.harRettighed(Rettighed.ASSESSOR))
					out.print("<li><a href='MainServlet?page=visiter'>Visitér</a></li>");
				if (activeUser.harRettighed(Rettighed.BOOKING))
					out.print("<li><a href='MainServlet?page=book'>Book</a></li>");
				if (activeUser.harRettighed(Rettighed.ADMIN))
					out.print("<li><a href='MainServlet?page=admin'>Administrer Brugere</a></li>");
				%>

				
				<li><a href='MainServlet?page=admin'>Administrer Brugere</a></li>
			</ul>
		</div>
		<div id="logo">
			<h3>Area 51</h3>
		</div>

	</div>
</body>
</html>
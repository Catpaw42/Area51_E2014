<%@page import="helperClasses.Const"%>
<%@page import="database.dto.Rettigheder.Rettighed"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="database.dto.Bruger"%>
<%@page import="helperClasses.Const" %>
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
<%
	Bruger aUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
%>
</head>
<body>
	<div id="wrapper">
		<div id="user">
			<%
				out.print(aUser.getBrugerNavn()+"<BR>");
				out.print(aUser.getFuldtNavn()+"<BR>");
			%>
		</div>
		<div id="currentpage">
			<h2 >
				<% 
					if(request.getSession().getAttribute(Const.PAGEHEADING)!= null) out.print(request.getSession().getAttribute(Const.PAGEHEADING));
				%>
			</h2>
		</div>
		<div id="mainmenu">
			<ul>
				<%
					if (aUser.harRettighed(Rettighed.REQUEST))
						out.print("<li> <a href='MainServlet?page="+Const.REKVISITION_SERVLET+"'>Rekvirer</a></li>");
					if (aUser.harRettighed(Rettighed.ASSESSOR))
						out.print("<li><a href='MainServlet?page="+ Const.VISITATION_SERVLET + "'>Visitér</a></li>");
					if (aUser.harRettighed(Rettighed.BOOKING))
						out.print("<li><a href='MainServlet?page=" + Const.BOOKING_SERVLET + "'>Book</a></li>");
					if (aUser.harRettighed(Rettighed.ADMIN))
						out.print("<li><a href='MainServlet?page=" + Const.ADMIN_SERVLET + "'>Administrer Brugere</a></li>");
				%>
				<li><a href='MainServlet?action=logout'>Log out</a></li>

			</ul>
		</div>

		<div id="logo">
			<h3>Area 51</h3>
		</div>

	</div>
</body>
</html>
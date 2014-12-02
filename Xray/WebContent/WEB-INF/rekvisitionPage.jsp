<!-- @author Magnus, Rúni, Christian -->
<!DOCTYPE HTML>
<%@page import="database.dto.RekvisitionExtended.Status"%>
<%@page import="database.dto.Bruger" %>
<%@page import="database.dto.Rettigheder" %>
<%@page import="helperClasses.Const" %>
<%@ page import="database.dto.RekvisitionExtended"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta charset="UTF-8">

<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/rekvisitionPage.css" rel="stylesheet" type="text/css" media="screen">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="js/rekvisitionPage.js"></script>
<title>Rekvisition</title>
</head>

<%
	Bruger activeUser = null;
	
	try{
		activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
	}catch(Exception e){}
	
	RekvisitionExtended[] rekv =(RekvisitionExtended[]) request.getSession().getAttribute(Const.REKVISITION_LIST);
%>
<body class="rekvisitionPage">
	<div class="topMenuIframe">
		<%@include file="topMenu.jsp"%>
	</div>
	<div id="mainpage">
		<ul class="showInline centerAlign">
			<li>
				<div id="search">
					<%@include file="searchBox.jsp" %>
				</div>
			</li>
		</ul>
		<ul id="mainmenu">
			<li><a id="nyRekvisition" href="javascript:showOverlay()">Skriv
					Ny Rekvisition</a>
			</li>
		</ul>
		<ul class=showInline>
			<li>
				<div id="rekvisitionList">
				Marker en undersøgelse for at se den i vinduet
					<table>
						<tr>
							<th>Cpr</th>
							<th>Navn</th>
							<th>Modalitet</th>
							<th>Afd</th>
							<th>Dato</th>
							<th>Status</th>
						</tr>
						<%				
						if(rekv != null){
 						 						
							for (RekvisitionExtended r : rekv){
 								out.print("<tr> <td id="+r.getRekvisitionId()+">"); // TODO test if id works
 								out.print(r.getPatient() == null ? "ingen patient" : r.getPatient().getPatientCpr() != null ? r.getPatient().getPatientCpr() : "intet cpr nummer fundet");
								out.print("</td> <td id="+r.getRekvisitionId()+">");
								out.print(r.getPatient() == null ? "ingen patient" : r.getPatient().getPatientNavn() != null ? r.getPatient().getPatientNavn() : "intet patient navn fundet");
								out.print("</td> <td id="+r.getRekvisitionId()+">");
								out.print(r.getModalitet() == null ? "ingen modalitet" : r.getModalitet().getModalitetNavn() != null ? r.getModalitet().getModalitetNavn() : "intet modalitet navn fundet" );
								out.print("</td> <td id="+r.getRekvisitionId()+">");
								out.print(r.getPatient() == null ? "ingen patient" : r.getPatient().getStamafdeling() != null ? r.getPatient().getStamafdeling() : "ingen stamafdeling");
								out.print("</td> <td id="+r.getRekvisitionId()+">");
								out.print(new SimpleDateFormat("dd-MMM-yyyy").format(r.getAfsendtDato()));
								out.print("</td> <td id="+r.getRekvisitionId()+">");
								out.print(r.getStatus());
								if(r.getStatus().equals(Status.PENDING) || r.getStatus().equals(Status.APPROVED)){
								out.print("</td> <td  id="+r.getRekvisitionId()+" class=\"button\" href=\"#\" onclick=cancelRekvisition("+ r.getRekvisitionId()+")>");
								out.print("<button class=\"annullerButton\" >Annuller</button>");
								}
								out.print("</td> </tr>");	
							}
						}
 						%>
					</table>
				</div>
			</li>
			<li>
			<br>
			<div id="embededSite"></div>
				<form id="visiterform" name="visiterform" method="post" action="RekvisitionServlet"></form>
			</li>
		</ul>
	</div>
	<div id="overlay"></div>
	<button class="closeButton" onclick="javascript:hideOverlay()">X</button>
	<div id="overlayPanel">
		<%@include file="nyRekvisitionPage.jsp"%>
	</div>
</body>
</html>

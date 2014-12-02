<!--@author Morten, Magnus -->
<!DOCTYPE HTML>
<%@page import="servlets.BookingServlet"%>
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
<link href="css/bookingPage.css" rel="stylesheet" type="text/css" media="screen">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bookingPage.js"></script>
<title>Booking</title>
</head>

<%
	Bruger activeUser = null;
		activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
	
	RekvisitionExtended[] rekv = null;
	if(request.getSession().getAttribute(Const.REKVISITION_LIST) != null){
	rekv =(RekvisitionExtended[]) request.getSession().getAttribute(Const.REKVISITION_LIST);
	}
%>
<body class="bookingPage">
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
		<hr />
		<ul class=showInline>
			<li>
				<div class="rekvisitionList" id="rekvisitionList">
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
						<tr>
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
								if(r.getStatus().equals(Status.APPROVED)){
								out.print("</td> <td id="+r.getRekvisitionId()+ " class=\"button\" href=\"#\" onclick=bookRekvisition("+ r.getRekvisitionId()+")>");
								out.print("<button class=\"markerBookedKnap\">Marker som booket</button>");
								}
								else{
									out.print("</td> <td id="+r.getRekvisitionId() +" class=\"button\">&nbsp;");
									
								}
								if(r.getStatus().equals(Status.BOOKED) || r.getStatus().equals(Status.APPROVED)){
									out.print("</td> <td id="+r.getRekvisitionId()+" class=\"button\" href=\"#\" onclick=revisitRekvisition("+r.getRekvisitionId()+")>");
									out.print("<button class=\"tilVisitationKnap button\">Til visitation</button>");
								}
								out.print("</td> </tr>");
								
							}
						}
 						%>
 						</tr>
					</table>
				</div>
				</li>
			<li>
				<br>
				<div id="embededSite">
				</div>
				<form id="visiterform" name="visiterform" method="post" action="BookingServlet">
				</form>
			</li>
		</ul>
	</div>
</body>
</html>

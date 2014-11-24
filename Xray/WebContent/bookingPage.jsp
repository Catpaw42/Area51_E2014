<!DOCTYPE HTML>
<%@page import="database.dto.Modalitet"%>
<%@page import="servlets.BookingServlet"%>
<%@page import="database.dto.RekvisitionExtended.Status"%>
<%@page import="database.dto.Bruger" %>
<%@page import="database.dto.Rettigheder" %>
<%@page import="helperClasses.Const" %>
<%@ page import="database.dto.RekvisitionExtended"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styleSheet.css" rel="stylesheet" type="text/css media="screen">
<link href="css/bookingPage.css" rel="stylesheet" type="text/css" media="screen">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bookingPage.js"></script>
<title>Booking</title>
</head>

<%
	Modalitet[] modList = null;
	Status[] statusList = null;
	Bruger activeUser = null;
	boolean userRightsBooking = false;
		activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		// is not being used at the moment
		for(Rettigheder r : activeUser.getRettigheder()){
			if(r.getRettighed().equals(Rettigheder.Rettighed.BOOKING)){
				userRightsBooking = true;
			}
		}
	
	try{
		modList = (Modalitet[]) request.getSession().getAttribute(Const.MODALITY_LIST);
	} catch(Exception e){}
	try{
		statusList = (Status[]) request.getSession().getAttribute(Const.STATUS_LIST);
	} catch(Exception e1){}
	
	RekvisitionExtended[] rekv =(RekvisitionExtended[]) request.getSession().getAttribute(Const.REKVISITION_LIST);
%>
<body class="bookingPage">
	<div class="topMenuIframe">
	<%@include file="topMenu.jsp"%>
	</div>
	<div id="mainpage">
		<ul class="showInline centerAlign">
			<li>
				<div id="search">
					<label for="search">Søg</label>
					<form id="search" name="search" method="post" action="">
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
								<td><input id="cpr" name="cpr" type="text" maxlength="11">
								</td>
								<td><input id="name" name="name" type="text" maxlength="30">
								</td>
								<td><select id="modality" name="modality">
										<%
											if(modList != null)
												for(int i = 0; i < modList.length; i++){
										%><option value=<%out.println(String.valueOf(i));%>>
											<%
												out.println(modList[i].getModalitetNavn());
											%>
										</option>
										<%
											}
										%>

								</select></td>
								<td><select name="department" id="department">
										<%
									if(activeUser != null){
									%>
										<option value="<% out.println(activeUser.getBrugerId()); %>">
											<%out.println(activeUser.getFuldtNavn());%>
										</option>
										<% }
									%>
										<option value="-1">Alle</option>
								</select></td>
								<td><input id="date" name="date" type="date"></td>
								<td><select id="status" name="status">
										<%
											if(statusList != null)
												for(int i = 0; i < statusList.length; i++){
										%><option value=<%out.println(statusList[i].name());%>>
											<%
												out.println(statusList[i].name().toLowerCase());
											%>
										</option>
										<%
											}
										%>
								</select></td>
							</tr>
						</table>
						<input type="submit" id="søg" name="søg" value="Søg">
					</form>
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
						if(rekv != null)
 						 						
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
								out.print("</td> <td id="+r.getRekvisitionId()+" href=\"#\" onclick=bookRekvisition("+ r.getRekvisitionId()+")>");
								out.print("<button class=\"markerBookedKnap\">marker som booket</button>");
								}
								else{
									out.print("</td> <td id="+r.getRekvisitionId() +">&nbsp;");
									
								}
								if(r.getStatus().equals(Status.BOOKED) || r.getStatus().equals(Status.APPROVED)){
									out.print("</td> <td id="+r.getRekvisitionId()+" href=\"#\" onclick=revisitRekvisition("+r.getRekvisitionId()+")>");
									out.print("<button class=\"tilVisitationKnap\">til visitation</button>");
								}
								out.print("</td> </tr>");
								
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
				<form id="visiterform" name="visiterform" method="post" action="WEB-INF/BookingServlet">
				</form>
			</li>
		</ul>
	</div>
	<div id="overlay"></div>
	<div id="overlayPanel">
		<%@include file="nyRekvisitionPage.jsp"%>
	</div>
</body>
</html>

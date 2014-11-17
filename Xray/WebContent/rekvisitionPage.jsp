<!DOCTYPE HTML>
<%-- <%@page import="database.dto.RekvisitionExtended1"%> --%>
<%@page import="database.dto.Modalitet"%>
<%@page import="servlets.RekvisitionServlet"%>
<%@page import="database.dto.RekvisitionExtended.Status"%>
<%@page import="database.dto.Bruger" %>
<%@page import="database.dto.Rettigheder" %>
<%@page import="helperClasses.Const" %>
<%@ page import="database.dto.RekvisitionExtended"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styleSheet.css" rel="stylesheet" type="text/css media="screen">
<link href="css/rekvisitionPage.css" rel="stylesheet" type="text/css" media="screen">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="js/rekvisitionPage.js"></script>
<title>Rekvisition</title>
</head>

<%
	Modalitet[] modList = null;
	Status[] statusList = null;
	Bruger activeUser = null;
	boolean userRightsRequisition = false;
	
	try{
		activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		// is not being used at the moment
		for(Rettigheder r : activeUser.getRettigheder()){
			if(r.getRettighed().equals(Rettigheder.Rettighed.REQUEST)){
				userRightsRequisition = true;
			}
		}
// 		if(activeUser == null){
// 			response.sendRedirect(Const.LOGIN_PAGE);
// 		}
	}catch(Exception e){}
	
	try{
		modList = (Modalitet[]) request.getSession().getAttribute(Const.MODALITY_LIST);
	} catch(Exception e){}
	try{
		statusList = (Status[]) request.getSession().getAttribute(Const.STATUS_LIST);
	} catch(Exception e1){}
	
	RekvisitionExtended[] rekv =(RekvisitionExtended[]) request.getSession().getAttribute(Const.REKVISITION_LIST);
%>
<body class="rekvisitionPage">
	<%@include file="topMenu.jsp"%>
	<div id="mainpage">
		<ul class="showInline centerAlign">
			<li><a id="nyRekvisition" href="javascript:showOverlay()">Skriv
					Ny Rekvisition</a></li>
			<li>
				<div id="search">
					<label for="search">Søg</label>
					<form id="search" name="search" method="post"
						action="RekvisitionServlet">
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
									%>	<option value="<% out.println(activeUser.getBrugerId()); %>"><%out.println(activeUser.getFuldtNavn());%> </option>
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
				<div id="rekvisitionList">
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
								out.print(r.getAfsendtDato());
								out.print("</td> <td id="+r.getRekvisitionId()+">");
								out.print(r.getStatus());
								out.print("</td> </tr>");
							}
 							
 						%>
					</table>
				</div>
			</li>
		
		</ul>
	</div>
	<div id="overlay"></div>
	<div id="overlayPanel">
		<%@include file="nyRekvisitionPage.jsp"%>
	</div>
</body>
</html>

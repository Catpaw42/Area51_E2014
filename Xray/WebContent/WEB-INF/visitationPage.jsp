<!-- @author Magnus, Morten, Rúni, Christian -->
<%@page import="helperClasses.Const"%>
<%@page import="database.dto.Rettigheder.Rettighed"%>
<%@page import="database.dto.Bruger"%>
<%@page import="database.dto.Modalitet"%>
<%@page import="database.dto.RekvisitionExtended.Status"%>
<%@page import="database.dto.Rettigheder" %>
<%@ page import="database.dto.RekvisitionExtended"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Cache-control" content="no-cache">
		<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		<link href="css/visitationPage.css" rel="stylesheet" type="text/css" media="screen">
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/visitationPage.js"></script>
<!-- 		todo fix names -->
		<title>Visitation</title>
	</head>
	<%
	Bruger activeUser = null;
	
	try{
		activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
	}catch(Exception e){}
	
	RekvisitionExtended[] rekv = null;
	if(request.getSession().getAttribute(Const.REKVISITION_LIST) != null){
	rekv =(RekvisitionExtended[]) request.getSession().getAttribute(Const.REKVISITION_LIST);
	}
	%>
	<body class="visitationPage">
		<div class="topMenuIframe">
		 <%@include file="topMenu.jsp" %>
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
					<div class="visitationList">
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
						if(rekv != null && rekv.length != 0){
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
					<form id="visiterform" name="visiterform" method="post" action="VisitationServlet">
						<div id="godkendAfvis">
							<input type="hidden" name="rekIDSubmit" id="rekIDSubmit" value="" />
							<input type="hidden" name="visiterAction" id="visiterAction" value="" />
							<p>
								<img alt="Godkend rekvisition" src="img/godkend.png" onClick="doGodkend()">
								<img alt="Afvis rekvisition" src="img/afvis.png" onClick="doAfvis()">
								
							</p>
							<div>
								<label for="prioritet">Prioritering</label>
								<input id="prioritet" name="prioritet" type="text" maxlength="100" size="75">
								<label for="bemaerkninger">Visitators bemærkninger - incl protokol</label>
								<textarea id="bemaerkninger" name="bemaerkninger" rows="3" cols="75" maxlength="300"></textarea>
							</div>
						</div>	
					</form>	
				</li>
			</ul>
		</div>
	</body>
</html>

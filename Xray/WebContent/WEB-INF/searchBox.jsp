<!-- @author Rúni -->
<%@page import="helperClasses.Const"%>
<%@page import="database.dto.Bruger"%>
<%@page import="database.dto.RekvisitionExtended.Status"%>
<%@page import="database.dto.Modalitet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<base target="_parent">
		<meta charset="utf-8">
		<meta http-equiv="Cache-control" content="no-cache">
	

	</head>
	<%
	Bruger loggedInUser = null;
		Modalitet[] modList = null;
	Status[] statusList = null;
	
	try{
		loggedInUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
	}catch(Exception e){}
	
	try{
		modList = (Modalitet[]) request.getSession().getAttribute(Const.MODALITY_LIST);
	} catch(Exception e){}
	try{
		statusList = (Status[]) request.getSession().getAttribute(Const.STATUS_LIST);
	} catch(Exception e1){}
	
	%>
	<body class="searchBox">
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
									<th>Fra Dato</th>
									<th>Til Dato</th>
									<th>Status</th>
								</tr>
								<tr>
									<td>
										<input id="cpr" name="cpr" type="text" maxlength="11">
									</td>
									<td>
										<input id="name" name="name" type="text" maxlength="30">
									</td>
									<td>
										<select id="modality" name="modality">
										<%
											if(modList != null)
												for(int i = 0; i < modList.length; i++){
										%><option value=<%out.println(modList[i].getModalitetId());%>>
											<%
												out.println(modList[i].getModalitetNavn());
											%>
										</option>
										<%
											}
										%>
										</select>
									</td>
									<td>
										<select name="department" id="department">
									<%
									if(loggedInUser != null){
									%>	<option value="<% out.println(loggedInUser.getBrugerId()); %>"><%out.println(loggedInUser.getFuldtNavn());%> </option>
									<% }
									%>
										<option selected value="-1">Alle</option>
							
								  		</select>
								  	</td>
								  	<td>
								  		<input type="date" id="fromDate" name="fromDate">	
								  	</td>
								  	<td>
								  		<input type="date" id="toDate" name="toDate">
								  	</td>
									<td>
										<select name="status" id="status">
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
								  		</select>
								  	</td>
								</tr>
				  			</table>
				  			<input type="submit" id="søg" name="søg" value="Søg">
				    	</form>
					</div>
				</li>
			</ul>
	</body>
</html>

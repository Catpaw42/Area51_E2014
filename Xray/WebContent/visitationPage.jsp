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
		<meta charset="utf-8">
		<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		<link href="css/visitationPage.css" rel="stylesheet" type="text/css" media="screen">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/visitationPage.js"></script>
<!-- 		todo fix names -->
		<title>Visitation</title>
	</head>
	
	<body class="visitationPage">
		<div class="topMenuIframe">
		 <%@include file="topMenu.jsp" %>
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
									<td>
										<input id="cpr" name="cpr" type="text" maxlength="11">
									</td>
									<td>
										<input id="name" name="name" type="text" maxlength="30">
									</td>
									<td>
										<select id="modality" name="modality">
											<option value="ul">UL - radiograf</option>
											<option value="ct">CT</option>
											<option value="rg">Pet/CT</option>
											<option value="rtg">Rtg.</option>
										</select>
									</td>
									<td>
										<select name="department" id="department">
											<option value="o">O</option>
											<option value="m" selected="selected">M</option>
											<option>Alle</option>
								  		</select>
								  	</td>
								  	<td>
								  		<input type="date" id="date" name="date">
								  	</td>
									<td>
										<select name="status" id="status">
											<option value="PENDING">Sendt</option>
											<option value="APPROVED">Visiteret</option>
											<option value="CANCELED">Annulleret</option>
											<option value="DECLINED">Afvist</option>
											<option value="BOOKED">Booket</option>
								  		</select>
								  	</td>
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
					<div class="visitationList">
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
								<td id="test1">255255-5555</td>
								<td id="test1">Den elskede leder Magnus</td>
								<td id="test1">UL</td>
								<td id="test1">O</td>
								<td id="test1">251014</td>
								<td id="test1">Sendt</td>
							</tr>
							<tr>
								<td id="test2">050501-2222</td>
								<td id="test2">Mickey Mouse</td>
								<td id="test2">CT</td>
								<td id="test2">O</td>
								<td id="test2">300115</td>
								<td id="test2">Visiteret</td>
							</tr>
							<tr>
								<td id="test3">111100-1144</td>
								<td id="test3">Marwin The Depressed Robot</td>
								<td id="test3">RGT</td>
								<td id="test3">M</td>
								<td id="test3">011211</td>
								<td id="test3">Annulleret</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</div>
				</li>
				<li>
					<div id="embededSite">
					</div>
					<div id="godkendAfvis">
						<img alt="Afvis rekvisition" src="img/afvis.png">
						<img alt="Godkend rekvisition" src="img/godkend.png">
						<input id="grundAfvis" name="grundAfvis" type="text" maxlength="50">
						<label for="grundAfvis">Afvis grund</label>
						<input id="prioritet" name="prioritet" type="text" maxlength="50">
						<label for="prioritet">Prioritering</label>
						
						
					</div>
				</li>
			</ul>
		</div>
	</body>
</html>
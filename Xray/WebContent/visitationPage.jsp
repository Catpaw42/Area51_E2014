<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<link href="styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/mainPage.js"></script>
		<title>Main Page</title>
	</head>
	
	<body class="rekvisitionPage">
		<div class="topMenuIframe">
		 <%@include file="topMenu.jsp" %>
		 </div>
		<div id="mainpage">
			<ul class="showInline centerAlign">
				<li>
					<div id="search">
						<label for="search">S�g</label>
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
											<option value="sendt">Sendt</option>
											<option value="visiteret">Visiteret</option>
											<option value="anulleret">Anulleret</option>
											<option value="afvist">Afvist</option>
											<option value="booket">Booket</option>
								  		</select>
								  	</td>
								</tr>
				  			</table>
				  			<input type="submit" id="s�g" name="s�g" value="S�g">
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
							<tr>
								<td>255255-5555</td>
								<td>Den elskede leder Magnus</td>
								<td>UL</td>
								<td>O</td>
								<td>251014</td>
								<td>Sendt</td>
							</tr>
							<tr>
								<td>050501-2222</td>
								<td>Mickey Mouse</td>
								<td>CT</td>
								<td>O</td>
								<td>300115</td>
								<td>Visiteret</td>
							</tr>
							<tr>
								<td>111100-1144</td>
								<td>Marwin The Depressed Robot</td>
								<td>RGT</td>
								<td>M</td>
								<td>011211</td>
								<td>Anulleret</td>
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
						<iframe id="embededSiteFrame" src="visiter.jsp"></iframe>
					</div>
				</li>
			</ul>
		</div>
	</body>
</html>
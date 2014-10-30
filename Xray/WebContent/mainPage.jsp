<!DOCTYPE HTML>
<html class="mainPage">
	<head>
		<meta charset="utf-8">
		<link href="styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		<title>Main Page</title>
	</head>
	
	<body>
		<div id="topBanner">
			<ul>
				<li>
					<h3>Area 51</h3>
				</li>
				<li>
					<p>Brian Bjørn</p>
					<p>Visitator</p>
				</li>
				
			</ul>
		</div>
		<div id="mainmenu">
			<ul>
				<li>
					<a href="rekvirer.jsp">Rekvirer</a>
				</li>
				<li>
					<a href="visiter.jsp">Visiter</a>
				</li>
				<li>
					<a href="book.jsp">Book</a>
				</li>
			</ul>
		</div>
		
		<div id="mainpage">
			<div id="search">
				<label for="search">Søg</label>
		    	<form id="search" name="search" method="post" action="">
		    		<table>
						<tr>
							<th>Cpr</th>
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
		  			<input type="submit" id="applyFilter" name="applyFilter" value="Apply Filter">
		    	</form>
			</div>
			<hr />
			<ul>
				<li>
					<div id="rekvisitionList">
						<table>
							<tr>
								<th>Cpr</th>
								<th>Modalitet</th>
								<th>Afd</th>
								<th>Dato</th>
								<th>Status</th>
							</tr>
							<tr>
								<td>255255-5555</td>
								<td>UL</td>
								<td>O</td>
								<td>251014</td>
								<td>Sendt</td>
							</tr>
							<tr>
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
							</tr>
							<tr>
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
							</tr>
							<tr>
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
							</tr>
							<tr>
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
							</tr>
							<tr>
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
							</tr>
							<tr>
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
						<iframe id="embededSiteFrame" src="NewFile.jsp"></iframe>
					</div>
				</li>
			</ul>
		</div>
	</body>
</html>
<!DOCTYPE HTML>
	<html>
		<head>
			<title>Visiter</title>
			<meta charset="utf-8">
			<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		</head>
		
		<body class="visiter">
			<ul class="showInline">
				<li>
					<div id="stamdata">
					<% 
					if(request.getParameter("rekvisition_Id").equalsIgnoreCase("test1")){
						out.print("test");
					}
					%>
					</div>
				</li>
				<li>
					<div id="supplerendeData">
					
					</div>
				</li>
			</ul>
		</body>
	</html>
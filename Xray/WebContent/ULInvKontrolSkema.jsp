<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title></title>
		</head>
		<body>
			<body class="UlInvKontrolSkema">
		<h2>KontrolSkema for invasiv ultralyd</h2>
		<ul>
			<li>
				<label for="akdato">Tidspunkt for sidste INR/Trombocytter</label>
					<input type="date" id="aktimestamp" name="aktimestamp">
			</li>
			<li>
					<label for="trombocytter">Trombocytter</label>
					<input type="text" id="trombocytter" name="trombocytter" placeholder="Indtast trompocytter">
			</li>
			<li>
					<label for="inr">INR</label>
					<input type="text" id="inr" name="inr" placeholder="Indtast INR">
			</li>
		</ul>
		</body>
	</html>
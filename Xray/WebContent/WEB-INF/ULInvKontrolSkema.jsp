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
					<input type="number" id="trombocytter" name="trombocytter" placeholder="Indtast trombocytter">
			</li>
			<li>
					<label for="inr">INR (med 1 decimal)</label>
					<input type="text" id="inr" name="inr" placeholder="Indtast INR" pattern="[0-9]+[.,][0-9]?">
			</li>
		</ul>
		</body>
	</html>
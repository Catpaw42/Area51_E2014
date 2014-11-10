<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
		<title></title>
	</head>
	<body>
		<h2>PET/CT Kontrolskema</h2>
		<ul>
			<li>
				<label for="formaal">Undersøgelsen er led i:</label>
				<select id="formaal">
					<option value="primaerdiag">Primær diagnostik</option>
					<option value="kontrolbeh">Behandlingskontrol</option>
					<option value="kontrolremission">Kontrol uden aktuel recidivmistanke</option>
					<option value="kontrolrecidiv">Udredning af recidivmistanke</option>
				</select>
				<label for="formaal_tekst">indsæt selectiv text her</label>
				<textarea id="formaal_tekst"></textarea>
			</li>
			<li>
				<label>Relative kontraindikationer for PET-skanning</label>
				<p>Kan patienten ligge helt stille i 30. minutter?
				Ja<input type = "radio" id="kanPtLiggeStille30Ja" name="kanPtLiggeStille30" value="true">
				Nej<input type = "radio" id="kanPtLiggeStille30Nej" name="kanPtLiggeStille30" value="false"></p>
				<p>Tåler patienten faste i 6 timer?
				Ja<input type = "radio" id="ptTaalerFasteJa" name="ptTaalerFaste" value="true">
				Nej<input type = "radio" id="ptTaalerFasteNej" name="ptTaalerFaste" value="false"></p>
				<p>Diabetes Melitus:
				Ja<input type = "radio" id="diabetesJa" name="diabetes" value="true">
				Nej<input type = "radio" id="diabetesNej" name="diabetes" value="false"></p>
				<label for="DM_Beh">Anfør medicinsk behandling</label>
				<input type="text" id="DM_Beh">
			</li>
		</ul>
	</body>
</html>
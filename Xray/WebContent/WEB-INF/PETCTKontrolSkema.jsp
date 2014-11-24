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
				<select id="formaal" name="formaal">
					<option value="primaerdiag">Primær diagnostik</option>
					<option value="kontrolbeh">Behandlingskontrol</option>
					<option value="kontrolremission">Kontrol uden aktuel recidivmistanke</option>
					<option value="kontrolrecidiv">Udredning af recidivmistanke</option>
				</select>
				<label for="formaal_tekst">indsæt selectiv text her</label>
				<textarea id="formaal_tekst" name="formaal_tekst"></textarea>
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
				<input type="text" id="DM_Beh" name="DM_Beh">
			</li>
			<li>
				<label>Andre Oplysninger:</label>
				<p>Smerter
				Ja<input type = "radio" id="smerterJa" name="smerter" value="true">
				Nej<input type = "radio" id="smerterNej" name="smerter" value="false"></p>
				<p>Respirationsinsufficiens
				Ja<input type = "radio" id="respInsuffJa" name="respInsuff" value="true">
				Nej<input type = "radio" id="respInsuffNej" name="respInsuff" value="false"></p>
				<p>Klaustrofobi
				Ja<input type = "radio" id="klaustrofobiJa" name="klaustrofobi" value="true">
				Nej<input type = "radio" id="klaustrofobiNej" name="klaustrofobi" value="false"></p>
			</li>
			<li>
				<p>Allergi:
				Ja<input type = "radio" id="allergiJa" name="allergi" value="true">
				Nej<input type = "radio" id="allergiNej" name="allergi" value="false"></p>
				<label for="allergi_tekst">Hvilken type allergi</label>
				<input type="text" id="allergi_tekst" name="allergi_tekst">
			</li>
			<li>
				<p>Er patienten adipøs?
				Ja<input type = "radio" id="fedmeJa" name="fedme" value="true">
				Nej<input type = "radio" id="fedmeNej" name="fedme" value="false"></p>
				<label for="petctvaegt">Vægt:</label>
				<input type="number" id="petctvaegt" name="petctvaegt">
			</li>
			<li>
				<p>Biopsi / finnålspunktur:
				Ja<input type = "radio" id="biopsiJa" name="biopsi" value="true">
				Nej<input type = "radio" id="biopsiNej" name="biopsi" value="false"></p>
				<label for="biopsi_tekst">Dato og lokalisation</label>
				<input type="text" id="biopsi_tekst" name="biopsi_tekst">
			</li>
			<li>
				<p>Operation:
				Ja<input type = "radio" id="operationJa" name="operation" value="true">
				Nej<input type = "radio" id="operationNej" name="operation" value="false"></p>
				<label for="operation_tekst">Dato og lokalisation</label>
				<input type="text" id="operation_tekst" name= "operation_tekst">
			</li>
			<li>
				<label>Kemo og stråleterapi:</label>
				<p>Aldrig givet
				Ja<input type = "radio" id="aldrigGivetKemoJa" name="aldrigGivetKemo" value="true">
				Nej<input type = "radio" id="aldrigGivetKemoNej" name="aldrigGivetKemo" value="false"></p>
				<p>Kemoterapi har været afsluttet i 2-3 uger
				Ja<input type = "radio" id="kemoterapiJa" name="kemoterapiafsluttet" value="true">
				Nej<input type = "radio" id="kemoterapiNej" name="kemoterapiafsluttet" value="false"></p>
				<p>Stråleterapi har været afsluttet i 2-3 måneder
				Ja<input type = "radio" id="stråleterapiJa" name="stråleterapiafsluttet" value="true">
				Nej<input type = "radio" id="stråleterapiNej" name="stråleterapiafsluttet" value="false"></p>
				<label for="straaleDato">Dato for sidste behandling:</label>
				<input type="date" id="straaleDato" name="straaleDato">
			</li>
			<li>
				<label>Relative kontraindikationer for CT-skanning</label>
				<p>Tidligere reaktion overfor iv. kontraststoffer
				Ja<input type = "radio" id="kontrast_reaktionJa" name="kontrast_reaktion" value="true">
				Nej<input type = "radio" id="kontrast_reaktionNej" name="kontrast_reaktion" value="false"></p>
				<label for="kontrast_reaktion_tekst">Hvilke(n)?</label>
				<input type="text" id="kontrast_reaktion_tekst" name="kontrast_reaktion_tekst">
			</li>
			<li>
				<p>Nedsat nyrefunktion:
				Ja<input type = "radio" id="nedsatNyreFktJa" name="nedsatNyreFkt" value="true">
				Nej<input type = "radio" id="nedsatNyreFktNej" name="nedsatNyreFkt" value="false"></p>
			</li>
			<li>
				<p>P-kreatinin skal foreligge senest 1 uge før undersøgelse hos alle patienter</p>
				<label for="sidstePKreatinin">Sidste P-kreatinin</label>
				<input type="number" id="sidstePKreatinin" name= "sidstePKreatinin" placeholder="&#x3bcmol/l">
				<label for="sidstePKreatTimestamp">Sidste P-kreatinin</label>
				<input type="date" id="sidstePKreatTimestamp" name="sidstePKreatTimestamp">
			</li>
		</ul>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
		<title></title>
	</head>
	<body class="CTKontrolSkema">
		<h2>CT Kontrolskema</h2>
		<ul>
			<li>
				<p>Har patienten Diabetes?
				Ja<input type = "radio" id="diabetesJa" name="diabetes">
				Nej<input type = "radio" id="diabetesNej" name="diabetes"></p>
			</li>
			<li>
				<p>Har patienten kendt nedsat nyrefunktion?
				Ja<input type = "radio" id="nyrefunktionJa" name="nyrefunktion">
				Nej<input type = "radio" id="nyrefunktionNej" name="nyrefunktion"></p>
			</li>
			<li>
				<p>Er patienten tidligere nyreopereret?
				Ja<input type = "radio" id="nyreopereretJa" name="nyreopereret">
				Nej<input type = "radio" id="nyreopereretNej" name="nyreopereret"></p>
			</li>
			<li>
				<p>Har patienten inkompenseret hjertesygdom?
				Ja<input type = "radio" id="hjertesygdomJa" name="hjertesygdom">
				Nej<input type = "radio" id="hjertesygdomNej" name="hjertesygdom"></p>
			</li>
			<li>
				<p>Har patienten nyt myokardieinfarkt(aktuelle indlæggelse)?
				Ja<input type = "radio" id="myokardieinfarktJa" name="myokardieinfarkt">
				Nej<input type = "radio" id="myokardieinfarktNej" name="myokardieinfarkt"></p>
			</li>
			<li>
				<p>Har patienten kendt proteinuri?
				Ja<input type = "radio" id="proteinuriJa" name="proteinuri">
				Nej<input type = "radio" id="proteinuriNej" name="proteinuri"></p>
			</li>
			<li>
				<p>Har patienten kendt urinsyregigt?
				Ja<input type = "radio" id="urinsyregigtJa" name="urinsyregigt">
				Nej<input type = "radio" id="urinsyregigtNej" name="urinsyregigt"></p>
			</li>
			<li>
				<p>Er patienten over 70 på undersøgelsestidspunktet?
				Ja<input type = "radio" id="over70Ja" name="over70">
				Nej<input type = "radio" id="over70Nej" name="over70"></p>
			</li>
			<li>
				<p>Har patienten kendt hypertension?
				Ja<input type = "radio" id="hypertensionJa" name="hypertension">
				Nej<input type = "radio" id="hypertensionNej" name="hypertension"></p>
			</li>
			<li>
				<p>Er patienten i behandling med NSAID-præparat?
				Ja<input type = "radio" id="NSAIDpræparatJa" name="NSAIDpræparat">
				Nej<input type = "radio" id="NSAIDpræparatNej" name="NSAIDpræparat"></p>
			</li>
			<li>
				<p>Er patienten i behandling med aminoglykosider?
				Ja<input type = "radio" id="aminoglykosidJa" name="aminoglykosid">
				Nej<input type = "radio" id="aminoglykosidNej" name="aminoglykosid"></p>
			</li>
			<li>
				<p>Har patienten kendt allergi?
				Ja<input type = "radio" id="alergiJa" name="alergi">
				Nej<input type = "radio" id="alergiNej" name="alergi"></p>
			</li>
			<li>
				<p>Har patienten haft tidligere kontraststofreaktion?
				Ja<input type = "radio" id="kontraststofreaktionJa" name="kontraststofreaktion">
				Nej<input type = "radio" id="kontraststofreaktionNej" name="kontraststofreaktion"></p>
			</li>
			<li>
				<p>Har patienten kendt astma?
				Ja<input type = "radio" id="astmaJa" name="astma">
				Nej<input type = "radio" id="astmaNej" name="astma"></p>
			</li>
			<li>
				<p>Har patienten kendt hyperthyreoidisme?
				Ja<input type = "radio" id="hyperthyreoidismeJa" name="hyperthyreoidisme">
				Nej<input type = "radio" id="hyperthyreoidismeNej" name="hyperthyreoidisme"></p>
			</li>
			<li>
				<p>Er patienten i behandling med et præparat, som indeholder Metformin?
				Ja<input type = "radio" id="metforminJa" name="metformin">
				Nej<input type = "radio" id="metforminNej" name="metformin"></p>
			</li>
			<li>
				<p>Er patienten i behandling med Interleukin 2?
				Ja<input type = "radio" id="interleukinJa" name="interleukin">
				Nej<input type = "radio" id="interleukinNej" name="interleukin"></p>
			</li>
			<li>
				<p>Er patienten i behandling med Beta-blokkere?
				Ja<input type = "radio" id="betaBlokkereJa" name="betaBlokkere">
				Nej<input type = "radio" id="betaBlokkereNej" name="betaBlokkere"></p>
			</li>
			<li>
				<label for="pKreatinin">P-Kreatinin</label>
				<input type="number" id="pKreatinin" name="pKreatinin" placeholder="Værdi">
				<label for="pKreatininDato">P-Kreatinin Dato</label>
				<input type="date" id="pKreatininDato" name="pKreatininDato">
			</li>
			<li>
				<label for="ptHøjde">Pt. Højde</label>
				<input type="number" id="ptHøjde" name="ptHøjde" placeholder="Højde">
			</li>
			<li>
				<label for="ptVægt">Pt. Vægt</label>
				<input type="number" id="ptVægt" name="ptVægt" placeholder="Vægt">
			</li>
		</ul>
	</body>
</html>
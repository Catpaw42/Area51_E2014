<!DOCTYPE HTML>
<html>
	<head>
		<title> Skriv Rekvisition </title>
		<meta charset ="utf-8">
		<link href="styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/Rekvisition.js"></script>
	</head>
	<body>
		<section class="roentgenform">
			<h1>Rekvisition til billeddiagnostisk undersøgelse</h1>
			<form id="rekvisitionsForm" action=NyRekvisitionServlet>
				<div  class="inline">
					<ul>
						<li>
							<div id="patientData">
								<h2>Patient</h2>
								<label for="cpr">CPR-Nummer</label>
								<input type="text" id="cpr" name="cpr" placeholder="012345-6789" required="required">
								<label for="ptNavn">Patient Navn</label>
								<input type="text" id="ptNavn" name="ptNavn" placeholder="Navn">
								<label for="ptAddresse">Addresse</label>
								<input type="text" id="ptAddresse" name="ptAddresse" placeholder="Addresse">
								<label for="telefonNr">Telefonnummer</label>
								<input type="text" id="telefonNr" name="telefonNr" placeholder="" required="required">
							</div>
						</li>
						<li>
							<div id="rekvirentData">
								<h2>Rekvirent</h2>
								<label for="rekvirent">Rekvirent</label>
								<input type="text" id="rekvirent" name="rekvirent" placeholder="Rekvirent" required="required">
								<label for="hendvisendeLæge">Henvisende læge</label>
								<input type="text" id="hendvisendeLæge" name="hendvisendeLæge" placeholder="Hendvisende læge">
								<label for="rekvirentTlf">Kontakt telefonnr</label>
								<input type="text" id="rekvirentTlf" name="rekvirentTlf" placeholder="Telefonnummer">
								<label for="afdeling">Afdeling</label>
								<input type="text" id="afdeling" name="afdeling" placeholder="Afdeling">
							</div>
						</li>
					</ul>
				</div>
				<hr />
				<div id="undersøgelseData">
					<label for="modalitet">Modalitet</label>
					<select id="modalitet" >
						<option value="røntgen" selected="selected">Røntgen</option>
						<option value="ultralyd">Ultralyd</option>
						<option value="invasivUltralyd">Invasiv Ultralyd</option>
						<option value="mr">MR</option>
						<option value="ct">CT</option>
						<option value="ctMedKontrast">CT med kontrast</option>
						<option value="pet">PET/CT</option>
						<option value="andet">Anden US - Beskriv</option>
					</select>
					<label for="indikation">Indikation</label>
					<textarea name="indikation"></textarea>
					<label for="billedtype">Billedtype</label>
					<select id="billedtype">
						<option value="rgtthorax">RGT thorax</option>
						<option value="2">dæk-tryk</option>
						<option value="3">udstødningsrør</option>
						<option value="4">venstre vinge</option>
						<option value="5">motherboard</option>
						<option value="6">bagben</option>
						<option value="7">Andet</option>
					</select>
					<label for="henvisning">Henvist til</label>
					<div id="henvisning">
						<input type="radio" id="radiologiskAfs" name="henvisningRadioButton">Radiologisk Afsnit
						<input type="radio" id="kliniskFysAfs" name="henvisningRadioButton">Klinisk Fysiologisk Afsnit
					</div>
					<label for="hospitalsønske">Evt. Hospitalsønske</label>
					<div id="hospitalsønske">
						<input type="radio" id="hillerød" name="hospitalsønskeRadioButton">Hillerød
						<input type="radio" id="frederikssund" name="hospitalsønskeRadioButton">Frederikssund
						<input type="radio" id="helsingør" name="hospitalsønskeRadioButton">Helsingør
					</div>
					<label for="prioriteringsønske">Prioriteringsønske</label>
					<div id="prioriteringsønske">
						<input type="radio" id="haste" name="prioriteringsønskeRadioButton">Haste
						<input type="radio" id="rutine" name="prioriteringsønskeRadioButton">Rutine <br>
						<input type="radio" id="fremskyndet" name="prioriteringsønskeRadioButton">Fremskyndet
						<input type="radio" id="pakkeforløb" name="prioriteringsønskeRadioButton">Pakkeforløb
					</div>
					<label for="transportValg">Udføres under indlæggelse</label>
					<div id="transportValg">
						<input type="radio" onclick="javascript:transportCheck();" name="transport" id="ambulant" checked="checked">Ambulant <br>
					<input type="radio" onclick="javascript:transportCheck();" name="transport" id="indlagt">Indlæggelse <br>
					</div>
					<label for="transport">Transport</label>
					<div id="transport">
						<div id="ambulantTransport">
							<input type="radio" id="ingenKørsel" name="ambulantRadioButton">Ingen Kørsel <br>
							<input type="radio" id="siddendeKørsel" name="ambulantRadioButton">Siddende Kørsel <br>
							<input type="radio" id="liggendeKørsel" name="ambulantRadioButton">Liggende Kørsel <br>
							<label for="datoForslag">Forslag til dato</label>
							<input type="text" id="datoForslag" name="datoForslag">
						</div>
						<div id="indlagtTransport">
							<input type="radio" id="gårMedPortør" name="indlagtRadioButton">Går selv med portør <br>
							<input type="radio" id="gårUdenPortør" name="indlagtRadioButton">Går selv uden portør <br>
							<input type="radio" id="kørestolMedPortør" name="indlagtRadioButton">Kørestol med portør <br>
							<input type="radio" id="seng" name="seng">Seng<br>
							<label for="afdelingsKode">Afdelings Kode</label>
							<input type="text" id="afdelingsKode" name="afdelingsKode" placeholder=""><br>
							<label for="dato">Dato</label>
							<input type="text" id="dato" name="dato">
						</div>
					</div>
					<label for="gravididtet">Mulighed for graviditet</label>
					<div id="gravididtet">
						<input type="radio" name="graviditet" id="jaGravid">Ja <br>
						<input type="radio" name="graviditet" id="nejGravid">Nej <br>
					</div>
					<label for="cave">Cave</label>
					<input type="text" id="cave" name="cave" placeholder="">
					<label for="særligeForhold">"Særlige Forhold"</label>
					<div id="særligeForhold">
						<input type="checkbox" id="hørehæmmet" name="hørehæmmet">Hørehæmmet <br>
						<input type="checkbox" id="synshæmmet" name="synshæmmet">Synshæmmet <br>
						<input type="checkbox" id="amputeret" name="amputeret">Amputeret <br>
						<input type="checkbox" id="kanIkkeStå" name="kanIkkeStå">Kan ikke selv stå <br>
						<input type="checkbox" id="dement" name="dement">Dement <br>
						<input type="checkbox" id="afasi" name="afasi">Afasi <br>
						<label for="ilt">Ilt</label>
						<input type="text" id="ilt" name="ilt" placeholder="Liter/min"><br>
						<label for="tolk">Tolk</label>
						<input type="text" id="tolk" name="tolk" placeholder="Sprog"><br>
						<label for="isolation">Isolation</label>
						<input type="text" id="isolation" name="isolation" placeholder="Hvilken?"><br>
						<label for="cytostatika">Cytostatika</label>
						<input type="date" id="cytostatika" name="cytostatika">
					</div>
					<label for="kliniskPrb">Klinisk Problemstilling</label><br>
					<label for="kliniskPrb">Anamnese - objektive fund - evt. medicin og laboratoriesvar relevant for undersøgelsen (Se kliniske vejledninger).<br>
					Begrundelse for prioriteringsønske skal fremgå af den kliniske problemstilling.</label>
					<textarea name="kliniskPrb"></textarea>
				</div>
				<div id="ctKontrolSkema">
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
							<p>Har patienten kendt alergi?
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
							<input type="text" id="pKreatinin" name="pKreatinin" placeholder="Værdi">
							<label for="pKreatininDato">P-Kreatinin Dato</label>
							<input type="date" id="pKreatininDato" name="pKreatininDato">
						</li>
						<li>
							<label for="ptHøjde">Pt. Højde</label>
							<input type="text" id="ptHøjde" name="ptHøjde" placeholder="Højde">
						</li>
						<li>
							<label for="ptVægt">Pt. Vægt</label>
							<input type="text" id="ptVægt" name="ptVægt" placeholder="Vægt">
						</li>
					</ul>
				</div>
				<div id="mrKontrolSkema">
				</div>
				<div id="petKontrolSkema">
				</div>
				<input type="submit" id="submit" name="submit" value="submit">
			</form>
		</section>
	</body>
</html>
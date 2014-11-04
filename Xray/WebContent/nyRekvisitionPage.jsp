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
			<h1>Rekvisition til billeddiagnostisk unders�gelse</h1>
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
								<label for="hendvisendeL�ge">Henvisende l�ge</label>
								<input type="text" id="hendvisendeL�ge" name="hendvisendeL�ge" placeholder="Hendvisende l�ge">
								<label for="rekvirentTlf">Kontakt telefonnr</label>
								<input type="text" id="rekvirentTlf" name="rekvirentTlf" placeholder="Telefonnummer">
								<label for="afdeling">Afdeling</label>
								<input type="text" id="afdeling" name="afdeling" placeholder="Afdeling">
							</div>
						</li>
					</ul>
				</div>
				<hr />
				<div id="unders�gelseData">
					<label for="modalitet">Modalitet</label>
					<select id="modalitet" >
						<option value="r�ntgen" selected="selected">R�ntgen</option>
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
						<option value="2">d�k-tryk</option>
						<option value="3">udst�dningsr�r</option>
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
					<label for="hospitals�nske">Evt. Hospitals�nske</label>
					<div id="hospitals�nske">
						<input type="radio" id="hiller�d" name="hospitals�nskeRadioButton">Hiller�d
						<input type="radio" id="frederikssund" name="hospitals�nskeRadioButton">Frederikssund
						<input type="radio" id="helsing�r" name="hospitals�nskeRadioButton">Helsing�r
					</div>
					<label for="prioriterings�nske">Prioriterings�nske</label>
					<div id="prioriterings�nske">
						<input type="radio" id="haste" name="prioriterings�nskeRadioButton">Haste
						<input type="radio" id="rutine" name="prioriterings�nskeRadioButton">Rutine <br>
						<input type="radio" id="fremskyndet" name="prioriterings�nskeRadioButton">Fremskyndet
						<input type="radio" id="pakkeforl�b" name="prioriterings�nskeRadioButton">Pakkeforl�b
					</div>
					<label for="transportValg">Udf�res under indl�ggelse</label>
					<div id="transportValg">
						<input type="radio" onclick="javascript:transportCheck();" name="transport" id="ambulant" checked="checked">Ambulant <br>
					<input type="radio" onclick="javascript:transportCheck();" name="transport" id="indlagt">Indl�ggelse <br>
					</div>
					<label for="transport">Transport</label>
					<div id="transport">
						<div id="ambulantTransport">
							<input type="radio" id="ingenK�rsel" name="ambulantRadioButton">Ingen K�rsel <br>
							<input type="radio" id="siddendeK�rsel" name="ambulantRadioButton">Siddende K�rsel <br>
							<input type="radio" id="liggendeK�rsel" name="ambulantRadioButton">Liggende K�rsel <br>
							<label for="datoForslag">Forslag til dato</label>
							<input type="text" id="datoForslag" name="datoForslag">
						</div>
						<div id="indlagtTransport">
							<input type="radio" id="g�rMedPort�r" name="indlagtRadioButton">G�r selv med port�r <br>
							<input type="radio" id="g�rUdenPort�r" name="indlagtRadioButton">G�r selv uden port�r <br>
							<input type="radio" id="k�restolMedPort�r" name="indlagtRadioButton">K�restol med port�r <br>
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
					<label for="s�rligeForhold">"S�rlige Forhold"</label>
					<div id="s�rligeForhold">
						<input type="checkbox" id="h�reh�mmet" name="h�reh�mmet">H�reh�mmet <br>
						<input type="checkbox" id="synsh�mmet" name="synsh�mmet">Synsh�mmet <br>
						<input type="checkbox" id="amputeret" name="amputeret">Amputeret <br>
						<input type="checkbox" id="kanIkkeSt�" name="kanIkkeSt�">Kan ikke selv st� <br>
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
					<label for="kliniskPrb">Anamnese - objektive fund - evt. medicin og laboratoriesvar relevant for unders�gelsen (Se kliniske vejledninger).<br>
					Begrundelse for prioriterings�nske skal fremg� af den kliniske problemstilling.</label>
					<textarea name="kliniskPrb"></textarea>
				</div>
				<input type="submit" id="submit" name="submit" value="submit">
			</form>
		</section>
	</body>
</html>
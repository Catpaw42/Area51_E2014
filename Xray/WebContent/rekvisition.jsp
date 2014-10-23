<!DOCTYPE HTML>
<html>
<head>
	<title> Skriv Rekvisition </title>
	<meta charset ="utf-8">
	<link href="css/RekvisitionCSS.css" rel="stylesheet" type="text/css" media="screen">
	<script type="text/javascript" src="js/Rekvisition.js"></script>
</head>
<body>
	<section class="roentgenform">
		<h1>Rekvisition til billeddiagnostisk undersøgelse</h1>
		<form id="rekvisitionsForm" action=RequisitionController>
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
						<label for="hendvisendeLÃ¦ge">Henvisende læge</label>
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
			<div id="undersÃ¸gelseData">
				<label for="modalitet">Modalitet</label>
				<select id="modalitet" >
					<option value="rÃ¸ntgen" selected="selected">RÃ¸ntgen</option>
					<option value="ultralyd">Ultralyd</option>
					<option value="mr">MR</option>
					<option value="ct">CT</option>
					<option value="ctMedKontrast">CT med kontrast</option>
					<option value="pet">PET/CT</option>
					<option value="andet">Anden US - Beskriv</option>
				</select>
				<label for="indikation">Indikation</label>
				<textarea name="indikation">write stuff</textarea>
				<label for="billedtype">Billedtype</label>
				<select id="billedtype">
					<option value="rgtthorax">RGT thorax</option>
					<option value="2">dÃ¦k-tryk</option>
					<option value="3">udstÃ¸dningsrÃ¸r</option>
					<option value="4">venstre vinge</option>
					<option value="5">motherboard</option>
					<option value="6">bagben</option>
					<option value="7">Andet</option>
				</select>
				<label for="henvisning">Henvist til</label>
				<div id="henvisning">
						<input type="checkbox" id="radiologiskAfs" name="radiologiskAfs">Radiologisk Afsnit
						<input type="checkbox" id="kliniskFysAfs" name="kliniskFysAfs">Klinisk Fysiologisk Afsnit
				</div>
				<label for="hospitalsÃ¸nske">Evt. HospitalsÃ¸nske</label>
				<div id="hospitalsÃ¸nske">
						<input type="checkbox" id="hillerÃ¸d" name="hillerÃ¸d">HillerÃ¸d
						<input type="checkbox" id="frederikssund" name="frederikssund">Frederikssund
				</div>
				<label for="prioriteringsÃ¸nske">PrioriteringsÃ¸nske</label>
				<div id="prioriteringsÃ¸nske">
						<input type="checkbox" id="haste" name="haste">Haste
						<input type="checkbox" id="rutine" name="rutine">Rutine <br>
						<input type="checkbox" id="fremskyndet" name="fremskyndet">Fremskyndet
						<input type="checkbox" id="pakkeforlÃ¸b" name="pakkeforlÃ¸b">PakkeforlÃ¸b
				</div>
				<label for="transport">UdfÃ¸res under indlÃ¦ggelse</label>
				<div id="transport">
				<input type="radio" onclick="javascript:transportCheck();" name="transport" id="ambulant" checked="checked">Ambulant <br>
				<input type="radio" onclick="javascript:transportCheck();" name="transport" id="indlagt">IndlÃ¦ggelse <br>
				</div>
				<label for="transport">Transport</label>
				<div id="transport">
					<div id="ambulantTransport">
						<label for="ambulant">Ambulant</label>
						<div id="ambulant">
							<input type="checkbox" id="ingenKÃ¸rsel" name="ingenKÃ¸rsel">Ingen KÃ¸rsel <br>
							<input type="checkbox" id="siddendeKÃ¸rsel" name="siddendeKÃ¸rsel">Siddende KÃ¸rsel <br>
							<input type="checkbox" id="liggendeKÃ¸rsel" name="liggendeKÃ¸rsel">Liggende KÃ¸rsel <br>
							<label for="datoForslag">Forslag til dato</label>
							<input type="text" id="datoForslag" name="datoForslag">
						</div>
					</div>
					<div id="indlagtTransport">
					<label for="indlagt">Indlagt</label>
						<div id="indlagt">
							<input type="checkbox" id="gÃ¥rMedPortÃ¸r" name="gÃ¥rMedPortÃ¸r">GÃ¥r selv med portÃ¸r <br>
							<input type="checkbox" id="gÃ¥rUdenPortÃ¸r" name="gÃ¥rUdenPortÃ¸r">GÃ¥r selv uden portÃ¸r <br>
							<input type="checkbox" id="kÃ¸restolMedPortÃ¸r" name="kÃ¸restolMedPortÃ¸r">KÃ¸restol med portÃ¸r <br>
							<input type="checkbox" id="seng" name="seng">Seng<br>
							<label for="afdelingsKode">Afdelings Kode</label>
							<input type="text" id="afdelingsKode" name="afdelingsKode" placeholder=""><br>
							<label for="dato">Dato</label>
							<input type="text" id="dato" name="dato">
						</div>
					</div>
				</div>
				<label for="gravididtet">Mulighed for graviditet</label>
				<div id="gravididtet">
				<input type="radio" name="graviditet" id="jaGravid">Ja <br>
				<input type="radio" name="graviditet" id="nejGravid">Nej <br>
				</div>
				<label for="cave">Cave</label>
				<input type="text" id="cave" name="cave" placeholder="">
				<label for="sÃ¦rligeForhold">"SÃ¦rlige Forhold"</label>
				<div id="sÃ¦rligeForhold">
					<input type="checkbox" id="hÃ¸rehÃ¦mmet" name="hÃ¸rehÃ¦mmet">HÃ¸rehÃ¦mmet <br>
					<input type="checkbox" id="synshÃ¦mmet" name="synshÃ¦mmet">SynshÃ¦mmet <br>
					<input type="checkbox" id="amputeret" name="amputeret">Amputeret <br>
					<input type="checkbox" id="kanIkkeStÃ¥" name="kanIkkeStÃ¥">Kan ikke selv stÃ¥ <br>
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
				<label for="kliniskPrb">Anamnese - objektive fund - evt. medicin og laboratoriesvar relevant for undersÃ¸gelsen (Se kliniske vejledninger).<br>
				Begrundelse for prioriteringsÃ¸nske skal fremgÃ¥ af den kliniske problemstilling.</label>
				<textarea name="kliniskPrb"></textarea>
			</div>
			<input type="submit" id="submit" name="submit" value="submit">
		</form>
	</section>
</body>
</html>
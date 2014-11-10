<!DOCTYPE HTML>
<html>
	<head>
		<title> Skriv Rekvisition </title>
		<meta charset ="utf-8">
		<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/Rekvisition.js"></script>
	</head>
	<body>
		<section class="roentgenform">
			<img alt="" src="img/NSHospitallogo.png">
			<h1>Rekvisition til billeddiagnostisk undersøgelse</h1>
			<form id="rekvisitionsForm" action=NyRekvisitionServlet method="post">
				<div>
					<ul class="showInline">
						<li>
							<div id="patientData">
								<h2>Patient</h2>
								<label for="patient_cpr">CPR-Nummer</label>
								<input type="text" id="patient_cpr" name="patient_cpr" placeholder="012345-6789" required="required">
								<label for="patient_navn">Patient Navn</label>
								<input type="text" id="patient_navn" name="patient_navn" placeholder="Navn"  required="required">
								<label for="patient_adresse">Adresse</label>
								<input type="text" id="patient_adresse" name="patient_adresse" placeholder="Adresse">
								<label for="patient_tlf">Patienttelefonnummer</label>
								<input type="text" id="patient_tlf" name="patient_tlf" placeholder="">
								<label for="paaroerende">Pårørende</label>
								<input type="text" id="paaroerende" name="paaroerende" placeholder="forældre/værge/andet">
								<label for="samtykke">Patientsamtykke</label>
								<div id="samtykke">
									<input type="radio" id="samtykke_ja" name="samtykke" value="ja">Ja
									<input type="radio" id="samtykke_nej" name="samtykke" value="nej">Nej
									<input type="radio" id="uden_samtykke" name="samtykke" value="uden">Ikke i stand til samtykke
								</div>
								<label for="triage">Triage</label>
								<input type="text" id="triage" name="triage" placeholder="evt. EWS">
								<label for="cave">Cave</label>
								<input type="text" id="cave" name="cave" placeholder="" required="required">
							</div>
						</li>
						<li>
							<div id="rekvirentData">
								<h2>Rekvirent</h2>
								<label for="rekvirent">Rekvirent</label>
								<input type="text" id="rekvirent" name="rekvirent" placeholder="Rekvirent" readonly="readonly">
								<label for="henv_afd">Rekvirerende Afdeling</label>
								<input type="text" id="henv_afd" name="henv_afd" placeholder="Henvisende Afdeling" required="required">
								<label for="henv_laege">Henvisende læge</label>
								<input type="text" id="henv_laege" name="henv_laege" placeholder="Henvisende læge">
								<label for="kontakt_tlf">Kontakt telefonnr</label>
								<input type="text" id="kontakt_tlf" name="kontakt_tlf" placeholder="Telefonnummer" required="required">
							</div>
						</li>
					</ul>
				</div>
				<hr />
				<div id="undersoegelsesData">
					<h2>Undersøgelse</h2>
					<label for="udf_indlagt">Udføres under indlæggelse</label>
					<div id="udf_indlagt">
						<input type="radio" onclick="javascript:udfIndlagt();" name="udf_indlagt" id="ambulant" required="required" value="false">Ambulant <br>
						<input type="radio" onclick="javascript:udfIndlagt();" name="udf_indlagt" id="indlagt" required="required" value="true">Indlæggelse <br>
					</div>
					<label for="henvist_til">Henvist til</label>
					<div id="henvist_til">
						<input type="radio" id="radiologiskAfs" name="henvist_til" checked="checked" required="required" value="radiologisk">Radiologisk Afsnit
						<input type="radio" id="kliniskFysAfs" name="henvist_til" required="required" value="klinfys">Klinisk Fysiologisk Afsnit
					</div>
					<label for="hospitalsønske">Evt. Hospitalsønske</label>
					<div id="hospitalsønske">
						<input type="radio" id="hillerød" name="hospitals_oenske" value="hilleroed">Hillerød
						<input type="radio" id="frederikssund" name="hospitals_oenske" value="frederikssund">Frederikssund
						<input type="radio" id="helsingør" name="hospitals_oenske" value="helsingoer">Helsingør
					</div>
					<label for="prioriterings_oenske">Prioriteringsønske</label>
					<div id="prioriterings_oenske">
						<input type="radio" id="haste" name="prioriterings_oenske" value="haste">Haste
						<input type="radio" id="fremskyndet" name="prioriterings_oenske" value="fremskyndet">Fremskyndet
						<input type="radio" id="rutine" name="prioriterings_oenske" value="rutine">Rutine
						<input type="radio" id="pakkeforloeb" name="prioriterings_oenske" value="pakke">Pakkeforløb
					</div>
					<label for="modalitet_navn">Modalitet</label>
					<select id="modalitet_navn" >
						<option value="RTG" selected="selected">Røntgen</option>
						<option value="UL">Ultralyd</option>
						<option value="invasiv_UL">Invasiv Ultralyd</option>
						<option value="MR">MR</option>
						<option value="CT">CT uden kontrast</option>
						<option value="CT_kontrast">CT med kontrast</option>
						<option value="PETCT">PET/CT</option>
						<option value="andet">Anden US - Beskriv nedenfor</option>
					</select>
					<label for="undersoegelses_id">Billedtype</label>
					<select id="undersoegelses_id">
						<option value="1">Do jsp stuff here</option>
						<option value="2">dæk-tryk</option>
						<option value="3">udstødningsrør</option>
						<option value="4">MR af venstre vinge</option>
						<option value="5">MR af hale</option>
						<option value="6">MR af højre bagben</option>
						<option value="7">Andet</option>
					</select>
					<p><label for="klinisk_problemstilling">Klinisk Problemstilling</label>
					Anamnese - objektive fund - evt. medicin og laboratoriesvar relevant for undersøgelsen (Se kliniske vejledninger).<br>
					Begrundelse for prioriteringsønske skal fremgå af den kliniske problemstilling.</p>
					<textarea name="klinisk_problemstilling"></textarea>
					<label for="transport">Transport</label>
					<div id="transport">
						<div id="ambulant_transport">
							<input type="radio" id="ingenKørsel" name="ambulant_transport" value="ingen">Ingen Kørsel <br>
							<input type="radio" id="siddendeKørsel" name="ambulant_transport" value="siddende">Siddende Kørsel <br>
							<input type="radio" id="liggendeKørsel" name="ambulant_transport" value="liggende">Liggende Kørsel <br>

						</div>
						<div id="indlagt_transport">
							<input type="radio" id="gårUdenPortør" name="indlagt_transport" value="selv">Går selv uden portør <br>
							<input type="radio" id="gårMedPortør" name="indlagt_transport" value="portoer">Går selv med portør <br>
							<input type="radio" id="kørestolMedPortør" name="indlagt_transport" value="koerestol">Kørestol med portør <br>
							<input type="radio" id="seng" name="indlagt_transport" value="seng">Seng<br>
						</div>
						<label for="datoForslag">Ønsket tidspunkt</label>
						<input type="text" id="dato_forslag" name="datoForslag">
					</div>
					<label for="gravididtet">Mulighed for graviditet</label>
					<div id="gravididtet">
						<input type="radio" name="graviditet" id="jaGravid" value="true">Ja
						<input type="radio" name="graviditet" id="nejGravid" value="false">Nej
					</div>
					<label for="særligeForhold">Særlige Forhold</label>
					<div id="særligeForhold">
						<input type="checkbox" id="ingen_saerlige_forhold" name="ingen_saerlige_forhold">Ingen særlige forhold <br>
						<input type="checkbox" id="hoerehaemmet" name="hoerehaemmet">Hørehæmmet <br>
						<input type="checkbox" id="synshaemmet" name="synshaemmet">Synshæmmet <br>
						<input type="checkbox" id="amputeret" name="amputeret">Amputeret <br>
						<input type="checkbox" id="kan_ikke_staa" name="kan_ikke_staa">Kan ikke selv stå <br>
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
				</div>
				<label for=tidl_billed_diagnostik>Tidligere undersøgelse</label>
					<p>Er patienten tidligere undersøgt i billeddiagnostisk regi indenfor hospitalerne i Nordsjælland?<BR/>
					Eller har patienten fået foretaget relevant undersøgelse udenfor hospitalerne i Nordsjælland?<BR/>
					Anfør undersøgelse, tidspunkt og hvor.</p>
					<textarea name="tidl_billed_diagnostik"></textarea>
				<hr />
				<div id="ctKontrolSkema">
					<%@include file="CTKontrolSkema.jsp" %>
				</div>
				<div id="mrKontrolSkema">
					<%@include file="MRKontrolSkema.jsp" %>
				</div>
				<div id="petKontrolSkema">
					<%@include file="PETCTKontrolSkema.jsp" %>
				</div>
				<input type="submit" id="submit" name="submit" value="submit">
			</form>
		</section>
	</body>
</html>
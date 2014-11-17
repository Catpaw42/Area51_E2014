<!DOCTYPE HTML>
<%@page import="database.dto.Bruger"%>
<%@page import="helperClasses.Const"%>
<%@page import="servlets.LoginServlet"%>
<html>
	<head>
		<title> Skriv Rekvisition </title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		<link href="css/nyRekvisitionPage.css" rel="stylesheet" type="text/css" media="screen">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/nyRekvisitionPage.js"></script>
	</head>
	<body>
		<section class="roentgenform">
			<img alt="Hospitalets logo" src="img/NSHospitallogo.png">
			<h1>Rekvisition til billeddiagnostisk undersøgelse</h1>
			<form id="rekvisitionsForm" action=NyRekvisitionServlet method="post">
				<div>
					<ul class="showInline">
						<li>
							<div id="patientData" class="hover">
								<h2>Patient</h2>
								<label for="patient_cpr">CPR-Nummer</label>
								<input class="required" type="text" id="patient_cpr" name="patient_cpr" placeholder="012345-6789">
								<label for="patient_navn">Patient Navn</label>
								<input class="required" type="text" id="patient_navn" name="patient_navn" placeholder="Navn">
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
								<input class="required" type="text" id="cave" name="cave" placeholder="">
							</div>
						</li>
						<li>
							<div id="rekvirentData" class="hover">
								<h2>Rekvirent</h2>
								<label for="rekvirent">Rekvirent</label>
								<input type="text" id="rekvirent" name="rekvirent" placeholder="Rekvirent" readonly="readonly" value=<%=((Bruger) request.getSession().getAttribute(Const.ACTIVE_USER)).getBrugerNavn()%>>
								<label for="henv_afd">Rekvirerende Afdeling</label>
								<input class="required" type="text" id="henv_afd" name="henv_afd" placeholder="Henvisende Afdeling">
								<label for="henv_laege">Henvisende læge</label>
								<input type="text" id="henv_laege" name="henv_laege" placeholder="Henvisende læge">
								<label for="kontakt_tlf">Kontakt telefonnr</label>
								<input type="text" id="kontakt_tlf" name="kontakt_tlf" placeholder="Telefonnummer">
							</div>
						</li>
					</ul>
				</div>
				<hr />
				<div id="undersoegelsesData">
					<h2>Undersøgelse</h2>
					<div class="hover">
						<label for="udf_indlagt">Udføres under indlæggelse</label>
						<div id="udf_indlagt">
							<input class="required" type="radio" onclick="javascript:udfIndlagt();" name="udf_indlagt" id="ambulant" value="false">Ambulant
							<input class="required" type="radio" onclick="javascript:udfIndlagt();" name="udf_indlagt" id="indlagt" value="true">Indlæggelse
						</div>
						<label for="henvist_til">Henvist til</label>
						<div id="henvist_til">
							<input class="required" type="radio" id="radiologiskAfs" name="henvist_til" checked="checked" value="radiologisk">Radiologisk Afsnit
							<input class="required" type="radio" id="kliniskFysAfs" name="henvist_til" value="klinfys">Klinisk Fysiologisk Afsnit
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
					</div>
					<div class="hover">
						<div>
						<label for="modalitet_navn">Modalitet</label>
						<select id="modalitet_navn" onchange="javascript:showSkema();">
							<option value="RTG" selected="selected">Røntgen</option>
							<option value="UL">Ultralyd</option>
							<option value="invasiv_UL">Invasiv Ultralyd</option>
							<option value="MR">MR</option>
							<option value="CT">CT uden kontrast</option>
							<option value="CT_kontrast">CT med kontrast</option>
							<option value="PETCT">PET/CT</option>
							<option value="andet">Anden US - Beskriv nedenfor</option>
						</select>
						</div>
						<div>
						<label for="undersoegelses_type">Undersøgelses type</label>
						<input class="required" type="text" id="undersoegelses_type">
						</div>
					</div>
					<div class="hover">
					<p><label for="klinisk_problemstilling">Klinisk Problemstilling</label>
					Anamnese - objektive fund - evt. medicin og laboratoriesvar relevant for undersøgelsen (Se kliniske vejledninger).<br>
					Begrundelse for prioriteringsønske skal fremgå af den kliniske problemstilling.</p>
					<textarea name="klinisk_problemstilling"></textarea>
					</div>
					<div id="transport" class="hover">
						<label for="transport">Transport</label>
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
						<label for="dato_forslag">Ønsket tidspunkt</label>
						<input type="text" id="dato_forslag" name="dato_forslag">
					</div>
					
					<div id="gravididtet" class="hover">
						<label for="gravididtet">Mulighed for graviditet</label>
						<input type="radio" name="graviditet" id="jaGravid" value="true">Ja
						<input type="radio" name="graviditet" id="nejGravid" value="false">Nej
					</div>
					
					<div id="særligeForhold" class="hover">
						<label for="særligeForhold">Særlige Forhold</label>
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
					<div class="hover">
						<label for=tidl_billed_diagnostik>Tidligere undersøgelse</label>
						<p>Er patienten tidligere undersøgt i billeddiagnostisk regi indenfor hospitalerne i Nordsjælland?<BR/>
						Eller har patienten fået foretaget relevant undersøgelse udenfor hospitalerne i Nordsjælland?<BR/>
						Anfør undersøgelse, tidspunkt og hvor.</p>
						<textarea name="tidl_billed_diagnostik"></textarea>
					</div>
				</div>
				<hr />
				<div id="CTSkema">
					<%@include file="CTKontrolSkema.jsp"%>
				</div>
				<div id="MRSkema">
					<%@include file="MRKontrolSkema.jsp"%>
				</div>
				<div id="PETCTSkema">
					<%@include file="PETCTKontrolSkema.jsp"%>
				</div>
				<div  id="ULInvSkema">
					<%@include file="ULInvKontrolSkema.jsp"%>
				</div>
				<input type="submit" id="submit" name="submit" value="submit">
			</form>
		</section>
	</body>
</html>
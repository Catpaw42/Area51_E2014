<%@page import="database.DataSourceConnector"%>
<%@page import="helperClasses.Const"%>
<%@page import="database.dto.Rettigheder.Rettighed"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="database.dto.Bruger"%>
<%@page import="helperClasses.Const" %>
<%@ page import="database.dto.RekvisitionExtended"%>
<%@ page import="database.dao.mysql.RekvisitionDaoImplExt"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%! //methods
	RekvisitionExtended rek;

	void getRekvisition()
	{
		String returnString = "";
		RekvisitionDaoImplExt rekDao=null;
		try{
			rekDao=new RekvisitionDaoImplExt(DataSourceConnector.getConnection());
		}
		catch(Exception e){
			
		}
		
		
		this.rek = rekDao.findByPrimaryKey(2);
		
		
		
		//return returnString;
	}

%>
	

<%
	getRekvisition();
%>
<!DOCTYPE HTML>
	<html>
		<head>
			<title>Visiter</title>
			<meta charset="utf-8">
			<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		</head>
		
		<body class="visiter">
			<ul class="showInline">
				<li>
					<div id="stamdata">
						<ul class="showInline">
							<li>
								<h2>Patient</h2>
								<label for="patient_cpr">CPR-Nummer</label>
								<input type="text" id="patient_cpr" name="patient_cpr" disabled value=" <% out.print(rek.getPatient().getPatientCpr()); %>">
								<label for="patient_navn">Patient Navn</label>
								<input type="text" id="patient_navn" name="patient_navn" disabled value=<% out.print(rek.getPatient().getPatientNavn()); %>>
								<label for="patient_adresse">Adresse</label>
								<input type="text" id="patient_adresse" name="patient_adresse" disabled value=<% out.print(rek.getPatient().getPatientAdresse()); %>>
								<label for="patient_tlf">Patienttelefonnummer</label>
								<input type="text" id="patient_tlf" name="patient_tlf" disabled value=<% out.print(rek.getPatient().getPatientTlf()); %>>
								<label for="paaroerende">Pårørende</label>
								<input type="text" id="paaroerende" name="paaroerende" disabled value=<% out.print(rek.getPaaroerende()==null?"":rek.getPaaroerende()); %>>
								<label for="samtykke">Patientsamtykke</label>
								<div id="samtykke">
									<input type="radio" id="samtykke_ja" name="samtykke" value="ja"  
									<%
										if(rek.getSamtykke()==RekvisitionExtended.Samtykke.JA){ out.print("checked");}
										else out.print("disabled");
									%>
										>Ja
									<input type="radio" id="samtykke_nej" name="samtykke" value="nej" disabled 
									<%
										if(rek.getSamtykke()==RekvisitionExtended.Samtykke.NEJ) { out.print("checked");}
										else out.print("disabled");
									%>
									>Nej
									<input type="radio" id="uden_samtykke" name="samtykke" value="uden" disabled 
									<%
										if(rek.getSamtykke()==RekvisitionExtended.Samtykke.UDEN_SAMTYKKE) { out.print("checked");}
										else out.print("disabled");
									%>
									>Ikke i stand til samtykke
								</div>
								<label for="triage">Triage</label>
								<input type="text" id="triage" name="triage" disabled value=<%out.print(rek.getTriage()); %>>
								<label for="cave">Cave</label>
								<input type="text" id="cave" name="cave" disabled value=<%out.print(rek.getCave()); %> >
							</li>
							<li>
								<h2>Rekvirent</h2>
								<label for="rekvirent">Rekvirent</label>
								<input type="text" id="rekvirent" name="rekvirent" disabled value=<%out.print(rek.getRekvirent().getFuldtNavn()); %>>
								<label for="henv_afd">Rekvirerende Afdeling</label>
								<input type="text" id="henv_afd" name="henv_afd" disabled value=<%out.print(rek.getHenvAfd()); %>>
								<label for="henv_laege">Henvisende læge</label>
								<input type="text" id="henv_laege" name="henv_laege" disabled value=<%out.print(rek.getHenvLaege()); %>>
								<label for="kontakt_tlf">Kontakt telefonnr</label>
								<input type="text" id="kontakt_tlf" name="kontakt_tlf" disabled value=<%out.print(rek.getKontaktTlf()); %>>
							</li>
						</ul>
						<hr />
						<div id="undersoegelsesData">
					<h2>Undersøgelse</h2>
					<label for="udf_indlagt">Udføres under indlæggelse</label>
					<div id="udf_indlagt">
						<input type="radio" name="udf_indlagt" id="ambulant" disabled value="false">Ambulant
						<input type="radio" name="udf_indlagt" id="indlagt" disabled value="true">Indlæggelse
					</div>
					<label for="henvist_til">Henvist til</label>
					<div id="henvist_til">
						<input type="radio" id="radiologiskAfs" name="henvist_til" checked="checked" disabled value="radiologisk">Radiologisk Afsnit
						<input type="radio" id="kliniskFysAfs" name="henvist_til" disabled value="klinfys">Klinisk Fysiologisk Afsnit
					</div>
					<label for="hospitalsønske">Evt. Hospitalsønske</label>
					<div id="hospitalsønske">
						<input type="radio" id="hillerød" name="hospitals_oenske" value="hilleroed" disabled>Hillerød
						<input type="radio" id="frederikssund" name="hospitals_oenske" value="frederikssund" disabled>Frederikssund
						<input type="radio" id="helsingør" name="hospitals_oenske" value="helsingoer" disabled>Helsingør
					</div>
					<label for="prioriterings_oenske">Prioriteringsønske</label>
					<div id="prioriterings_oenske">
						<input type="radio" id="haste" name="prioriterings_oenske" value="haste" disabled>Haste
						<input type="radio" id="fremskyndet" name="prioriterings_oenske" value="fremskyndet" disabled>Fremskyndet
						<input type="radio" id="rutine" name="prioriterings_oenske" value="rutine" disabled>Rutine
						<input type="radio" id="pakkeforloeb" name="prioriterings_oenske" value="pakke" disabled>Pakkeforløb
					</div>
					<div>
					<label for="modalitet_navn">Modalitet</label>
					<select id="modalitet_navn" tabindex="1" disabled>
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
					<input type="text" id="undersoegelses_type" disabled>
					</div>
					<p><label for="klinisk_problemstilling">Klinisk Problemstilling</label>
					Anamnese - objektive fund - evt. medicin og laboratoriesvar relevant for undersøgelsen (Se kliniske vejledninger).<br>
					Begrundelse for prioriteringsønske skal fremgå af den kliniske problemstilling.</p>
					<textarea name="klinisk_problemstilling" disabled></textarea>
					<div id="transport">
						<label for="transport">Transport</label>
						<div id="ambulant_transport">
							<input type="radio" id="ingenKørsel" name="ambulant_transport" value="ingen" disabled>Ingen Kørsel <br>
							<input type="radio" id="siddendeKørsel" name="ambulant_transport" value="siddende" disabled>Siddende Kørsel <br>
							<input type="radio" id="liggendeKørsel" name="ambulant_transport" value="liggende" disabled>Liggende Kørsel <br>

						</div>
						<div id="indlagt_transport">
							<input type="radio" id="gårUdenPortør" name="indlagt_transport" value="selv" disabled>Går selv uden portør <br>
							<input type="radio" id="gårMedPortør" name="indlagt_transport" value="portoer" disabled>Går selv med portør <br>
							<input type="radio" id="kørestolMedPortør" name="indlagt_transport" value="koerestol"disabled>Kørestol med portør <br>
							<input type="radio" id="seng" name="indlagt_transport" value="seng">Seng<br>
						</div>
						<label for="dato_forslag">Ønsket tidspunkt</label>
						<input type="text" id="dato_forslag" name="dato_forslag" disabled>
					</div>
					
					<div id="gravididtet">
						<label for="gravididtet">Mulighed for graviditet</label>
						<input type="radio" name="graviditet" id="jaGravid" value="true" disabled>Ja
						<input type="radio" name="graviditet" id="nejGravid" value="false" disabled>Nej
					</div>
					
					<div id="særligeForhold">
						<label for="særligeForhold">Særlige Forhold</label>
						<input type="checkbox" id="ingen_saerlige_forhold" name="ingen_saerlige_forhold" disabled>Ingen særlige forhold <br>
						<input type="checkbox" id="hoerehaemmet" name="hoerehaemmet" disabled>Hørehæmmet <br>
						<input type="checkbox" id="synshaemmet" name="synshaemmet" disabled>Synshæmmet <br>
						<input type="checkbox" id="amputeret" name="amputeret" disabled>Amputeret <br>
						<input type="checkbox" id="kan_ikke_staa" name="kan_ikke_staa" disabled>Kan ikke selv stå <br>
						<input type="checkbox" id="dement" name="dement" disabled>Dement <br>
						<input type="checkbox" id="afasi" name="afasi" disabled>Afasi <br>
						<label for="ilt">Ilt</label>
						<input type="text" id="ilt" name="ilt" placeholder="Liter/min" disabled><br>
						<label for="tolk">Tolk</label>
						<input type="text" id="tolk" name="tolk" placeholder="Sprog" disabled><br>
						<label for="isolation">Isolation</label>
						<input type="text" id="isolation" name="isolation" placeholder="Hvilken?" disabled><br>
						<label for="cytostatika">Cytostatika</label>
						<input type="date" id="cytostatika" name="cytostatika" disabled>
					</div>
				</div>
				<div>
					<label for=tidl_billed_diagnostik>Tidligere undersøgelse</label>
					<p>Er patienten tidligere undersøgt i billeddiagnostisk regi indenfor hospitalerne i Nordsjælland?<BR/>
					Eller har patienten fået foretaget relevant undersøgelse udenfor hospitalerne i Nordsjælland?<BR/>
					Anfør undersøgelse, tidspunkt og hvor.</p>
					<textarea name="tidl_billed_diagnostik" disabled></textarea>
				</div>		
								
					<% 
					if(request.getParameter("rekvisition_Id").equalsIgnoreCase("1")){
						out.print("test test test");
					}
					%>
					</div>
				</li>
				<li>
					<div id="supplerendeData">
					
					</div>
				</li>
				<li>
					
				</li>
			</ul>
		</body>
	</html>
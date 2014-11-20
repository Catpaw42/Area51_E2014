<!DOCTYPE HTML>
<%@page import="database.dto.RekvisitionExtended.IndlaeggelseTransport"%>
<%@page import="database.dto.RekvisitionExtended.AmbulantKoersel"%>
<%@page import="database.dto.RekvisitionExtended.Prioritering"%>
<%@page import="database.dto.RekvisitionExtended.HospitalOenske"%>
<%@page import="database.dto.RekvisitionExtended.HenvistTil"%>
<%@page import="database.dto.RekvisitionExtended.Samtykke"%>
<%@page import="database.dto.RekvisitionExtended.Status"%>
<%@page import="database.dto.UndersoegelsesType"%>
<%@page import="database.DataSourceConnector"%>
<%@page import="helperClasses.Const"%>
<%@page import="database.dto.Rettigheder.Rettighed"%>
<%@page import="database.dto.Modalitet"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="database.dto.Bruger"%>
<%@page import="helperClasses.Const" %>
<%@ page import="database.dto.RekvisitionExtended"%>
<%@ page import="database.dao.mysql.RekvisitionDaoImplExt"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<html>
		<head>
			<title>Visiter</title>
			<meta charset="utf-8">
			<link href="css/styleSheet.css" rel="stylesheet" type="text/css" media="screen">
		</head>
		<% //methods
	RekvisitionExtended rek = null;
		RekvisitionDaoImplExt rekDao = null;
		try{
			rekDao=new RekvisitionDaoImplExt(DataSourceConnector.getConnection());
		}
		catch(Exception e){
		}
// 		rek = (RekvisitionExtended) request.getAttribute(Const.REKVISITION_SELECTED);
// 		if(request.getParameter(Const.REKVISITION_ID) != null){
// 		rek = rekDao.findByPrimaryKey(Integer.valueOf(request.getParameter(Const.REKVISITION_ID)));
// 		}
//TODO should not be retrieved from dao but as an attribute on request object
		rek = rekDao.findByPrimaryKey(Integer.valueOf(request.getParameter(Const.REKVISITION_ID)));

%>
		<body class="visiter">
			<ul class="showInline">
				<li>
					<div id="stamdata">
							<%if(rek.getVisitator() != null) {
								out.print("<b> Visitator</b>");
								out.println("<p>" + rek.getVisitator().getFuldtNavn() + "</p>");
							
							}%>
							<%if(rek.getVisitatorBemaerkning()!=null){
								out.print("<b>Visitatorbemærkninger</b>");
								out.println("<p>" + rek.getVisitatorBemaerkning()+ "</p>");
								out.println("<b>Visitatorprioritering</b>");
								out.println("<p>" +rek.getVisitatorPrioritering() + "</p>");
 							}%> 
						<ul class="showInline">
							<li>
								<h2>Patient</h2>
								<label for="patient_cpr">CPR-Nummer</label>
								<input type="text" id="patient_cpr" name="patient_cpr" disabled value="<% out.print(rek.getPatient().getPatientCpr()); %>">
								<label for="patient_navn">Patient Navn</label>
								<input type="text" id="patient_navn" name="patient_navn" disabled value="<% out.print(rek.getPatient().getPatientNavn()); %>">
								<label for="patient_adresse">Adresse</label>
								<input type="text" id="patient_adresse" name="patient_adresse" disabled value="<% out.print(rek.getPatient().getPatientAdresse()); %>">
								<label for="patient_tlf">Patienttelefonnummer</label>
								<input type="text" id="patient_tlf" name="patient_tlf" disabled value="<% out.print(rek.getPatient().getPatientTlf()); %>">
								<label for="paaroerende">Pårørende</label>
								<input type="text" id="paaroerende" name="paaroerende" disabled value="<% out.print(rek.getPaaroerende()==null?"":rek.getPaaroerende()); %>">
								<label for="samtykke">Patientsamtykke</label>
								<div id="samtykke">
									<input type="radio" id="samtykke_ja" name="samtykke" value="ja"  
									<%
										if(rek.getSamtykke()==RekvisitionExtended.Samtykke.JA){ out.print("checked");}
										else out.print("disabled");
									%>
										>Ja
									<input type="radio" id="samtykke_nej" name="samtykke" value="nej"  
									<%
										if(rek.getSamtykke()==RekvisitionExtended.Samtykke.NEJ) { out.print("checked");}
										else out.print("disabled");
 									%>>
									Nej
									<input type="radio" id="uden_samtykke" name="samtykke" value="uden"  
									<%
										if(rek.getSamtykke()==RekvisitionExtended.Samtykke.UDEN_SAMTYKKE) { out.print("checked");}
										else out.print("disabled");
 									%>>
									Ikke i stand til samtykke
								</div>
								<label for="triage">Triage</label>
								<input type="text" id="triage" name="triage" disabled value="<%out.print(rek.getTriage()); %>">
								<label for="cave">Cave</label>
								<input type="text" id="cave" name="cave" disabled value="<%out.print(rek.getCave()); %>" >
							</li>
							<li>
								<h2>Rekvirent</h2>
								<label for="rekvirent">Rekvirent</label>
								<input type="text" id="rekvirent" name="rekvirent" disabled value="<%out.print(rek.getRekvirent().getFuldtNavn()); %>">
								<label for="henv_afd">Rekvirerende Afdeling</label>
								<input type="text" id="henv_afd" name="henv_afd" disabled value="<%out.print(rek.getHenvAfd()); %>">
								<label for="henv_laege">Henvisende læge</label>
								<input type="text" id="henv_laege" name="henv_laege" disabled value="<%out.print(rek.getHenvLaege()); %>">
								<label for="kontakt_tlf">Kontakt telefonnr</label>
								<input type="text" id="kontakt_tlf" name="kontakt_tlf" disabled value="<%out.print(rek.getKontaktTlf()); %>">
							</li>
						</ul>
						<hr />
						<div id="undersoegelsesData">
							<h2>Undersøgelse</h2>
							<label for="udf_indlagt">Udføres under indlæggelse</label>
							<div id="udf_indlagt">
								<input type="radio" name="udf_indlagt" id="ambulant"  value="ambulant" <%out.print(rek.getAmbulant() == null ? "disabled" : "checked"); %>>Ambulant
								<input type="radio" name="udf_indlagt" id="indlagt"  value="indlagt" <%out.print(rek.getUdfIndlagt() == null ? "disabled" : "checked"); %>>Indlæggelse
							</div>
							<label for="henvist_til">Henvist til</label>
							<div id="henvist_til">
								<input type="radio" id="radiologiskAfs" name="henvist_til" <%out.print(rek.getHenvistTil() == HenvistTil.RADIOLOGISK ? "checked" : "disabled"); %>  value="radiologisk">Radiologisk Afsnit
								<input type="radio" id="kliniskFysAfs" name="henvist_til" <%out.print(rek.getHenvistTil() == HenvistTil.KLINISK ? "checked" : "disabled"); %>  value="klinfys">Klinisk Fysiologisk Afsnit
							</div>
							<label for="hospitalsønske">Evt. Hospitalsønske</label>
							<div id="hospitalsønske">
								<input type="radio" id="hillerød" name="hospitals_oenske" <%out.print(rek.getHospitalOenske() == HospitalOenske.HILLEROED ? "checked" : "disabled"); %> value="hilleroed" >Hillerød
								<input type="radio" id="frederikssund" name="hospitals_oenske" <%out.print(rek.getHospitalOenske() == HospitalOenske.FREDERIKSSUND ? "checked" : "disabled"); %> value="frederikssund" >Frederikssund
								<input type="radio" id="helsingør" name="hospitals_oenske" <%out.print(rek.getHospitalOenske() == HospitalOenske.HELSINGOER ? "checked" : "disabled"); %> value="helsingoer" >Helsingør
							</div>
							<label for="prioriterings_oenske">Prioriteringsønske</label>
							<div id="prioriterings_oenske">
								<input type="radio" id="haste" name="prioriterings_oenske" value="haste" <%out.print(rek.getPrioritering() == Prioritering.HASTE ? "checked" : "disabled" ); %> >Haste
								<input type="radio" id="fremskyndet" name="prioriterings_oenske" value="fremskyndet" <%out.print(rek.getPrioritering() == Prioritering.FREMSKYNDET ? "checked" : "disabled" ); %> >Fremskyndet
								<input type="radio" id="rutine" name="prioriterings_oenske" value="rutine" <%out.print(rek.getPrioritering() == Prioritering.RUTINE ? "checked" : "disabled" ); %> >Rutine
								<input type="radio" id="pakkeforloeb" name="prioriterings_oenske" value="pakke" <%out.print(rek.getPrioritering() == Prioritering.PAKKEFORLOEB ? "checked" : "disabled" ); %> >Pakkeforløb
							</div>
							<div>
								<label for="modalitet_navn">Modalitet</label>
								<select id="modalitet_navn" tabindex="1" disabled>
									<option value="RTG" selected="selected"><%out.print(rek.getModalitet().getModalitetNavn()); %></option>
								</select>
							</div>
							<div>
								<label for="undersoegelses_type">Undersøgelses type</label>
								<input type="text" id="undersoegelses_type" value="<%out.print(rek.getUndersoegelsesType().getUndersoegelsesNavn() == null ? "" : rek.getUndersoegelsesType().getUndersoegelsesNavn() ); %>" disabled>
							</div>
						<p><label for="klinisk_problemstilling">Klinisk Problemstilling</label>
						Anamnese - objektive fund - evt. medicin og laboratoriesvar relevant for undersøgelsen (Se kliniske vejledninger).<br>
						Begrundelse for prioriteringsønske skal fremgå af den kliniske problemstilling.</p>
						<textarea name="klinisk_problemstilling" disabled><%out.print(rek.getKliniskProblemstilling() == null ? "" : rek.getKliniskProblemstilling()); %></textarea>
						<div id="transport">
							<label for="transport">Transport</label>
							<div id="ambulant_transport">
								<input type="radio" id="ingenKørsel" name="ambulant_transport" value="ingen" <%out.print(rek.getAmbulantKoersel() == AmbulantKoersel.INGEN ? "checked" : "disabled"); %> >Ingen Kørsel <br>
								<input type="radio" id="siddendeKørsel" name="ambulant_transport" value="siddende" <%out.print(rek.getAmbulantKoersel() == AmbulantKoersel.SIDDENDE ? "checked" : "disabled"); %> >Siddende Kørsel <br>
								<input type="radio" id="liggendeKørsel" name="ambulant_transport" value="liggende" <%out.print(rek.getAmbulantKoersel() == AmbulantKoersel.LIGGENDE ? "checked" : "disabled"); %> >Liggende Kørsel <br>
							</div>
							<div id="indlagt_transport">
								<input type="radio" id="gårUdenPortør" name="indlagt_transport" value="selv" <%out.print(rek.getIndlaeggelseTransport() == IndlaeggelseTransport.GAA_UDEN_PORTOER ? "checked" : "disabled"); %> >Går selv uden portør <br>
								<input type="radio" id="gårMedPortør" name="indlagt_transport" value="portoer" <%out.print(rek.getIndlaeggelseTransport() == IndlaeggelseTransport.GAA_MED_PORTOER ? "checked" : "disabled"); %> >Går selv med portør <br>
								<input type="radio" id="kørestolMedPortør" name="indlagt_transport" value="koerestol" <%out.print(rek.getIndlaeggelseTransport() == IndlaeggelseTransport.KOERESTOL ? "checked" : "disabled"); %> >Kørestol med portør <br>
								<input type="radio" id="seng" name="indlagt_transport" value="seng" <%out.print(rek.getIndlaeggelseTransport() == IndlaeggelseTransport.SENG ? "checked" : "disabled"); %> >Seng<br>
							</div>
							<label for="dato_forslag">Ønsket tidspunkt</label>
							<input type="text" id="dato_forslag" name="dato_forslag" value="<%out.print(rek.getDatoForslag() == null ? "" : rek.getDatoForslag()); %>" disabled>
						</div>
						
						<div id="gravididtet">
							<label for="gravididtet">Mulighed for graviditet</label>
							<input type="radio" name="graviditet" id="jaGravid" value="true" <%out.print(rek.getGraviditet() ? "checked" : "disabled"); %> >Ja
							<input type="radio" name="graviditet" id="nejGravid" value="false" <%out.print(!rek.getGraviditet() ? "checked" : "disabled"); %> >Nej
						</div>
						
						<div id="særligeForhold">
							<label for="særligeForhold">Særlige Forhold</label>
							<%
							//ingen særlige forhold check
							boolean ingenSaerligeForhold = false;
								if(!rek.getHoerehaemmet() && !rek.getSynshaemmet() && !rek.getAmputeret() && !rek.getKanIkkeStaa() &&
										!rek.getDement() && !rek.getAfasi() && rek.getIltLiterPrmin() == null && rek.getTolkSprog() == null && 
										rek.getIsolation() == null && rek.getCytostatikaDato() == null)
								{
									ingenSaerligeForhold = true;
								}
	 						%>
							<input type="checkbox" id="ingen_saerlige_forhold" name="ingen_saerlige_forhold" <%out.print(ingenSaerligeForhold ? "checked" : ""); %> disabled>Ingen særlige forhold <br>
							<input type="checkbox" id="hoerehaemmet" name="hoerehaemmet" <%out.print(rek.getHoerehaemmet() ? "checked" : ""); %> disabled>Hørehæmmet <br>
							<input type="checkbox" id="synshaemmet" name="synshaemmet" <%out.print(rek.getSynshaemmet() ? "checked" : ""); %> disabled>Synshæmmet <br>
							<input type="checkbox" id="amputeret" name="amputeret" <%out.print(rek.getAmputeret() ? "checked" : ""); %> disabled>Amputeret <br>
							<input type="checkbox" id="kan_ikke_staa" name="kan_ikke_staa" <%out.print(rek.getKanIkkeStaa() ? "checked" : ""); %> disabled>Kan ikke selv stå <br>
							<input type="checkbox" id="dement" name="dement" <%out.print(rek.getDement() ? "checked" : ""); %> disabled>Dement <br>
							<input type="checkbox" id="afasi" name="afasi" <%out.print(rek.getAfasi() ? "checked" : ""); %> disabled>Afasi <br>
							<label for="ilt">Ilt</label>
							<input type="text" id="ilt" name="ilt" value="<%out.print(rek.getIltLiterPrmin() == null ? "" : rek.getIltLiterPrmin().toString()); %>" disabled><br>
							<label for="tolk">Tolk</label>
							<input type="text" id="tolk" name="tolk" value="<%out.print(rek.getTolkSprog() == null ? "" : rek.getTolkSprog()); %>" disabled><br>
							<label for="isolation">Isolation</label>
							<input type="text" id="isolation" name="isolation" value="<%out.print(rek.getIsolation() == null ? "" : rek.getIsolation()); %>" disabled><br>
							<label for="cytostatika">Cytostatika</label>
							<input type="text" id="cytostatika" name="cytostatika" value="<%out.print(rek.getCytostatikaDato() == null ? "" : rek.getCytostatikaDato().toString()); %>" disabled>
						</div>
					</div>
					<div>
						<label for=tidl_billed_diagnostik>Tidligere undersøgelse</label>
						<p>Er patienten tidligere undersøgt i billeddiagnostisk regi indenfor hospitalerne i Nordsjælland?<BR/>
						Eller har patienten fået foretaget relevant undersøgelse udenfor hospitalerne i Nordsjælland?<BR/>
						Anfør undersøgelse, tidspunkt og hvor.</p>
						<textarea name="tidl_billed_diagnostik" disabled><%out.print(rek.getTidlBilledDiagnostik() == null ? "" : rek.getTidlBilledDiagnostik()); %></textarea>
					</div>		
				</div>
			</li>
			</ul>
			
				<div id="supplerendeData">
					<h2>
					<%
					String kontrolSkemaTekst=null;
					if(rek.getCTKontrastKontrolskemaId() != null){
						kontrolSkemaTekst = rek.getCtKontrastKontrolskema().toString();
					}
					
					if(rek.getMRKontrolskemaId() != null){
						kontrolSkemaTekst = rek.getMRKontrolskema().toString();
					}
					
					if(rek.getPETCTKontrolskemaId() != null){
						kontrolSkemaTekst = rek.getPetctKontrolskema().toString();
					}
					
					if(rek.getInvasivULKontrolskemaId() != null){
						//missing
						//kontrolSkemaTekst = rek.geti.toString();
					}
					
					if(kontrolSkemaTekst != null){
						kontrolSkemaTekst = kontrolSkemaTekst.replace("{", "</h2><p>");
						kontrolSkemaTekst = kontrolSkemaTekst.replace("}", "</p>");
						kontrolSkemaTekst = kontrolSkemaTekst.replace(",", "<br>");
						kontrolSkemaTekst = kontrolSkemaTekst.replace("=", ": ");
						kontrolSkemaTekst = kontrolSkemaTekst.replace("true", "ja");
						kontrolSkemaTekst = kontrolSkemaTekst.replace("false", "nej");
						out.println(kontrolSkemaTekst);
					}
					%> 
				</div>

	</body>
</html>


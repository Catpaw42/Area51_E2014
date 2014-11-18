package servlets;

import helperClasses.Const;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoledge.audao.db.dao.DaoException;

import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.CtKontrastKontrolskemaDao;
import database.dao.MRKontrolskemaDao;
import database.dao.ModalitetDao;
import database.dao.PETCTKontrolskemaDao;
import database.dao.PatientDao;
import database.dao.RekvisitionDao;
import database.dao.UndersoegelsesTypeDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dao.mysql.CtKontrastKontrolskemaDaoImpl;
import database.dao.mysql.MRKontrolskemaDaoImpl;
import database.dao.mysql.ModalitetDaoImpl;
import database.dao.mysql.PETCTKontrolskemaDaoImpl;
import database.dao.mysql.PatientDaoImpl;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dao.mysql.UndersoegelsesTypeDaoImpl;
import database.dto.Bruger;
import database.dto.CtKontrastKontrolskema;
import database.dto.MRKontrolskema;
import database.dto.Modalitet;
import database.dto.PETCTKontrolskema;
import database.dto.Patient;
import database.dto.RekvisitionExtended;
import database.dto.MRKontrolskema.MRBoern;
import database.dto.MRKontrolskema.MRVoksen;
import database.dto.PETCTKontrolskema.Formaal;
import database.dto.PETCTKontrolskema.KemoOgStraale;
import database.dto.RekvisitionExtended.AmbulantKoersel;
import database.dto.RekvisitionExtended.HenvistTil;
import database.dto.RekvisitionExtended.HospitalOenske;
import database.dto.RekvisitionExtended.IndlaeggelseTransport;
import database.dto.RekvisitionExtended.Prioritering;
import database.dto.RekvisitionExtended.Samtykke;
import database.dto.UndersoegelsesType;
import database.interfaces.IDataSourceConnector.ConnectionException;

/**
 * Servlet implementation class NyRekvisitionServlet
 */
@SuppressWarnings("serial")
@WebServlet("/NyRekvisitionServlet")
public class NyRekvisitionServlet extends HttpServlet
{      
	
	private static final String HENV_AFD = "henv_afd";
	private static final String PATIENT_TLF = "patient_tlf";
	private static final String PATIENT_NAVN = "patient_navn";
	private static final String PATIENT_ADRESSE = "patient_adresse";
	private static final String PATIENT_CPR = "patient_cpr";
	private static final boolean DEBUG = false;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NyRekvisitionServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Getting List of modalities - first create connection
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e) {
			//Connection Fails TODO message to user
			e.printStackTrace();
		}
		//And DAO
		ModalitetDao modDao = new ModalitetDaoImpl(conn);
		//Getting Modalities
		Modalitet[] modList = modDao.findDynamic(null, 0, -1, null);
		request.setAttribute(Const.MODALITY_LIST, modList);
		if (Const.DEBUG) System.out.println(modList);
		
		request.getRequestDispatcher(Const.NEW_REKVISITION_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		request.setCharacterEncoding("UTF-8");
		//Getting a database connection....
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e1) {
			e1.printStackTrace();
		}

		Bruger activeBruger = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		
		//making patient object...
		Patient pt = new Patient();	
//		pt.setFoedselsdag(Timestamp.valueOf(parseCPRBirthday(request.getParameter(PATIENT_CPR))));
		pt.setFoedselsdag(java.sql.Date.valueOf(parseCPRBirthday(request.getParameter(PATIENT_CPR))));
		pt.setPatientCpr(request.getParameter(PATIENT_CPR));
		pt.setPatientAdresse(request.getParameter(PATIENT_ADRESSE));
		pt.setPatientNavn(request.getParameter(PATIENT_NAVN));
		pt.setPatientTlf(request.getParameter(PATIENT_TLF));
		pt.setStamafdeling(activeBruger.getBrugerNavn());
		if (Const.DEBUG)System.out.println(pt);
		//Time to store patient
		Integer ptId = null;
		PatientDao ptDao = new PatientDaoImpl(conn);
		try {
			ptId = ptDao.insert(pt);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
		
		//Making Rekvisition DTO
		RekvisitionExtended rek = new RekvisitionExtended();
		rek.setPaaroerende(request.getParameter("paaroerende"));
		rek.setSamtykke(convertSamtykke(request)); //TODO validate samtykke.
		rek.setTriage(request.getParameter("triage"));
		rek.setCave(request.getParameter("cave"));//TODO validate
		try {
			rek.setRekvirentId(Integer.valueOf(request.getParameter("rekvirent_id")));
		} catch (NumberFormatException e){
			//TODO handle missing rekvirent 
			//returnToPage("Manglende RekvirentID);
		}
		rek.setRekvirentId(activeBruger.getBrugerId()); 
		rek.setHenvAfd(request.getParameter(HENV_AFD));
		rek.setHenvLaege(request.getParameter("henv_laege"));
		rek.setKontaktTlf(request.getParameter("kontakt_tlf"));
		rek.setUdfIndlagt(Boolean.valueOf(request.getParameter("udf_indlagt")));
		rek.setAmbulant(!rek.getUdfIndlagt());
		rek.setHenvistTil(convertHenvistTil(request));
		rek.setHospitalOenske(convertHospitalOenske(request));
		rek.setPrioritering(convertPrioritering(request));
		
		//Get undersøgelsesType data og gem en ny.
		Integer USTypeID = -1;
		
		UndersoegelsesType USType = new UndersoegelsesType();
		USType.setModalitetId(Integer.valueOf(request.getParameter("modalitet_navn")));
		USType.setUndersoegelsesNavn(request.getParameter("undersoegelses_type"));
		UndersoegelsesTypeDao usTypeDao = new UndersoegelsesTypeDaoImpl(conn) ;
		try {
			USTypeID = usTypeDao.insert(USType);
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		rek.setUndersoegelsesTypeId(USTypeID); //TODO FIXXX!!!
		
		rek.setKliniskProblemstilling(request.getParameter("klinisk_problemstilling"));
		rek.setAmbulantKoersel(convertAmbulantKoersel(request));
		rek.setIndlaeggelseTransport(convertIndlaeggelseTransport(request));
		rek.setDatoForslag(request.getParameter("dato_forslag"));
		rek.setGraviditet(Boolean.valueOf(request.getParameter("graviditet")));
		rek.setHoerehaemmet(getBoolFromCheckbox(request,"hoerehaemmet"));
		rek.setSynshaemmet(getBoolFromCheckbox(request, "synshaemmet"));
		rek.setAmputeret(getBoolFromCheckbox(request, "amputeret"));
		rek.setKanIkkeStaa(getBoolFromCheckbox(request, "kan_ikke_staa"));
		rek.setDement(getBoolFromCheckbox(request, "dement"));
		rek.setAfasi(getBoolFromCheckbox(request, "afasi"));
		try {
			rek.setIltLiterPrmin(Integer.valueOf(request.getParameter("ilt")));
		} catch (NumberFormatException e){
			rek.setIltLiterPrmin(null);
		}
		rek.setTolkSprog(request.getParameter("tolk"));
		rek.setIsolation(request.getParameter("isolation"));
		try {
			rek.setCytostatikaDato(java.sql.Date.valueOf(request.getParameter("cytostatika")));
			System.out.println(rek.getCytostatikaDato());
		} catch (IllegalArgumentException e) {
			rek.setCytostatikaDato(null);
		}
		rek.setTidlBilledDiagnostik(request.getParameter("tidl_billed_diagnostik"));
		rek.setPatientId(ptId);
		rek.setStatus(RekvisitionExtended.Status.PENDING);
		rek.setAfsendtDato(new Date());
		System.out.println(rek);
		//Check Modalitet
		String modalitet = request.getParameter("modalitet_navn");
		// can not switch on null - makes empty string instead should not happen
		modalitet = modalitet == null ? "" : modalitet;
		switch (modalitet) {
		case "invasiv_UL":
			Integer ULSkemaID = storeULInvKontrolSkema(request,response);
			rek.setInvasivULKontrolskemaId(ULSkemaID);
			break;
		case "MR":
			Integer MRSkemaID = null;
			try {
				MRSkemaID = storeMRSkema(request, response);
			} catch (DaoException e1) {
				// TODO Error page???
				e1.printStackTrace();
			}
			rek.setMRKontrolskemaId(MRSkemaID);
			break;
		case "CT_kontrast":
			Integer CTKSkemaID = null;
			try {
				CTKSkemaID = storeCTKSkema(request, response);
			} catch (DaoException e1) {
				// TODO Error page???
				e1.printStackTrace();
			}
			rek.setCTKontrastKontrolskemaId(CTKSkemaID);
			break;
		case "PETCT":
			Integer PETCTSkemaID = null;
			try {
				PETCTSkemaID = storePETCTSkema(request,response);
			} catch (DaoException e1) {
				// TODO Error page???
				e1.printStackTrace();
			}
			rek.setPETCTKontrolskemaId(PETCTSkemaID);
			break;
		default:
			//Now store the requisition
			RekvisitionDao rekDao = new RekvisitionDaoImplExt(conn);
			try {
				rekDao.insert(rek);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//HopeFully it went well ;)
			//TODO - real page
			PrintWriter out = response.getWriter();
			out.println("<HTML><BODY>Tak for din henvendelse - du kan følge med i status for din rekvisition i oversigten <BR>");
			out.println("<A HREF='RekvisitionServlet'>Tilbage til rekvisitioner</A></BODY><HTML>");
			break;
		}
		
		

	}

	private Integer storePETCTSkema(HttpServletRequest request,
			HttpServletResponse response) throws DaoException {
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e1) {
			e1.printStackTrace();
		}
		//TODO remove
				if (request.getSession().getAttribute(Const.ACTIVE_USER) == null) {
					BrugerDao bDao = new BrugerDaoImpl(conn);
					Bruger abruger = bDao.findByPrimaryKey(1);
					request.getSession().setAttribute(Const.ACTIVE_USER, abruger);
				}
		
		PETCTKontrolskema pck = new PETCTKontrolskema();
		pck.setFormaal(convertFormaalMetode(request));
		pck.setFormaalTekst(request.getParameter("formaal_text"));
		pck.setKanPtLiggeStille30(Boolean.valueOf(request.getParameter("kanPtLiggeStille30")));
		pck.setPtTaalerFaste(Boolean.valueOf(request.getParameter("ptTaalerFaste")));
		pck.setDiabetes(Boolean.valueOf(request.getParameter("diabetes")));
		pck.setDMBeh(request.getParameter("DM_Beh"));
		pck.setSmerter(Boolean.valueOf(request.getParameter("smerter")));
		pck.setRespInsuff(Boolean.valueOf(request.getParameter("respInsuff")));
		pck.setKlaustrofobi(Boolean.valueOf(request.getParameter("klaustrofobi")));
		pck.setAllergi(Boolean.valueOf(request.getParameter("allergi")));
		pck.setAllergiTekst(request.getParameter("allergi_tekst"));
		pck.setFedme(Boolean.valueOf(request.getParameter("fedme")));
		pck.setVaegt(Integer.valueOf(request.getParameter("vaegt")));
		pck.setBiopsi(Boolean.valueOf(request.getParameter("biopsi")));
		pck.setBiopsiTekst(request.getParameter("biopsi_tekst"));
		pck.setOperation(Boolean.valueOf(request.getParameter("operation")));
		pck.setOperationTekst(request.getParameter("operation_tekst"));
		pck.setKemoOgStraale(convertKemostraale(request));
		pck.setSidstePKreatTimestamp(Timestamp.valueOf(request.getParameter("straaleDato")));
		pck.setNedsatNyreFkt(Boolean.valueOf(request.getParameter("nedsatNyreFkt")));
		pck.setSidstePKreatinin(Integer.valueOf(request.getParameter("sidstePKreatinin")));
		pck.setSidstePKreatTimestamp(Timestamp.valueOf(request.getParameter("sidstePKreatTimestamp")));
		
		PETCTKontrolskemaDao petctkDao = new PETCTKontrolskemaDaoImpl(conn);
		return petctkDao.insert(pck);
	}

	private Integer storeCTKSkema(HttpServletRequest request,
			HttpServletResponse response) throws DaoException {
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e1) {
			e1.printStackTrace();
		}
		//TODO remove
				if (request.getSession().getAttribute(Const.ACTIVE_USER) == null) {
					BrugerDao bDao = new BrugerDaoImpl(conn);
					Bruger abruger = bDao.findByPrimaryKey(1);
					request.getSession().setAttribute(Const.ACTIVE_USER, abruger);
				}
		
		//	Making CTTKontrolskema dto
		CtKontrastKontrolskema ctk = new CtKontrastKontrolskema();
		ctk.setDiabetes(Boolean.valueOf(request.getParameter("diabetes")));
		ctk.setNyrefunktion(Boolean.valueOf(request.getParameter("nyrefunktion")));
		ctk.setNyreopereret(Boolean.valueOf(request.getParameter("nyreopereret")));
		ctk.setHjertesygdom(Boolean.valueOf(request.getParameter("hjertesygdom")));
		ctk.setMyokardieinfarkt(Boolean.valueOf(request.getParameter("myokardieinfarkt")));
		ctk.setProteinuri(Boolean.valueOf(request.getParameter("proteinuri")));
		ctk.setUrinsyregigt(Boolean.valueOf(request.getParameter("urinsyregigt")));
		ctk.setOver70(Boolean.valueOf(request.getParameter("over70")));
		ctk.setHypertension(Boolean.valueOf(request.getParameter("hypertension")));
		ctk.setNsaidPraeparat(Boolean.valueOf(request.getParameter("NSAIDpræparat")));
		ctk.setAllergi(Boolean.valueOf(request.getParameter("alergi")));
		ctk.setKontraststofreaktion(Boolean.valueOf(request.getParameter("konstrastofreaktion")));
		ctk.setAstma(Boolean.valueOf(request.getParameter("astma")));
		ctk.setHyperthyreoidisme(Boolean.valueOf(request.getParameter("hyperthyreoidisme")));
		ctk.setMetformin(Boolean.valueOf(request.getParameter("metformin")));
		ctk.setInterleukin2(Boolean.valueOf(request.getParameter("interleukin")));
		ctk.setBetaBlokkere(Boolean.valueOf(request.getParameter("betaBlokkere")));
		ctk.setPKreatininVaerdi(request.getParameter("Værdi"));
		//	setPKreatininTimestamp???
		ctk.setPtHoejde(Integer.valueOf(request.getParameter("Højde")));
		ctk.setPtVaegt(Integer.valueOf(request.getParameter("Vægt")));
		
		CtKontrastKontrolskemaDao ctkDao = new CtKontrastKontrolskemaDaoImpl(conn);
			return ctkDao.insert(ctk);		
	}

	private Integer storeMRSkema(HttpServletRequest request,
			HttpServletResponse response) throws DaoException {
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e1) {
			e1.printStackTrace();
		}
		//TODO remove
				if (request.getSession().getAttribute(Const.ACTIVE_USER) == null) {
					BrugerDao bDao = new BrugerDaoImpl(conn);
					Bruger abruger = bDao.findByPrimaryKey(1);
					request.getSession().setAttribute(Const.ACTIVE_USER, abruger);
				}
		
		MRKontrolskema mrk = new MRKontrolskema();
		mrk.setPacemaker(Boolean.valueOf(request.getParameter("pacemaker")));
		mrk.setMetalImplantater(Boolean.valueOf(request.getParameter("metal_implantater")));
		mrk.setMetalImplantaterBeskrivelse(request.getParameter("metal_implantater_beskrivelse"));
		mrk.setAndetMetalisk(Boolean.valueOf(request.getParameter("andet_metalisk")));
		mrk.setAndetMetaliskBeskrivelse(request.getParameter("andet_metalisk_beskrivelse"));
		mrk.setNyresygdom(Boolean.valueOf(request.getParameter("nyresygdom")));
		mrk.setNyresygdomKreatinin(Integer.valueOf(request.getParameter("nyresygdom_kreatinin")));
		mrk.setGraviditet(Boolean.valueOf(request.getParameter("graviditet")));
		mrk.setGraviditetUge(Integer.valueOf(request.getParameter("graviditet_uge")));
		mrk.setKlaustrofobi(Boolean.valueOf(request.getParameter("klaustrofobi")));
		mrk.setHoejde(Integer.valueOf(request.getParameter("hoejde")));
		mrk.setVaegt(Integer.valueOf(request.getParameter("vaegt")));
		mrk.setMRBoern(convertSederingBoern(request));
		mrk.setMRVoksen(convertSederingVoksen(request));
		mrk.setPraepForsyn(request.getParameter("praep_forsyn"));
		
		MRKontrolskemaDao mrkDao = new MRKontrolskemaDaoImpl(conn);
		return mrkDao.insert(mrk);
	}

	private Integer storeULInvKontrolSkema(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return -1;
	}

	private String parseCPRBirthday(String foedselsdagString) {
//		String  = request.getParameter(PATIENT_CPR);
		Integer foedeaar = Integer.valueOf(foedselsdagString.substring(4, 6));
		String digit7String = foedselsdagString.substring(6,7);
		if (digit7String.equalsIgnoreCase("-") ) digit7String = foedselsdagString.substring(7, 8);
		Integer digit7 = Integer.valueOf(digit7String);				
		if (digit7 <= 3  ){
			foedeaar = 1900 + foedeaar;
		} else {
			if ((digit7 == 4 || digit7 == 9) && foedeaar >=37){
				foedeaar = 1900 + foedeaar;	
			} else {
				if (foedeaar >=58 && (digit7 !=4||digit7!=9)){
					foedeaar = 1800 + foedeaar;
				} else {
					foedeaar = 2000 + foedeaar;
				}
			}
			
		}
		foedselsdagString = String.valueOf(foedeaar) + "-" + foedselsdagString.substring(2,4)+"-"+foedselsdagString.substring(0, 2);
		System.out.println("birthday from cpr: " + foedselsdagString);
//		Date d = new Date();
//		System.out.println("Date format: " + d.toString());
//		Timestamp t = Timestamp.valueOf(foedselsdagString);
//		new Date(foedselsdagString);
//		new Date(123);
//		System.out.println("come on: " + Date.parse(foedselsdagString));
//		System.out.println("new format: " + java.sql.Date.valueOf(d.toString()));
//		System.out.println("second fomrat: " + Date.parse(d.toString()));
//		foedselsdagString = foedselsdagString + " 00:00:00.000000000";
		
		return foedselsdagString;
	}

	private Boolean getBoolFromCheckbox(HttpServletRequest request,
			String boxname) {
		if("on".equals(request.getParameter(boxname))){
			// check box is selected
			return true;
		} else{
			// check box is not selected
			return false;
		}
	}

	private IndlaeggelseTransport convertIndlaeggelseTransport(
			HttpServletRequest request) {
		IndlaeggelseTransport indlTrans;
		String transString = request.getParameter("indlagt_transport");
		if (transString==null) return null;
		switch (transString) {
		case "selv":
			indlTrans = RekvisitionExtended.IndlaeggelseTransport.GAA_UDEN_PORTOER;
			break;
		case "portoer":
			indlTrans = RekvisitionExtended.IndlaeggelseTransport.GAA_MED_PORTOER;
			break;
		case "koerestol":
			indlTrans = RekvisitionExtended.IndlaeggelseTransport.KOERESTOL;
			break;
		case "seng":
			indlTrans = RekvisitionExtended.IndlaeggelseTransport.SENG;
			break;
		default:
			indlTrans=null;
			break;
		}
		return indlTrans;
	}

	private AmbulantKoersel convertAmbulantKoersel(HttpServletRequest request) {
		AmbulantKoersel ambuTrans;
		String transString = request.getParameter("ambulant_transport");
		if (transString==null) return null;
		switch (transString) {
		case "ingen":
			ambuTrans = RekvisitionExtended.AmbulantKoersel.INGEN;
			break;
		case "siddende":
			ambuTrans = RekvisitionExtended.AmbulantKoersel.SIDDENDE;
			break;
		case "liggende":
			ambuTrans = RekvisitionExtended.AmbulantKoersel.LIGGENDE;
			break;
		default:
			ambuTrans = null;
			break;
		}
		return ambuTrans;
	}

	private Prioritering convertPrioritering(HttpServletRequest request) {
		Prioritering prio;
		String prioString = request.getParameter("prioriterings_oenske");
		if (prioString==null)return null;
		switch (prioString) {
		case "haste":
			prio = RekvisitionExtended.Prioritering.HASTE; 
			break;
		case "fremskyndet":
			prio = RekvisitionExtended.Prioritering.FREMSKYNDET; 
			break;
		case "rutine":
			prio = RekvisitionExtended.Prioritering.RUTINE; 
			break;
		case "pakke":
			prio = RekvisitionExtended.Prioritering.PAKKEFORLOEB; 
			break;
		default:
			prio = null;
			break;
		}

		return prio;
	}

	private HospitalOenske convertHospitalOenske(HttpServletRequest request) {
		HospitalOenske hospOensk;
		String hospOenskString = request.getParameter("hospitals_oenske");
		if (hospOenskString==null) return null;
		switch (hospOenskString) {
		case "hilleroed":
			hospOensk = RekvisitionExtended.HospitalOenske.HILLEROED;
			break;
		case "frederikssund":
			hospOensk = RekvisitionExtended.HospitalOenske.FREDERIKSSUND;
			break;
		case "helsingoer":
			hospOensk = RekvisitionExtended.HospitalOenske.HELSINGOER;
			break;
		default:
			hospOensk=null;
			break;
		}
		return hospOensk;
	}

	private HenvistTil convertHenvistTil(HttpServletRequest request) {
		HenvistTil henv;
		String henvString = request.getParameter("henvist_til");
		if (henvString==null) return null;
		switch (henvString) {
		case "radiologisk":
			henv = RekvisitionExtended.HenvistTil.RADIOLOGISK;
			break;
		case "klinfys":
			henv = RekvisitionExtended.HenvistTil.KLINISK;
			break;
		default:
			henv = null;
			break;
		}
		return henv;
	}

	private Samtykke convertSamtykke(HttpServletRequest request) {
		Samtykke samtykke;
		String samtykkeString = request.getParameter("samtykke");
		if (samtykkeString == null) return null;
		switch (samtykkeString) {
		case "ja":
			samtykke = RekvisitionExtended.Samtykke.JA;
			break;
		case "nej":
			samtykke = RekvisitionExtended.Samtykke.NEJ;
			break;
		default:
			samtykke = RekvisitionExtended.Samtykke.UDEN_SAMTYKKE;
			break;
		}
		return samtykke;
	}
	
private MRBoern convertSederingBoern(HttpServletRequest request){
		
		MRBoern mrboern;
		String mrboernString = request.getParameter("sederingBoern");
		if (mrboernString == null) return null;
		switch (mrboernString) {
		case "uden_sedering":
			mrboern = MRKontrolskema.MRBoern.UDEN_SEDERING;
			break;
		case "i_generel_anaestesi":
			mrboern = MRKontrolskema.MRBoern.I_GENEREL_ANAESTESI;
			break;
		default:
			mrboern = MRKontrolskema.MRBoern.UDEN_SEDERING;
			break;
		}
		
		return mrboern;
	}

private MRVoksen convertSederingVoksen(HttpServletRequest request){
	
	MRVoksen mrvoksen;
	String mrvoksenString = request.getParameter("sederingVoksne");
	if (mrvoksenString == null) return null;
	switch (mrvoksenString) {
	case "uden_sedering":
		mrvoksen = MRKontrolskema.MRVoksen.UDEN_SEDERING;
		break;
	case "i_generel_anaestesi":
		mrvoksen = MRKontrolskema.MRVoksen.I_GENEREL_ANAESTESI;
		break;
	default:
		mrvoksen = MRKontrolskema.MRVoksen.UDEN_SEDERING;
		break;
	}
	
	return mrvoksen;
}

private KemoOgStraale convertKemostraale(HttpServletRequest request){
	
	KemoOgStraale kemoOgStraale;
	String kemiOgStraaleString = request.getParameter("aldrigGivetKemo");
	if (kemiOgStraaleString == null) return null;
	switch (kemiOgStraaleString) {
	case "aldrigGivetKemoJa":
		kemoOgStraale = PETCTKontrolskema.KemoOgStraale.ALDRIGGIVET;
		break;
	case "kemoterapiJa":
	case "stråleterapiNej":
		kemoOgStraale = PETCTKontrolskema.KemoOgStraale.KEMOTERAPI;
		break;
	case "kemoterapiNej":
	case "stråleterapiJa":
		kemoOgStraale = PETCTKontrolskema.KemoOgStraale.STRAALETERAPI;
		break;
	default:
		kemoOgStraale = PETCTKontrolskema.KemoOgStraale.KEMO_OG_STRAALE;
		break;
	}
	
	return kemoOgStraale;
}

private Formaal convertFormaalMetode(HttpServletRequest request){

	Formaal formaal = null;
	String formaalString = request.getParameter("formaal");
	if (formaalString == null) return null;
	switch (formaalString) {
	case "primardiag":
		formaal = PETCTKontrolskema.Formaal.PRIMAERDIAG;
		break;
	case "kontrolbeh":
		formaal = PETCTKontrolskema.Formaal.KONTROLBEH;
		break;
	case "kontrolremission":
		formaal = PETCTKontrolskema.Formaal.KONTROLREMISSION;
		break;
	case "kontrolrecidiv":
		formaal = PETCTKontrolskema.Formaal.KONTROLRECIDIV;
		break;
	}

	return formaal;
}

}

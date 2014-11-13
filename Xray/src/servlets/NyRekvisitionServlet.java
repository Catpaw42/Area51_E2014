package servlets;

import helperClasses.Const;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
































import com.spoledge.audao.db.dao.DaoException;

import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.DaoFactory;
import database.dao.DaoFactory.Factory;
import database.dao.ModalitetDao;
import database.dao.PatientDao;
import database.dao.RekvisitionDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dao.mysql.DaoFactoryImpl;
import database.dao.mysql.ModalitetDaoImpl;
import database.dao.mysql.PatientDaoImpl;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dto.Bruger;
import database.dto.Modalitet;
import database.dto.Patient;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.AmbulantKoersel;
import database.dto.RekvisitionExtended.HenvistTil;
import database.dto.RekvisitionExtended.HospitalOenske;
import database.dto.RekvisitionExtended.IndlaeggelseTransport;
import database.dto.RekvisitionExtended.Prioritering;
import database.dto.RekvisitionExtended.Samtykke;
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
	private static String NyRekPage = "nyRekvisitionPage.jsp";
	
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
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e) {
			//Connection Fails
			e.printStackTrace();
		}
		ModalitetDao modDao = new ModalitetDaoImpl(conn);
		Modalitet[] modList = modDao.findDynamic(null, 0, -1, null);
		request.setAttribute(Const.MODALITY_LIST, modList);
		request.getRequestDispatcher(NyRekPage).forward(request, response);
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
		//TODO remove
				if (request.getSession().getAttribute(Const.ACTIVE_USER) == null) {
					BrugerDao bDao = new BrugerDaoImpl(conn);
					Bruger abruger = bDao.findByPrimaryKey(1);
					request.getSession().setAttribute(Const.ACTIVE_USER, abruger);
				}
		Bruger activeBruger = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		
		//making patient object...
		Patient pt = new Patient();		
		pt.setFoedselsdag(java.sql.Date.valueOf(parseCprBirthday(request)));
		pt.setPatientCpr(request.getParameter(PATIENT_CPR));
		pt.setPatientAdresse(request.getParameter(PATIENT_ADRESSE));
		pt.setPatientNavn(request.getParameter(PATIENT_NAVN));
		pt.setPatientTlf(request.getParameter(PATIENT_TLF));
		pt.setStamafdeling(activeBruger.getBrugerNavn());
		System.out.println(pt);
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
		//Rekvisition.setPaaroerende TODO
		rek.setPaaroerende(request.getParameter("paaroerende"));
		rek.setSamtykke(convertSamtykke(request)); //TODO validate samtykke.
		rek.setTriage(request.getParameter("triage"));
		rek.setCave(request.getParameter("cave"));//TODO validate
		try {
			rek.setRekvirentId(Integer.valueOf(request.getParameter("rekvirent")));
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
		try {
			rek.setUndersoegelsesTypeId(Integer.valueOf(request.getParameter("undersoegelses_id")));
		} catch (NumberFormatException e) {
			//TODO meaningful handling of error
		}
		rek.setUndersoegelsesTypeId(1); //TODO FIXXX!!!
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
		//Time to store requisition
		RekvisitionDao rekDao = new RekvisitionDaoImpl(conn);
		try {
			rekDao.insert(rek);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HopeFully it went well ;)

		PrintWriter out = response.getWriter();
		out.println("<HTML><BODY>Tak for din henvendelse - du kan følge med i status for din rekvisition i oversigten <BR>");
		out.println("<A HREF='RekvisitionServlet'>Tilbage til rekvisitioner</A></BODY><HTML>");

	}

	private String parseCprBirthday(HttpServletRequest request) {
		String foedselsdagString = request.getParameter(PATIENT_CPR);
		Integer foedeaar = Integer.valueOf(foedselsdagString.substring(4, 6));
		String digit7String = foedselsdagString.substring(7,8);
		if (digit7String.equalsIgnoreCase("-") ) digit7String = foedselsdagString.substring(8, 9);
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

}

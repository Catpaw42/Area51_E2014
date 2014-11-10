package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





















import com.spoledge.audao.db.dao.DaoException;

import database.DataSourceConnector;
import database.dao.DaoFactory;
import database.dao.DaoFactory.Factory;
import database.dao.PatientDao;
import database.dao.RekvisitionDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dao.mysql.DaoFactoryImpl;
import database.dao.mysql.PatientDaoImpl;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dto.Bruger;
import database.dto.Patient;
import database.dto.Rekvisition;
import database.dto.Rekvisition.AmbulantKoersel;
import database.dto.Rekvisition.HenvistTil;
import database.dto.Rekvisition.HospitalOenske;
import database.dto.Rekvisition.IndlaeggelseTransport;
import database.dto.Rekvisition.Prioritering;
import database.dto.Rekvisition.Samtykke;
import database.interfaces.IDataSourceConnector.ConnectionException;

/**
 * Servlet implementation class NyRekvisitionServlet
 */
@SuppressWarnings("serial")
@WebServlet("/NyRekvisitionServlet")
public class NyRekvisitionServlet extends HttpServlet
{       
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
		//request.setAttribute("modaliteter", arg1); TODO send list of modalities
		//request.setAttribute("Undersoegelses_typer", Object[][] ); TODO send list of types
		request.getRequestDispatcher("nyRekvisitionPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Getting a database connection....
		Connection connection = null;
		try {
			connection = DataSourceConnector.getConnection();
		} catch (ConnectionException e1) {
			e1.printStackTrace();
		}
		//making patient object...
		Patient pt = new Patient();		
		pt.setFoedselsdag(java.sql.Date.valueOf(parseCprBirthday(request)));
		pt.setPatientCpr(request.getParameter("patient_cpr"));
		pt.setPatientAdresse(request.getParameter("patient_adresse"));
		pt.setPatientNavn(request.getParameter("patient_navn"));
		pt.setPatientTlf(request.getParameter("patient_tlf"));	
		System.out.println(pt);
		//Time to store patient
		Integer ptId = null;
		PatientDao ptDao = new PatientDaoImpl(connection);
		try {
			ptDao.insert(pt);
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Making Rekvisition DTO
		Rekvisition rek = new Rekvisition();
		//Rekvisition.setPaaroerende TODO
		rek.setSamtykke(convertSamtykke(request)); //TODO validate samtykke.
		rek.setTriage(request.getParameter("triage"));
		rek.setCave(request.getParameter("cave"));//TODO validate
		try {
			rek.setRekvirentId(Integer.valueOf(request.getParameter("rekvirent")));
		} catch (NumberFormatException e){
			//TODO handle missing rekvirent 
			//returnToPage("Manglende RekvirentID);
		}
		//TODO Rekvirerende afdeling findes ikke - bundet til brugeren?
		rek.setHenvLaege(request.getParameter("henv_laege"));
		rek.setKontaktTlf(request.getParameter("kontakt_tlf"));
		rek.setUdfIndlagt(Boolean.valueOf(request.getParameter("udf_indlagt")));
		rek.setHenvistTil(convertHenvistTil(request));
		rek.setHospitalOenske(convertHospitalOenske(request));
		rek.setPrioritering(convertPrioritering(request));
		try {
			rek.setUndersoegelsesTypeId(Integer.valueOf(request.getParameter("undersoegelses_id")));
		} catch (NumberFormatException e) {
			//TODO meaningful handling of error
		}
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
		rek.setTidlBilledDiagnostik(request.getParameter("tidl_billeddiagnostik"));
		rek.setPatientId(pt.getPatientId());//TODO change to ptID from DB call
		System.out.println(rek);
		//Time to store requisition
		RekvisitionDao rekDao = new RekvisitionDaoImpl(connection);
		try {
			rekDao.insert(rek);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HopeFully it went well ;)

		PrintWriter out = response.getWriter();
		out.println("Tak for din henvendelse - du kan følge med i status for din rekvisition i oversigten <BR>");
		out.println("<A HREF='RekvisitionServlet'>Tilbage til rekvisitioner</A>");

	}

	private String parseCprBirthday(HttpServletRequest request) {
		String foedselsdagString = request.getParameter("patient_cpr");
		Integer foedeaar = Integer.valueOf(foedselsdagString.substring(4, 6));
		if (new java.util.Date().getYear() - foedeaar >= 100 ){
			foedeaar = 2000 + foedeaar;
		} else {
			foedeaar = 1900 + foedeaar;
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
			indlTrans = Rekvisition.IndlaeggelseTransport.GAA_UDEN_PORTOER;
			break;
		case "portoer":
			indlTrans = Rekvisition.IndlaeggelseTransport.GAA_MED_PORTOER;
			break;
		case "koerestol":
			indlTrans = Rekvisition.IndlaeggelseTransport.KOERESTOL;
			break;
		case "seng":
			indlTrans = Rekvisition.IndlaeggelseTransport.SENG;
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
			ambuTrans = Rekvisition.AmbulantKoersel.INGEN;
			break;
		case "siddende":
			ambuTrans = Rekvisition.AmbulantKoersel.SIDDENDE;
			break;
		case "liggende":
			ambuTrans = Rekvisition.AmbulantKoersel.LIGGENDE;
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
			prio = Rekvisition.Prioritering.HASTE; 
			break;
		case "fremskyndet":
			prio = Rekvisition.Prioritering.FREMSKYNDET; 
			break;
		case "rutine":
			prio = Rekvisition.Prioritering.RUTINE; 
			break;
		case "pakke":
			prio = Rekvisition.Prioritering.PAKKEFORLOEB; 
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
			hospOensk = Rekvisition.HospitalOenske.HILLEROED;
			break;
		case "frederikssund":
			hospOensk = Rekvisition.HospitalOenske.FREDERIKSSUND;
			break;
		case "helsingoer":
			hospOensk = Rekvisition.HospitalOenske.HELSINGOER;
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
			henv = Rekvisition.HenvistTil.RADIOLOGISK;
			break;
		case "klinfys":
			henv = Rekvisition.HenvistTil.KLINISK;
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
			samtykke = Rekvisition.Samtykke.JA;
			break;
		case "nej":
			samtykke = Rekvisition.Samtykke.NEJ;
			break;
		default:
			samtykke = Rekvisition.Samtykke.UDEN_SAMTYKKE;
			break;
		}
		return samtykke;
	}

}

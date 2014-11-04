package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.spoledge.audao.db.dao.DaoException;

import database.DAOUser;
import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.DaoFactory;
import database.dao.RequisitionDao;
import database.dao.mysql.PetctKontrolskemaDaoImpl;
import database.dto.Bruger;
import database.dto.PetctKontrolskema;
import database.dto.PetctKontrolskema.Formaal;
import database.dto.PetctKontrolskema.KemoOgStraale;
import database.dto.Requisition;
import database.dto.Requisition.AmbulantKoersel;
import database.dto.Requisition.HenvistTil;
import database.dto.Requisition.HospitalOenske;
import database.dto.Requisition.IndlaeggelseTransport;
import database.dto.Requisition.Pririotering;
import database.dto.Requisition.Status;
import database.dto.Requisition.UndersoegelseModalitet;
import database.interfaces.IDataSourceConnector.ConnectionException;
//import dto.DTOexRequest;
//import dto.DTOexRequest.Status;
import dto.RequisitionTemplate.Prioritering;
import dto.MrKontrol;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		// Get Connection and Statement
		try {
			connection = DataSourceConnector.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e){
			e.printStackTrace();
		}catch (ConnectionException e1) {
			e1.printStackTrace();
		}
		
//		testUser(statement, connection);
		
//		testMrKontrol(statement, connection);
		
//		testExRequest(connection);
		System.out.println("##########tester bruger dto#############");
		testBruger(connection);
		System.out.println("#############tester petCtKontrolskema#########");
		testPetCtKontrolskema(connection);

	}
	
	private void testPetCtKontrolskema(Connection conn){
		//kan være null
		Boolean setDMRegime= null;
		Boolean setPOKontrast= null; 
		Boolean setPreMed= null;
		
		//kan ikke være null
		Boolean setDiabetes= true;
		Boolean setKanPtLiggeStille30= true;
		Boolean setKlaustrofobi= false; 
		Boolean setNedsatNyreFkt= true;
		Boolean setOperation= false;
		Boolean setBiopsi= true;
		Boolean setAllergi= false;
		Boolean setFedme= true;
		Boolean setIVKontrast= false;
		Boolean setPtTaalerFaste= false;
		Boolean setRelKontraIndCT= true;
		Boolean setRespInsuff= false;
		Boolean setSmerter= true; 
		
		String setAktuelPKreatAndetText = "det ser ikke så godt ud";
		String setAllergiText= null;
		String setBiopsiText= null;
		String setDatoForslag= null;
		String setDMBeh= null;
		String setFormaalAndetText= null;
		String setFormaalBehandlingsktrlText= null;
		String setFormaalRecidivText= null;
		String setOperationText= null;
		String setRelKontraIndCTText= null;
		String setTidlBilledDiagnostik= null;
		
		Integer setAktuelPKreatinin= null;
		Integer setPetctKontrolskemaId= null;
		Integer setSidstePKreatinin= null;
		Integer setVaegt= null;
		
		Date setAktuelPKreatTimestamp= null;
		Date setSidstePKreatTimestamp= null;
		
		Formaal setFormaal= null;
		KemoOgStraale setKemoOgStraale= null;
		
		
		PetctKontrolskema dto = new PetctKontrolskema();
		PetctKontrolskemaDaoImpl dao = new PetctKontrolskemaDaoImpl(conn);
		
		dto.setAktuelPKreatAndetText(setAktuelPKreatAndetText);
		dto.setAktuelPKreatinin(setAktuelPKreatinin);
		dto.setAktuelPKreatTimestamp(setAktuelPKreatTimestamp);
		dto.setAllergi(setAllergi);
		dto.setAllergiText(setAllergiText);
		dto.setBiopsi(setBiopsi);
		dto.setBiopsiText(setBiopsiText);
		dto.setDatoForslag(setDatoForslag);
		dto.setDiabetes(setDiabetes);
		dto.setDMBeh(setDMBeh);
		dto.setDMRegime(setDMRegime);
		dto.setFedme(setFedme);
		dto.setFormaal(setFormaal);
		dto.setFormaalAndetText(setFormaalAndetText);
		dto.setFormaalBehandlingsktrlText(setFormaalBehandlingsktrlText);
		dto.setFormaalRecidivText(setFormaalRecidivText);
		dto.setIVKontrast(setIVKontrast);
		dto.setKanPtLiggeStille30(setKanPtLiggeStille30);
		dto.setKemoOgStraale(setKemoOgStraale);
		dto.setKlaustrofobi(setKlaustrofobi);
		dto.setNedsatNyreFkt(setNedsatNyreFkt);
		dto.setOperation(setOperation);
		dto.setOperationText(setOperationText);
		dto.setPetctKontrolskemaId(setPetctKontrolskemaId);
		dto.setPOKontrast(setPOKontrast);
		dto.setPreMed(setPreMed);
		dto.setPtTaalerFaste(setPtTaalerFaste);
		dto.setRelKontraIndCT(setRelKontraIndCT);
		dto.setRelKontraIndCTText(setRelKontraIndCTText);
		dto.setRespInsuff(setRespInsuff);
		dto.setSidstePKreatinin(setSidstePKreatinin);
		dto.setSidstePKreatTimestamp(setSidstePKreatTimestamp);
		dto.setSmerter(setSmerter);
		dto.setTidlBilledDiagnostik(setTidlBilledDiagnostik);
		dto.setVaegt(setVaegt);
		
		int pk = 0;
		try {
			System.out.println("indsætter petctkontrol dto i database");
			pk = dao.insert(dto);
			System.out.println("###########dto indsat med#######");
			System.out.println("pk: " + pk);
		} catch (DaoException e) {
			System.err.println("petCtKontrol dto blev ikke succesfuldt sat ind i database");
			e.printStackTrace();
			System.out.println("########################################");
		}
		System.out.println("henter petctkontrolskema med pk: " + pk);
		PetctKontrolskema fdto = dao.findByPrimaryKey(pk);
		System.out.println("pk: " + fdto.getPetctKontrolskemaId());
		System.out.println("aktuelpkreatinandettekst: " + fdto.getAktuelPKreatAndetText());
		System.out.println("########################################");
		System.out.println("henter ikke eksisterende objekt... pk: " + pk + 1000);
		fdto = dao.findByPrimaryKey(pk+1000);
		try{
			System.out.println("aktuelpkreatinandettekst: " + fdto.getAktuelPKreatAndetText());
		} catch (NullPointerException e){
			System.out.println("nullPointerException kastet - objektet er null");
		}
		
	}

	private void testMrKontrol(Statement statement, Connection connection) {
		MrKontrol m = new MrKontrol();
		m.setAllergi(true);
		m.setAminoglykosider(false);
		m.setAstma(true);
		m.setBetaBlokkere(false);
		m.setCtKontrastKontrolskemaId(-1);
		m.setDiabetes(null);
		m.setHjertesygdom(false);
		m.setHypertension(true);
		m.setHyperthyreoidisme(false);
		m.setInterleukin2(true);
		m.setKontraststofreaktion(false);
		m.setMetformin(null);
		m.setMyokarieinfarkt(false);
		m.setNsaidPraeparat(true);
//		m.setNyrefunktion(_val);
		m.setNyreopereret(false);
		m.setOver70(true);
		m.setPKreatinTimestamp(new Date());
		m.setPKreatinVaerdi("meget h�j");
		m.setProteinuri(true);
		m.setPtHoejde(198);
		m.setPtVaegt(32);
		m.setUrinsyregigt(true);
		
		
		
		
	}
	
	private void testBruger(Connection conn){
		int id = 2;
		String brugernavn = "Hans";
		boolean erAktiv = true; 
		
		Bruger dto = new Bruger();
		//dto.setBrugerId(id);
		dto.setBrugerNavn(brugernavn);
		dto.setErAktiv(erAktiv);
		BrugerDao dao = DaoFactory.createBrugerDao(conn);
		try {
			System.out.println("\n forsøger at inds�tte bruger i database");
			System.out.println("#######bruger til inds�tning#####");
			System.out.println("brugerId: " + id);
			System.out.println("brugernavn: " + brugernavn);
			System.out.println("erAktiv: " + erAktiv);
			System.out.println("################");
			Bruger alreadyExist = dao.findByPrimaryKey(id);
			
			if(alreadyExist != null){
				System.out.println("bruger findes allerede med id= " + id);
				System.out.println("#########fundet bruger###########");
				System.out.println("brugerid: " + alreadyExist.getBrugerId());
				System.out.println("brugernavn: " + alreadyExist.getBrugerNavn());
				System.out.println("erAktiv: " + alreadyExist.getErAktiv());
				System.out.println("#################################");
				System.out.println("brugerid på givne dto ignoreres, og bliver automatisk sat i database");
			}
			int pk = dao.insert(dto);
			System.out.println("bruger tilføjet database");
			System.out.println("####tilføjet bruger#########");
			System.out.println("brugerId: " + pk);
		} catch (DaoException e) {
			System.err.println("fejlede at tilføje bruger til database");
		}
		
		try {
			
			System.out.println("\n forsøger at hente bruger fra database");
			System.out.println("#######user#####");
			System.out.println("brugerId: " + id);
			System.out.println("################");
			Bruger nDto = dao.findByPrimaryKey(id);
			System.out.println("#####fundet bruger#######");
			System.out.println("brugerId: " + nDto.getBrugerId());
			System.out.println("brugernavn: " + nDto.getBrugerNavn());
			System.out.println("erAktiv: " + nDto.getErAktiv());
		} catch (Exception e) {
			System.err.println("fejlede at hente bruger fra database");
		}
		
		
		
		erAktiv = !erAktiv;
		try{
			System.out.println("\n forsøger at opdatere erAktiv for bruger i database");
			System.out.println("######opdatering til bruger######");
			System.out.println("userid: " + id);
			System.out.println("isActive: " + erAktiv);
			System.out.println("################");
			dao.updateErAktiv(id, erAktiv);
			System.out.println("bruger opdateret");
		}catch (DaoException e){
			System.err.println("fejlede at opdatere bruger i database");
		}
		
	}
	
	private void testBrugerInput(String message, Bruger dto, BrugerDao dao, int id, String brugernavn, boolean erAktiv){
		
	}
	
	private void testExRequest(Connection conn){
		Long primaryKey = new Long(2);
//		DTOexRequest dtod = new DTOexRequest(-1, 2, 1, 0, Status.PENDING, new Timestamp(new Date().getTime()), null, new Timestamp(new Date(0).getTime()), null);
//		DaoFactory f = new DaoFactory();
		System.err.println("trying to get dao \n");
		RequisitionDao dao = DaoFactory.createRequisitionDao(conn);
		System.err.println("dao aquired");
		Requisition dto = new Requisition();
		dto.setAmbulant(true);
		dto.setAmbulantKoersel(AmbulantKoersel.LIGGENDE);
		dto.setCave("utrolig farligt alts�");
		dto.setDatoForslag("198-123-1");
		dto.setGraviditet(true);
		dto.setHenvistTil(HenvistTil.RADIOLOGISK);
		dto.setHospitalOenske(HospitalOenske.FREDERIKSSUND);
		dto.setIndlaeggelseTransport(IndlaeggelseTransport.GAA_MED_PORTOER);
		dto.setPririotering(Pririotering.PAKKEFORLOEB);
		dto.setRekvisitionId(primaryKey); // primary key
		dto.setSamtykke(true);
		dto.setStatus(Status.APPROVED);
		dto.setUdfIndlagt(false);
		dto.setUndersoegelseModalitet(UndersoegelseModalitet.CT_KONTRAST);
		
		try {
			// test insert
			System.out.println("insert dto: pr-key: " + dto.getRekvisitionId() + "...");
			dao.insert(dto);
			System.out.println("dto inserted");
			// test findByPrimary key by searching for previous inserted object
			System.out.println("searching for inserted dto pr-key: " + dto.getRekvisitionId() + "...");
			Requisition r =  dao.findByPrimaryKey(dto.getRekvisitionId());
			System.out.println("objects primary key: " + r.getRekvisitionId());
			
			//test update of status
			System.out.println("updating status...");
			System.out.println("current status: " + dto.getStatus().toString() + " vs " + r.getStatus() );
			boolean success = dao.updateStatus(dto.getRekvisitionId(), Status.CANCELED);
			System.out.println("update was a success: " + success);
			r = dao.findByPrimaryKey(primaryKey);
			System.out.println("new status vs old: " + r.getStatus() + " vs " + dto.getStatus());
			
			
		} catch (DaoException e) {
			System.err.println("failed to insert row");
			e.printStackTrace();
		}
		
	}

	private void testUser(Statement statement, Connection connection) {
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM users";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=statement)statement.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=connection)connection.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

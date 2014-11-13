package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.spoledge.audao.db.dao.DaoException;

import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.DaoFactory;
import database.dao.ModalitetDao;
import database.dao.PETCTKontrolskemaDao;
import database.dao.RekvisitionDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dao.mysql.BrugerDaoImplExtended;
import database.dao.mysql.ModalitetDaoImpl;
import database.dao.mysql.PETCTKontrolskemaDaoImpl;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dto.Bruger;
import database.dto.CtKontrastKontrolskema;
import database.dto.Modalitet;
import database.dto.PETCTKontrolskema;
import database.dto.PETCTKontrolskema.Formaal;
import database.dto.PETCTKontrolskema.KemoOgStraale;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.AmbulantKoersel;
import database.dto.RekvisitionExtended.HenvistTil;
import database.dto.RekvisitionExtended.HospitalOenske;
import database.dto.RekvisitionExtended.IndlaeggelseTransport;
import database.dto.RekvisitionExtended.Prioritering;
import database.dto.RekvisitionExtended.Samtykke;
import database.dto.RekvisitionExtended.Status;
import database.interfaces.IDataSourceConnector.ConnectionException;
//import dto.DTOexRequest;
//import dto.DTOexRequest.Status;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		System.out.println("\n \n#############tester petCtKontrolskema#########");
		testPetCtKontrolskema(connection);
		System.out.println("\n \n#############test exrequest###################");
		testExRequest(connection);
		System.out.println("\n \n#############test modalitet###################");
		testModalitet(connection);
		System.out.println("\n \n#############test mrKontrol###################");
		testMrKontrol(statement, connection);
		System.out.println("\n \n#############test rettigheder#################");
		testRettigheder(connection);
		System.out.println("\n \n############# test adv search#################");
		testAdvSearch(connection);
//		testBrugerValidering(connection);



	}

	private void testAdvSearch(Connection connection) {
		RekvisitionExtended[] r = null;
		RekvisitionDaoImplExt dao = new RekvisitionDaoImplExt(connection);
		// test: "Røntgen"
		r = dao.findByAdvSearch(null, null, null, null, null, null);
		System.out.println("################ adv search########################");
		System.out.println("arrayStørrelse: " + r.length);
		for (RekvisitionExtended rekvisition : r) {
			System.out.println(rekvisition.getRekvisitionId());
		}
		System.out.println(r.length + " funde rekvisitioner###################");
		
		System.out.println("############### dynamic search####################");
		RekvisitionExtended[] rek = dao.findDynamic("status=?", 0, -1, new Object[]{Status.PENDING});
		for (RekvisitionExtended rekvisition : rek) {
			System.out.println("id: " + rekvisition.getRekvisitionId());
			System.out.println("status: " + rekvisition.getStatus());
		}
		
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


		PETCTKontrolskema dto = new PETCTKontrolskema();
		PETCTKontrolskemaDao dao = new PETCTKontrolskemaDaoImpl(conn);

		dto.setAktuelAndetTekst(setAktuelPKreatAndetText);
		dto.setAktuelPKreatinin(setAktuelPKreatinin);
		dto.setAktuelPKreatTimestamp(setAktuelPKreatTimestamp);
		dto.setAllergi(setAllergi);
		dto.setAllergiTekst(setAllergiText);
		dto.setBiopsi(setBiopsi);
		dto.setBiopsiTekst(setBiopsiText);
		dto.setDiabetes(setDiabetes);
		dto.setDMBeh(setDMBeh);
		dto.setDMRegime(setDMRegime);
		dto.setFedme(setFedme);
		dto.setFormaal(setFormaal);
		dto.setFormaalTekst(setFormaalAndetText);
		dto.setIVKontrast(setIVKontrast);
		dto.setKanPtLiggeStille30(setKanPtLiggeStille30);
		dto.setKemoOgStraale(setKemoOgStraale);
		dto.setKlaustrofobi(setKlaustrofobi);
		dto.setNedsatNyreFkt(setNedsatNyreFkt);
		dto.setOperation(setOperation);
		dto.setOperationTekst(setOperationText);
		dto.setPETCTKontrolskemaId(setPetctKontrolskemaId);
		dto.setPOKontrast(setPOKontrast);
		dto.setPreMed(setPreMed);
		dto.setPtTaalerFaste(setPtTaalerFaste);
		dto.setRespInsuff(setRespInsuff);
		dto.setSidstePKreatinin(setSidstePKreatinin);
		dto.setSidstePKreatTimestamp(setSidstePKreatTimestamp);
		dto.setSmerter(setSmerter);
		dto.setVaegt(setVaegt);

		int pk = 0;
		try {
			System.out.println("indsætter petctkontrol dto i database");
			pk = dao.insert(dto);
			System.out.println("###########dto indsat med#######");
			System.out.println("pk: " + pk);
		} catch (DaoException e) {
			System.err.println("petCtKontrol dto blev ikke succesfuldt sat ind i database \n");
			System.err.println(e.getMessage());
			System.out.println("\n ########################################");
		}
		System.out.println("henter petctkontrolskema med pk: " + pk);
		PETCTKontrolskema fdto = dao.findByPrimaryKey(pk);
		System.out.println("fdto " + fdto);
		System.out.println("pk: " + (fdto == null ? "no dto found" : fdto.getPETCTKontrolskemaId()));
		System.out.println("aktuelpkreatinandettekst: " + (fdto == null ? "no dto found" : fdto.getAktuelPKreatinin()));
		System.out.println("########################################");
		System.out.println("henter ikke eksisterende objekt... pk: " + pk + 1000);
		fdto = dao.findByPrimaryKey(pk+1000);
		try{
			System.out.println("aktuelpkreatinandettekst: " + fdto.getAktuelPKreatinin());
		} catch (NullPointerException e){
			System.out.println("nullPointerException kastet - objektet er null");
		}

	}

	private void testMrKontrol(Statement statement, Connection connection) {
		CtKontrastKontrolskema m = new CtKontrastKontrolskema();
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
		m.setMyokardieinfarkt(false);
		m.setNsaidPraeparat(true);
		//		m.setNyrefunktion(_val);
		m.setNyreopereret(false);
		m.setOver70(true);
		m.setPKreatininTimestamp(new Date());
		m.setPKreatininVaerdi("meget h�j");
		m.setProteinuri(true);
		m.setPtHoejde(198);
		m.setPtVaegt(32);
		m.setUrinsyregigt(true);


		

	}

	
	private void testBruger(Connection conn){
		int id = 2;
		String brugernavn = "mumming";
		String fuldtnavn = "Martin Nielsen";
		String kodeord = "1234";
		boolean erAktiv = true; 

		Bruger dto = new Bruger();
		//dto.setBrugerId(id);
		dto.setBrugerNavn(brugernavn);
		dto.setErAktiv(erAktiv);
		dto.setFuldtNavn(fuldtnavn);
		dto.setKodeord(kodeord);
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
			System.err.println(e.getMessage());
		}

		try {

			System.out.println("\n forsøger at hente bruger fra database");
			System.out.println("#######user#####");
			System.out.println("brugerId: " + id);
			Bruger nDto = dao.findByPrimaryKey(id);
			System.out.println("#####fundet bruger#######");
			System.out.println("brugerId: " + nDto.getBrugerId());
			System.out.println("brugernavn: " + nDto.getBrugerNavn());
			System.out.println("erAktiv: " + nDto.getErAktiv());
		} catch (Exception e) {
			System.err.println("fejlede at hente bruger fra database");
		}
		
		try {
		System.out.println("#######findByUserName#####");
		BrugerDaoImplExtended bdie = null;
		bdie = new BrugerDaoImplExtended(conn);
		Bruger b = bdie.findByUserName(brugernavn, kodeord);
		System.out.println(b.getBrugerNavn());
		System.out.println("################");
		}	catch(Exception e){
			System.out.println("Fejlede i at finde bruger efter brugernavn");
		}


		erAktiv = !erAktiv;
		try{
			System.out.println("\n forsøger at opdatere erAktiv for bruger i database");
			System.out.println("######opdatering til bruger######");
			System.out.println("userid: " + id);
			System.out.println("isActive: " + erAktiv);
			System.out.println("################");
			dto.setErAktiv(erAktiv);
			dao.update(id, dto);
			System.out.println("bruger opdateret");
		}catch (DaoException e){
			System.err.println("fejlede at opdatere bruger i database");
			System.err.println(e.getMessage());
		}

	}
	
	private void testBrugerValidering(Connection conn){
		String brugernavn = "mumming";
		String kodeord = "1234";
		BrugerDao brugerDao = null;
		brugerDao = new BrugerDaoImpl(conn);
		System.out.println("######VALIDERING AF BRUGER######");
//		if(brugerDao.validate(brugernavn, kodeord)){
			System.out.println("User was validated.");
//		} else{
			System.out.println("User was NOT validated.");
//		}
		System.out.println("################");
	}

	private void testBrugerInput(String message, Bruger dto, BrugerDao dao, int id, String brugernavn, boolean erAktiv){

	}



	private void testExRequest(Connection conn){
		Integer primaryKey = new Integer(2);
		//		DTOexRequest dtod = new DTOexRequest(-1, 2, 1, 0, Status.PENDING, new Timestamp(new Date().getTime()), null, new Timestamp(new Date(0).getTime()), null);
		//		DaoFactory f = new DaoFactory();
		System.err.println("trying to get dao \n");
		RekvisitionDao dao = DaoFactory.createRekvisitionDao(conn);
		System.err.println("dao aquired");
		RekvisitionExtended dto = new RekvisitionExtended();
		dto.setAmbulant(true);
		dto.setAmbulantKoersel(AmbulantKoersel.LIGGENDE);
		dto.setCave("utrolig farligt alts�");
		dto.setDatoForslag("198-123-1");
		dto.setGraviditet(true);
		dto.setHenvistTil(HenvistTil.RADIOLOGISK);
		dto.setHospitalOenske(HospitalOenske.FREDERIKSSUND);
		dto.setIndlaeggelseTransport(IndlaeggelseTransport.GAA_MED_PORTOER);
		dto.setPrioritering(Prioritering.PAKKEFORLOEB);
		dto.setRekvisitionId(primaryKey); // primary key
		dto.setSamtykke(Samtykke.JA);
		dto.setStatus(Status.APPROVED);
		dto.setUdfIndlagt(false);
		dto.setUndersoegelsesTypeId(2);

		try {
			// test insert
			System.out.println("insert dto: pr-key: " + dto.getRekvisitionId() + "...");
			dao.insert(dto);
			System.out.println("dto inserted");
			// test findByPrimary key by searching for previous inserted object
			System.out.println("searching for inserted dto pr-key: " + dto.getRekvisitionId() + "...");
			RekvisitionExtended r =  dao.findByPrimaryKey(dto.getRekvisitionId());
			System.out.println("objects primary key: " + r.getRekvisitionId());

			//test update of status
			System.out.println("updating status...");
			System.out.println("current status: " + dto.getStatus().toString() + " vs " + r.getStatus() );
			dto.setStatus(Status.CANCELED);
			boolean success = dao.update(dto.getRekvisitionId(), dto);
			System.out.println("update was a success: " + success);
			r = dao.findByPrimaryKey(primaryKey);
			System.out.println("new status vs old: " + r.getStatus() + " vs " + dto.getStatus());


		} catch (DaoException e) {
			System.err.println("failed to insert row");
			System.err.println(e.getMessage());
		}

	}

	private void testModalitet(Connection conn){
		Modalitet dto = new Modalitet();
		dto.setModalitetNavn("test modalitet");
		ModalitetDao dao = new ModalitetDaoImpl(conn);
		try {
			System.out.println("indsætter Modalitetdto ind i database...");
			System.out.println("#######Modalitet##########");
			System.out.println("modalitetnavn: " + dto.getModalitetNavn());
			System.out.println("##########################");
			int id = dao.insert(dto);
			System.out.println("dto indsat med id: " + id);
		} catch (DaoException e) {
			System.err.println("Failed to insert dto into database");
			System.err.println(e.getMessage());
		}
	}

	private void testRettigheder(Connection conn){

	}

	//	private void testUser(Statement statement, Connection connection) {
	//		ResultSet resultSet = null;
	//		try {
	//			String query = "SELECT * FROM users";
	//			resultSet = statement.executeQuery(query);
	//			while (resultSet.next()) {
	//				System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3));
	//			}
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		}finally {
	//			try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
	//			{e.printStackTrace();}
	//			try { if(null!=statement)statement.close();} catch (SQLException e) 
	//			{e.printStackTrace();}
	//			try { if(null!=connection)connection.close();} catch (SQLException e) 
	//			{e.printStackTrace();}
	//		}
	//	}



}

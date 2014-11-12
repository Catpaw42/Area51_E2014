package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.Timestamp;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.spoledge.audao.db.dao.DaoException;

import database.DataBaseController;
import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.ModalitetDao;
import database.dao.PatientDao;
import database.dao.RekvisitionDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dao.mysql.BrugerDaoImplExtended;
import database.dao.mysql.ModalitetDaoImpl;
import database.dao.mysql.PatientDaoImpl;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dto.Bruger;
import database.dto.Modalitet;
import database.dto.Patient;
import database.dto.Rekvisition.Status;
import database.dto.RekvisitionExtended;
import database.interfaces.IDataSourceConnector.ConnectionException;
import database.DataSourceConnector;
import database.dao.RekvisitionDao;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dto.Rekvisition;

/**
 * Servlet implementation class RekvisitionServlet
 */
@WebServlet("/RekvisitionServlet")
public class RekvisitionServlet extends HttpServlet {
	private java.sql.Connection conn = null;
	public static final long serialVersionUID = 1L;
	public static final String REKVISITION_LIST = "rekvisitionList";
	public static final String ACTIVE_USER = "activeUser";
	public static final String REKVISITION_PAGE = "rekvisitionPage.jsp";

	private static final String PARAM_CPR = "cpr";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_MODALITY = "modality";
	private static final String PARAM_DEPARTMENT = "department";
	private static final String PARAM_DATE = "date";

	// used for the search boxes in rekvisitionPage.jsp
	public static final String MODALITY_LIST = "modalityList";
	public static final String STATUS_LIST = "statusList";
	public Modalitet[] modList = null;
	public Status[] statusList = null;		
	////////////////////////////////////////////////////	

	private BrugerDaoImplExtended  userDao;
	private RekvisitionDaoImplExt rekvisitionDao;
	private ModalitetDao modDao;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RekvisitionServlet() {
		super();
		try {
			this.conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		this.userDao = new BrugerDaoImplExtended(conn);
		this.rekvisitionDao = new RekvisitionDaoImplExt(conn);
		this.modDao = new ModalitetDaoImpl(conn);

		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createSearchDropdowns(request);
		String condStatus = "status=?";
		String condRekvUserName = "bruger_navn=?";
		String condRekvirentId = "rekvirent_id=?";

		Bruger activeUser = (Bruger) request.getAttribute(ACTIVE_USER);

		if(activeUser == null){ // kun af test grunde
			activeUser = userDao.findByPrimaryKey(1);
		}


		Rekvisition[] rekvlist;
		// gets list of the active user
		rekvlist = rekvisitionDao.findDynamic(condRekvirentId, 0, -1, activeUser.getBrugerId());
		// rekvlist = rekvisitionDao.findDynamic(cond, 0, -1, new Object[]{Status.PENDING}); //(cond, 0, -1, new Object[]{Status.PENDING});

		request.setAttribute(REKVISITION_LIST, rekvlist);	
		request.getRequestDispatcher(REKVISITION_PAGE).forward(request, response);

	}
	/**
	 * have to be called so the dropdowns in rekvisitionPage are filled
	 * @param request
	 */
	private void createSearchDropdowns(HttpServletRequest request){
		request.setAttribute(MODALITY_LIST, modDao.findDynamic(null, 0, -1, null));
		request.setAttribute(STATUS_LIST, Status.values());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createSearchDropdowns(request);
		ArrayList<Object> params = new ArrayList<>();
		String cond = "";
		// parameters
		String cpr = request.getParameter(PARAM_CPR);
		String name = request.getParameter(PARAM_NAME);
		String modality = request.getParameter(PARAM_MODALITY);
		String department = request.getParameter(PARAM_DEPARTMENT);
		String date = request.getParameter(PARAM_DATE).replace(" ", "");
		Timestamp timestamp = null;

		System.out.println("#######DATE: " + date);
		timestamp = (validateDate(date) ? stringToTimestamp(date) : null);
		System.out.println("#####TIMESTAMP: " + timestamp);

		String status = request.getParameter("status");
		Status statusObj = null;

		for(int i = 0; i < Status.values().length; i++){
			System.out.println("values: " + Status.values()[i]);
			if(status.equalsIgnoreCase(Status.values()[i].name())){
				System.out.println("values: " + Status.values()[i]);
				statusObj = Status.values()[i];
			}
		}

		// objekter
		Rekvisition[] rekvDto;
		//TODO dao'er. skal ændres til almindeligt interface senere
		RekvisitionDaoImplExt rekvDao = new RekvisitionDaoImplExt(conn);
		rekvDto = rekvDao.findByAdvSearch(cpr, name, modality, statusObj, timestamp, department);

		//##############TEST######################
		//		Patient p1 = new Patient();
		//		p1.setPatientId(1);
		//		p1.setPatientNavn("nansy");
		//		p1.setPatientCpr("1234");
		//		p1.setStamafdeling("gsdf");
		//		p1.setPatientAdresse("test");
		//		p1.setPatientTlf("sdfsgf");
		//		p1.setFoedselsdag(new Date());
		//		
		//		PatientDao dao1 = new PatientDaoImpl(conn);
		//		try {
		//			dao1.insert(p1);
		//		} catch (DaoException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}


		//#########################################
		//		Rekvisition[] rekv;
		//		String modalitet = request.getParameter("modality");
		//		String afdeling = request.getParameter("department");
		//		String dato = request.getParameter("date");
		//		String status = request.getParameter("status");




		// sammensætter select statement til at finde patient

		if(cpr != null){
			cond = "patient_cpr=?";
			params.add(cpr);
		}
		if(name != null){
			cond = cond + (cpr != null ? "AND patient_navn=?": "patient_navn=?");
			params.add(name);
		}
		if(department != null){
			cond = cond + (name != null || cpr != null ? "AND stamafdeling=?" : "stamafdeling=?");
			params.add(department);
		}


		// sammensætter select statement til at finde rekvisition
		if(modality != null){
			//			rekvCond = "modalitet=?"
		}


		System.out.print(cond);
		for(Object pa : params){
			System.out.println(pa.toString());
		}

		//		cond = cond + modality
		//		cond = cond + department
		//		cond = cond + date
		//		cond = 

		PatientDao dao = new PatientDaoImpl(conn);
		Patient[] p = dao.findDynamic(cond, 0, -1, params.toArray());
		Patient[] p2 = dao.findDynamic(null, 0, -1, null);
		System.out.println("p2 length: " + p2.length);
		System.out.println("p2 test: " + p2[0].getPatientNavn() + "##########");
		System.out.println("yay");
		if(p != null){
			System.out.println(p.length);
			//			System.out.println(p[0].getPatientAdresse());
			System.out.println("lnyay2");
			for (Patient patient : p) {
				System.out.println("yay3");
				System.out.println(patient.getPatientNavn());
			}
		}
		request.setAttribute(REKVISITION_LIST, rekvDto);


		request.getRequestDispatcher(REKVISITION_PAGE).forward(request, response);

	}

	private boolean validateCpr(String cpr){
		return cpr.matches("(\\d{6}-\\w{4})");
	}
	/**
	 * 
	 * @param date formats accepted are: yyyy-mm-dd or yyyymmdd.
	 * constraints have been set so it is not possible to hava mm > 12 and dd > 39
	 * @return true if string matches format
	 */
	private boolean validateDate(String date){
		return date.matches("(\\d{4}-[0-1][0-2]-[0-3]\\d)");
//		return date.matches("([0-3]\\d-[0-1][0-2]-\\d{4})|(\\d{8})");
	}
	/**
	 * 
	 * @param timestamp String which can be transformed to timestamp. Should pass the validator method to make sure it passes 
	 * @return if format is not correct null will be returned
	 * Date can have two formats therefore check is made to find out which one
	 */
	private Timestamp stringToTimestamp(String timestamp){
		if(!validateDate(timestamp)) return null;

		DateFormat dateFormat = new SimpleDateFormat((timestamp.contains("-") ? "yyyy-MM-dd" : "yyyyMMdd"));

		//		if(!timestamp.contains("-")){
		//			dateFormat = new SimpleDateFormat("ddMMyyyy");
		//		}
		Date date = null;
		try {
			date = dateFormat.parse(timestamp);
		} catch (ParseException e) {
			System.err.println("failed to parse timestamp");
		}

		return new Timestamp(date.getTime());

	}

}

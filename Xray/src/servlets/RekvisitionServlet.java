package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.Timestamp;

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
	public static final String MODALITY = "modalitet";
	public static final String STATUS_LIST = "statusList";
	
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Bruger activeUser = (Bruger) request.getAttribute(ACTIVE_USER);
		
		if(activeUser == null){
			activeUser = userDao.findByPrimaryKey(1);
		}
		String cond = "status=?";
		String rekvCondName = "bruger_navn=?";
		String rekvCondId = "rekvirent_id=?";

		Rekvisition[] rekvlist;

		System.out.println("status options: " + Status.PENDING.ordinal());
		rekvlist = rekvisitionDao.findDynamic(rekvCondId, 0, -1, activeUser.getBrugerId());
		// rekvlist = rekvisitionDao.findDynamic(cond, 0, -1, new Object[]{Status.PENDING}); //(cond, 0, -1, new Object[]{Status.PENDING});
		System.out.println("length: " + rekvlist.length);
		// for test
		for (Rekvisition rekvisition : rekvlist) {
			
			System.out.println("id: " + rekvisition.getRekvisitionId());
			System.out.println("status: " + rekvisition.getStatus());
		}
		
		Modalitet[] modList = modDao.findDynamic(null, 0, -1, null);
		
		for (Modalitet modalitet : modList) {
			System.out.println(modalitet.getModalitetNavn());
		}
		for (Status s : Status.values()) {
			System.out.println(s.name());
		}
		
		request.setAttribute(REKVISITION_LIST, rekvlist);
		
		request.getRequestDispatcher(REKVISITION_PAGE).forward(request, response);
		
	}
	
	private init(Request request){
		request.setAttribute(MODALITY, modList);
		request.setAttribute(STATUS_LIST, Status.values());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Object> params = new ArrayList<>();
		String cond = "";
		// parameters
		String cpr = request.getParameter("cpr");
		String name = request.getParameter("name");
		String modality = request.getParameter("modality");
		String department = request.getParameter("department");
		String date = request.getParameter("date");
		Timestamp dateObj;
		try{
			dateObj = (!date.equals("") ? new Timestamp(Long.valueOf(date)) : null);
		} catch(Exception e){
			System.out.println("wrong date format");
			dateObj = null;
		}

		String status = request.getParameter("status");
		Status statusObj = null;
		
		System.out.println("status: " + status);
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
		rekvDto = rekvDao.findByAdvSearch(cpr, name, modality, statusObj, dateObj, department);

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
		
		System.out.println();
		request.getRequestDispatcher(REKVISITION_PAGE).forward(request, response);

	}

}
